package br.com.diego.financas.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import br.com.diego.financas.modelo.Conta;

public class ContaDAO {

	private Connection connection;

	public ContaDAO(Connection connection) {
		this.connection = connection;
	}

	public void adiciona(Conta conta) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("insert into Conta (num_conta, tipo_conta, senha, data_criacao, status, saldo) values (?,?,?,?,?,?)");
			ps.setString(1, conta.getNum_conta());
			ps.setString(2, conta.getTipo_conta());
			ps.setString(3, conta.getSenha());
			ps.setDate(4, new java.sql.Date(conta.getData_criacao().getTime()));
			ps.setString(5, conta.getStatus());
			ps.setDouble(6, conta.getSaldo());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Conta conta) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("update Conta set tipo_conta=?, senha=?, data_criacao=?, status=?, saldo=? where num_conta=?");
			ps.setString(2, conta.getTipo_conta());
			ps.setString(3, conta.getSenha());
			ps.setDate(4, new java.sql.Date(conta.getData_criacao().getTime()));
			ps.setString(5, conta.getStatus());
			ps.setDouble(6, conta.getSaldo());
			ps.setString(1, conta.getNum_conta());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Conta conta) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("delete from Conta where id=?");
			ps.setString(1, conta.getNum_conta());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Conta procura(Integer id) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("select tipo_conta, senha, data_criacao, status, saldo from Conta where num_conta = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				return null;
			}
			Conta conta = new Conta();
			conta.setTipo_conta(rs.getString("tipo_conta"));
			conta.setSenha(rs.getString("senha"));
			conta.setData_criacao(rs.getDate("data_criacao"));
			conta.setStatus(rs.getString("status"));
			conta.setSaldo(rs.getDouble("saldo"));
			ps.close();
			return conta;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public List<Conta> lista() {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("select tipo_conta, senha, data_criacao, status, saldo from Conta");
			ResultSet rs = ps.executeQuery();

			List<Conta> lista = new ArrayList<Conta>();

			while (rs.next()) {
				Conta conta = new Conta();
				conta.setTipo_conta(rs.getString("tipo_conta"));
				conta.setSenha(rs.getString("senha"));
				conta.setData_criacao(rs.getDate("data_criacao"));
				conta.setStatus(rs.getString("status"));
				conta.setSaldo((rs.getDouble("saldo")));
				lista.add(conta);
			}

			ps.close();
			return lista;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Conta> procuraPeloNome(String nome) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("select num_conta, tipo_conta, senha, data_criacao, status, saldo from Conta where num_conta like ?");
			ps.setString(1, "%" + nome + "%");
			ResultSet rs = ps.executeQuery();

			List<Conta> lista = new ArrayList<Conta>();

			while (rs.next()) {
				Conta conta = new Conta();
				conta.setNum_conta(rs.getString("num_conta"));
				conta.setTipo_conta(rs.getString("tipo_conta"));
				conta.setSenha(rs.getString("senha"));
				conta.setData_criacao(rs.getDate("data_criacao"));
				conta.setStatus(rs.getString("status"));
				conta.setSaldo(rs.getDouble("saldo"));
				lista.add(conta);
			}

			ps.close();
			return lista;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
