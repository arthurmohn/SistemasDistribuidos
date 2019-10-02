package Server;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Servidor {

    public static void main(String args[]){

        try{
            Banco banco = new ImpBanco(111, "Arthur");
            Registry registry = LocateRegistry.createRegistry(2001);
            registry.rebind("Banco", banco);

            System.out.println("Servidor em Execução!");

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
