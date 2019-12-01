package br.com.bahia.bancos.modelo;

import javax.persistence.*;

import br.com.bahia.bancos.modelo.Pessoa;
import br.com.bahia.bancos.modelo.Transacao;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Gera id automaticamente
    private Integer id;
    private String agencia;
    private String num_conta;
    private String senha;
    private BigDecimal saldo;
    private String tipo;
    
    @OneToOne	//relacao (1:1)
    @JoinColumn(unique=true)
    private Pessoa pessoa;
    
    @OneToMany(mappedBy = "conta")	//relacao (1:n)
    private List<Transacao> movimentacoes;

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNum_conta() {
		return num_conta;
	}

	public void setNum_conta(String num_conta) {
		this.num_conta = num_conta;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public void setTransacao(Transacao transacao) {
		this.movimentacoes.add(transacao);
	}
	
	public void saque(BigDecimal valor) {
		this.saldo = this.saldo.subtract(valor);
	}
	
	public void deposito(BigDecimal valor) {
		this.saldo = this.saldo.add(valor);
	}
	
	public Integer getId() {
		return this.id;
	}
    
    
}