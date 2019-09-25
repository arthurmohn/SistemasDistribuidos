import java.io.*;
import java.math.BigDecimal;
import java.net.*;

public class Connection extends Thread {
    ObjectInputStream in;
    ObjectOutputStream out;
    Socket clientSocket;
    public Connection (Socket aClientSocket) {
        try {
            clientSocket = aClientSocket;

            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());

            this.start();
        } catch(IOException e) {System.out.println("Connection:"+e.getMessage());}
    }
    public void run(){
        try {

            Mensagem msgIN =  (Mensagem) in.readObject();
            Mensagem msgOUT = null;
            BigDecimal aux;

            String op = msgIN.getOp();

            if(op.equalsIgnoreCase(TipoOperacao.SOMA.name()))
            {
                BigDecimal result = msgIN.getParemetro1().add(msgIN.getParametro2());
                aux = result;
                msgOUT = new Mensagem(null, aux, null);
            }

            if(op.equalsIgnoreCase(TipoOperacao.SUBTRACAO.name()))
            {
                BigDecimal result = msgIN.getParemetro1().subtract(msgIN.getParametro2());
                aux = result;
                msgOUT = new Mensagem(null, aux, null);
            }

            if(op.equalsIgnoreCase(TipoOperacao.DIVISAO.name()))
            {
                BigDecimal result = msgIN.getParemetro1().divide(msgIN.getParametro2());
                aux = result;
                msgOUT = new Mensagem(null, aux, null);
            }

            if(op.equalsIgnoreCase(TipoOperacao.MULTIPLICACAO.name()))
            {
                BigDecimal result = msgIN.getParemetro1().multiply(msgIN.getParametro2());
                aux = result;
                msgOUT = new Mensagem(null, aux, null);
            }


            out.writeObject(msgOUT);

        } catch(EOFException e) {System.out.println("EOF:"+e.getMessage());
        } catch(IOException e) {System.out.println("IO:"+e.getMessage());} catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{ try {clientSocket.close();}catch (IOException e){/*close failed*/}}
    }
}
