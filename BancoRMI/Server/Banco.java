package Server;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Banco extends Remote {

    public void saque (BigDecimal valor) throws RemoteException;

    public void deposito (BigDecimal op1) throws RemoteException;

    public BigDecimal saldo() throws RemoteException;
}
