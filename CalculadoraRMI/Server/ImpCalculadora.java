package Server;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class ImpCalculadora extends UnicastRemoteObject implements Calculadora{


    protected ImpCalculadora() throws RemoteException {
        super();
    }

    @Override
    public BigDecimal adicao(BigDecimal op1, BigDecimal op2) throws RemoteException {
        return op1.add(op2);
    }

    @Override
    public BigDecimal subtracao(BigDecimal op1, BigDecimal op2) throws RemoteException {
        return op1.subtract(op2);
    }

    @Override
    public BigDecimal divisao(BigDecimal op1, BigDecimal op2) throws RemoteException {
        return op1.divide(op2);
    }

    @Override
    public BigDecimal multiplicacao(BigDecimal op1, BigDecimal op2) throws RemoteException {
        return op1.multiply(op2);
    }
}
