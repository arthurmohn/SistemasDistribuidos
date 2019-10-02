package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorRMI {

    public static void main(String args[]) {

        try{
            Calculadora calculadora = new ImpCalculadora();
            Registry registry = LocateRegistry.createRegistry(2001);
            registry.rebind("Calculadora",calculadora);

            System.out.println("Servidor executando!");

        }catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
