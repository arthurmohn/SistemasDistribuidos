package br.com.bahia.bancos.teste;

import javax.persistence.EntityManager;

import br.com.bahia.bancos.modelo.Pessoa;
import br.com.bahia.bancos.util.JPAUtil;

public class TestePessoa {

	public static void main(String[] args) {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf_cnpj("05835338503");
		pessoa.setEmail("armando@baiano.ba");
		pessoa.setEndereco("Barreiras, Bahia");
		pessoa.setNome("Armando Neto");
		pessoa.setTipo("fisica");
		
		EntityManager em = new JPAUtil().getEntityManager();
        em.getTransaction().begin();
        
        em.persist(pessoa);
        em.getTransaction().commit();
		em.close();
		

	}

}
