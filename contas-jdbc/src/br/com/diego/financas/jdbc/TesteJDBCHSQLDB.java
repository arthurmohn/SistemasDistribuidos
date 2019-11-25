package br.com.diego.financas.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import br.com.diego.financas.modelo.Conta;

public class TesteJDBCHSQLDB {

	public static void main(String[] args) throws SQLException {

		Conta conta = new Conta();
		conta.setNum_conta("10829390123");
		conta.setSaldo(new Double(100000));
		conta.setData_criacao(new Date(System.currentTimeMillis()));


		Connection con = new ConnectionFactory().getConnectionHSQLDB();

		new BD(con).geraTabelaContasHSQLDB();

		con.setAutoCommit(false);

		ContaDAO dao = new ContaDAO(con);
		dao.adiciona(conta);

		List<Conta> contas = dao.lista();

		for (Conta c : contas) {
			System.out.println(c.getNum_conta());
		}

		con.commit();
		con.close();
	}

}
