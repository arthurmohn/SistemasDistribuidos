package br.com.bahia.bancos.modelo;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.mysql.cj.Query;
import com.mysql.cj.result.RowList;

import br.com.bahia.bancos.modelo.Conta;

import br.com.bahia.bancos.util.JPAUtil;

public class BancoImpl extends UnicastRemoteObject implements Banco{
	
	protected BancoImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean acessar_conta(String senha, String num_conta) { //Implementacao para verificar acesso a conta
		
		EntityManager em = new JPAUtil().getEntityManager(); //Cria um gerenciador de entidade
		
		//Seleciona no BD a entidade c do tipo Conta que possui numero da conta = num_conta
		javax.persistence.Query query =em.createQuery("SELECT c FROM Conta c  WHERE c.num_conta = "+num_conta); 
		
		List<Conta> lista = query.getResultList();	//Pega a entidade do query e coloca em uma lista
		em.close();
		if(lista.size() == 0) {							//Verifica se a tabela nao esta vazia
			System.out.println("Conta inexistente");
			return false;
		}
		if(lista.get(0).getSenha().equalsIgnoreCase(senha)) {	//Verifica se a senha esta correta
			return true;
		}
		System.out.println("Senha incorreta");
		return false;

	}

	@Override
	public void sacar(String senha, String num_conta, BigDecimal valor) {
		EntityManager em = new JPAUtil().getEntityManager();
		
		//Seleciona no BD a entidade c do tipo Conta que possui numero da conta = num_conta
		javax.persistence.Query query = em.createQuery("SELECT c FROM Conta c  WHERE c.num_conta = "+num_conta);
		
		List<Conta> lista = query.getResultList();	//Pega a entidade do query e coloca em uma lista
		em.close();
		Conta conta = lista.get(0);					//Pega o elemento da primeira posicao da lista
		
		if(conta.getSenha().equalsIgnoreCase(senha)) {	//Verifica se a senha esta correta
			BigDecimal saldo = conta.getSaldo();
			if(saldo.compareTo(valor) == -1) {				//Conpara o valor a ser sacado com o saldo
				System.out.println("Saldo insuficiente!");
				return ;
			}
			else {
				EntityManager em2 = new JPAUtil().getEntityManager();
				Integer id = conta.getId();
				Transacao transacao = new Transacao();
				
				Conta contaAtualizada = em2.find(Conta.class, id);
				em2.getTransaction().begin();
				
				contaAtualizada.saque(valor);				//Chama o metodo saque
				transacao.setConta(contaAtualizada);		//Salva a operacao na tabela Transacoes
				transacao.setValor(valor.multiply(new BigDecimal("-1")));
				transacao.setIdOrigem(id);
				transacao.setData(Calendar.getInstance());
				contaAtualizada.setTransacao(transacao);  //Salva a transacao na lista de transacoes da conta
				
				em2.persist(transacao);
				em2.persist(contaAtualizada);
		        em2.getTransaction().commit();
		        em2.close();
			}
		}
		else {
			System.out.println("Senha incorreta");
		}
		
	}

	@Override
	public void deposito(String senha, String num_conta, BigDecimal valor) {
		EntityManager em = new JPAUtil().getEntityManager();
		
		//Seleciona no BD a entidade c do tipo Conta que possui numero da conta = num_conta
		javax.persistence.Query query = em.createQuery("SELECT c FROM Conta c  WHERE c.num_conta = "+num_conta);
		
		List<Conta> lista = query.getResultList();		//Pega a conta do query e coloca em uma lista
		em.close();
		
		Conta conta = lista.get(0);		//Pega a conta da primeira posicao da lista
		
		if(conta.getSenha().equalsIgnoreCase(senha)) {	//Verifica se a senha esta correta
			
			BigDecimal saldo = conta.getSaldo(); 		
			EntityManager em2 = new JPAUtil().getEntityManager();
			Integer id = conta.getId();
			Transacao transacao = new Transacao();
			
			Conta contaAtualizada = em2.find(Conta.class, id);
			em2.getTransaction().begin();
			
			contaAtualizada.deposito(valor); 		//Realiza o deposito
			transacao.setConta(contaAtualizada);	//Adiciona a transacao ao BD
			transacao.setValor(valor);
			transacao.setIdDestino(id);
			transacao.setData(Calendar.getInstance());
			contaAtualizada.setTransacao(transacao);	//Adiciona a transacao a conta
			
			em2.persist(transacao);
			em2.persist(contaAtualizada);
	        em2.getTransaction().commit();
	        em2.close();
		}
		else {
			System.out.println("Senha incorreta");
		}
		
		
	}

