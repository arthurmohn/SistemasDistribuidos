package Server;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculadora extends Remote {

    public BigDecimal adicao(BigDecimal op1, BigDecimal op2) throws
            RemoteException;

    public BigDecimal subtracao(BigDecimal op1, BigDecimal op2) throws
            RemoteException;

    public BigDecimal divisao(BigDecimal op1, BigDecimal op2) throws
            RemoteException;

    public BigDecimal multiplicacao(BigDecimal op1, BigDecimal op2) throws
            RemoteException;

}
