package br.com.bahia.bancos.modelo;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class Server { //Servidor RMI
	Server (){
		try {
			Banco b = new BancoImpl();
			Naming.rebind("rmi://localhost/Banco", b);
		}
		catch (Exception e) {
			e.printStackTrace();
	}
}
	public static void main(String[] args) throws RemoteException{
	java.rmi.registry.Registry rmiRegistry = java.rmi.registry.LocateRegistry.createRegistry(1080);
	new Server();
	}
}