	@Override
	public void transferencia(String senha, String num_conta_origem, String num_conta_destino, BigDecimal valor) {
		EntityManager em = new JPAUtil().getEntityManager();
		
		javax.persistence.Query query1 = em.createQuery("SELECT c FROM Conta c  WHERE c.num_conta = "+num_conta_origem);
		List<Conta> lista1 = query1.getResultList();
		javax.persistence.Query query2 = em.createQuery("SELECT c FROM Conta c  WHERE c.num_conta = "+num_conta_destino);
		List<Conta> lista2 = query2.getResultList();
		em.close();
		Conta conta_origem = lista1.get(0);
		Conta conta_destino = lista2.get(0);
		
		if(conta_origem.getSenha().equalsIgnoreCase(senha)) {		//Verifica se a senha esta correta
			BigDecimal saldo = conta_origem.getSaldo();				//Obtem o saldo da conta de origem
			if(saldo.compareTo(valor) == -1) {						//Verifica se ha saldo suficiente
				System.out.println("Saldo insuficiente!");
				return ;
			}
			else {
				EntityManager em2 = new JPAUtil().getEntityManager();
				Integer id_origem = conta_origem.getId();
				Integer id_destino = conta_destino.getId();
				Transacao transacao_origem = new Transacao();
				Transacao transacao_destino = new Transacao();
				
				Conta contaAtualizadaOrigem = em2.find(Conta.class, id_origem);		//Seleciona a conta de origem
				Conta contaAtualizadaDestino = em2.find(Conta.class, id_destino);	//Seleciona a conta de destino
				em2.getTransaction().begin();
				
				contaAtualizadaOrigem.saque(valor);									//Realiza um saque na conta origem
				transacao_origem.setConta(contaAtualizadaOrigem);					//Adiciona a transacao ao BD
				transacao_origem.setValor(valor.multiply(new BigDecimal("-1")));
				transacao_origem.setIdOrigem(id_origem);
				transacao_origem.setIdDestino(id_destino);
				transacao_origem.setData(Calendar.getInstance());
				contaAtualizadaOrigem.setTransacao(transacao_origem);
				
				contaAtualizadaDestino.deposito(valor);								//Realiza um deposito na conta destino
				transacao_destino.setConta(contaAtualizadaDestino);
				transacao_destino.setValor(valor);
				transacao_destino.setIdOrigem(id_origem);
				transacao_destino.setIdDestino(id_destino);
				transacao_destino.setData(Calendar.getInstance());
				contaAtualizadaDestino.setTransacao(transacao_destino);
				
				em2.persist(transacao_origem);
				em2.persist(contaAtualizadaOrigem);
				em2.persist(transacao_destino);
				em2.persist(contaAtualizadaDestino);
		        em2.getTransaction().commit();
		        em2.close();
			}
		}
	}

	@Override
	public String saldo(String senha, String num_conta) {
		EntityManager em = new JPAUtil().getEntityManager();
		
		javax.persistence.Query query = em.createQuery("SELECT c FROM Conta c  WHERE c.num_conta = "+num_conta);
		List<Conta> lista = query.getResultList();
		em.close();
		
		if(lista.get(0).getSenha().equalsIgnoreCase(senha)) {
			return lista.get(0).getSaldo().toString();
		}
		else {
			return("Senha incorreta!");
		}

		
	}

	@Override
	public String extrato(String senha, String num_conta){
		EntityManager em = new JPAUtil().getEntityManager();
		
		javax.persistence.Query query = em.createQuery("SELECT c FROM Conta c  WHERE c.num_conta = "+num_conta);
		List<Conta> lista = query.getResultList();
		Conta conta = lista.get(0);
		em.close();
		
		if(lista.get(0).getSenha().equalsIgnoreCase(senha)) {
			Integer id = conta.getId();
			String resp = "";
			em = new JPAUtil().getEntityManager();
			
			query = em.createQuery("SELECT t FROM Transacao t WHERE t.conta_id = "+id);
			List<Transacao> transacoes = query.getResultList();
			int ini;
			if(transacoes.size() < 10) ini = 0;
			else ini = transacoes.size()-10;
			for (int i = ini; i < transacoes.size(); i++) {
				resp+= transacoes.get(i).toString()+"\n";
			}
			em.close();
			return resp;
		}
		else {
			return("Senha incorreta!");
		}
		
	}

}
