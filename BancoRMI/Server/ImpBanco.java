package Server;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.function.BiFunction;

public class ImpBanco extends UnicastRemoteObject implements Banco {

    private BigDecimal saldo;
    private String nomeCliente;
    private Integer numero;

    public ImpBanco(Integer numero, String nomeCliente, BigDecimal saldo) throws RemoteException {
        this.numero = numero;
        this.nomeCliente = nomeCliente;
        this.saldo = saldo;
    }

    public ImpBanco(Integer numero, String nomeCliente) throws RemoteException {
        this.numero = numero;
        this.nomeCliente = nomeCliente;
        this.saldo = new BigDecimal(0.0);
    }


    protected ImpBanco() throws RemoteException {
        super();
    }

    @Override
    public void saque(BigDecimal valor) throws RemoteException {
        this.saldo = this.saldo.subtract(valor);
    }

    @Override
    public void deposito(BigDecimal op1) throws RemoteException {
        this.saldo = this.saldo.add(op1);
    }

    @Override
    public BigDecimal saldo() throws RemoteException {
        return this.saldo;
    }
}
