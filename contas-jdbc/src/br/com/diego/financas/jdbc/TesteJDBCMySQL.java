package br.com.diego.financas.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.diego.financas.modelo.Conta;

public class TesteJDBCMySQL {

	public static void main(String[] args) throws SQLException {

		Conta conta = new Conta();
		conta.setNum_conta("10829390123");
		conta.setSaldo(new Double(100000));
		conta.setData_criacao(new Date(System.currentTimeMillis()));

		Connection con = new ConnectionFactory().getConnectionMySQL();
		new BD(con).geraTabelaContasMySQL();

		con.setAutoCommit(false);

		ContaDAO dao = new ContaDAO(con);
		dao.adiciona(conta);

		List<Conta> lista = dao.lista();

		for (Conta c : lista) {
			System.out.println(c.getNum_conta());
		}

		con.commit();
		con.close();
	}

}
