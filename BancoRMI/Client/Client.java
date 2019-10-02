package Client;

import Server.Banco;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String args[]) {
        Banco banco;
        Registry registry;

        try {
            registry = LocateRegistry.getRegistry("127.0.0.1", 2001);
            banco = (Banco) registry.lookup("Banco");

            banco.deposito(new BigDecimal(500));

            banco.saque(new BigDecimal(40.87));

            System.out.println("O saldo é: " + banco.saldo().setScale(2, RoundingMode.DOWN));

        }catch (RemoteException | NotBoundException e){
            e.printStackTrace();
        }
    }
}
