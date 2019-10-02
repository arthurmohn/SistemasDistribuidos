package Client;

import Server.Calculadora;

import java.math.BigDecimal;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientCalculadora {

    public static void main(String args[]) {
        Calculadora calculadora;
        Registry registry;

        try {
            registry = LocateRegistry.getRegistry("172.16.17.204", 2001);
            calculadora = (Calculadora) registry.lookup("Calculadora");

            System.out.println(calculadora.adicao(new BigDecimal(6), new BigDecimal(3)));
            System.out.println(calculadora.subtracao(new BigDecimal(6), new BigDecimal(3)));
            System.out.println(calculadora.divisao(new BigDecimal(6), new BigDecimal(3)));
            System.out.println(calculadora.multiplicacao(new BigDecimal(6), new BigDecimal(3)));

        }catch (RemoteException | NotBoundException e){
            e.printStackTrace();
        }
    }
}
