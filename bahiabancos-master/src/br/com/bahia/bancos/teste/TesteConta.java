package br.com.bahia.bancos.teste;

import br.com.bahia.bancos.modelo.Conta;
import br.com.bahia.bancos.util.JPAUtil;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TesteConta {
	
	public static void main(String[] args) {
		Conta conta = new Conta();
		conta.setAgencia("0001");
		conta.setNum_conta("00001");
		conta.setSenha("1234");
		conta.setTipo("corrente");
		conta.setSaldo(new BigDecimal("100"));
		
		EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();
        
        em.persist(conta);
        em.getTransaction().commit();
		em.close();
	
	}
}
