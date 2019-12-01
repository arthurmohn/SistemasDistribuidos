package br.com.bahia.bancos.modelo;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Banco extends Remote { //Interface Banco
	
	public boolean acessar_conta(String senha, String num_conta) throws RemoteException;
	
	public void sacar(String senha, String num_conta, BigDecimal valor) throws RemoteException;
	
	public void deposito(String senha, String num_conta, BigDecimal valor) throws RemoteException;
	
	public void transferencia(String senha, String num_conta_origem, String num_conta_destino, BigDecimal valor) throws RemoteException;
	
	public String saldo(String senha, String num_conta) throws RemoteException;
	
	public String extrato(String senha, String num_conta) throws RemoteException;
	
	//public ArrayList historico(String senha, String num_conta);
}
