package br.com.bahia.bancos.modelo;

import javax.persistence.*;
import java.util.List;
import java.util.Calendar;

@Entity
public class Pessoa {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO) //Gera o id automaticamente
    private Integer id;
	private String nome;
	private String tipo;
	private String cpf_cnpj;
	private String email;
	private String endereco;
	
	@Temporal(TemporalType.DATE)
	private Calendar data_nascimento;
	
	@OneToOne(mappedBy="pessoa")
	@JoinColumn(unique=true)
	private Conta conta;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	

}