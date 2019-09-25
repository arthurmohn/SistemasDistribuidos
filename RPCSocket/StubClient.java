import java.math.BigDecimal;
import java.net.*;
import java.io.*;

public class StubClient implements Calculadora{

    @Override
    public BigDecimal soma(BigDecimal op1, BigDecimal op2) {
        Socket s = null;
        Mensagem msg = null;
        try{

            int serverPort = 7896;
            s = new Socket("localhost", serverPort);
            ObjectInputStream in = new ObjectInputStream( s.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream( s.getOutputStream());

            msg = new Mensagem();
            msg.setOp(TipoOperacao.SOMA);
            msg.setParametro1(op1);
            msg.setParametro2(op2);

            out.writeObject(msg);

            msg = (Mensagem) in.readObject();



        }catch (UnknownHostException e){
            System.out.println("Sock:"+e.getMessage());
        }catch (EOFException e){System.out.println("EOF:"+e.getMessage());
        }catch (IOException e){System.out.println("IO:"+e.getMessage());} catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}

        return msg.getParemetro1();
    }

    @Override
    public BigDecimal subtracao(BigDecimal op1, BigDecimal op2) {
        Socket s = null;
        Mensagem msg = null;
        try{

            int serverPort = 7896;
            s = new Socket("localhost", serverPort);
            ObjectInputStream in = new ObjectInputStream( s.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream( s.getOutputStream());

            msg = new Mensagem();
            msg.setOp(TipoOperacao.SUBTRACAO);
            msg.setParametro1(op1);
            msg.setParametro2(op2);

            out.writeObject(msg);

            msg = (Mensagem) in.readObject();



        }catch (UnknownHostException e){
            System.out.println("Sock:"+e.getMessage());
        }catch (EOFException e){System.out.println("EOF:"+e.getMessage());
        }catch (IOException e){System.out.println("IO:"+e.getMessage());} catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}

        return msg.getParemetro1();
    }

    @Override
    public BigDecimal multiplicacao(BigDecimal op1, BigDecimal op2) {
        Socket s = null;
        Mensagem msg = null;
        try{

            int serverPort = 7896;
            s = new Socket("localhost", serverPort);
            ObjectInputStream in = new ObjectInputStream( s.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream( s.getOutputStream());

            msg = new Mensagem();
            msg.setOp(TipoOperacao.MULTIPLICACAO);
            msg.setParametro1(op1);
            msg.setParametro2(op2);

            out.writeObject(msg);

            msg = (Mensagem) in.readObject();



        }catch (UnknownHostException e){
            System.out.println("Sock:"+e.getMessage());
        }catch (EOFException e){System.out.println("EOF:"+e.getMessage());
        }catch (IOException e){System.out.println("IO:"+e.getMessage());} catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}

        return msg.getParemetro1();
    }

    @Override
    public BigDecimal divisao(BigDecimal op1, BigDecimal op2) {
        Socket s = null;
        Mensagem msg = null;
        try{

            int serverPort = 7896;
            s = new Socket("localhost", serverPort);
            ObjectInputStream in = new ObjectInputStream( s.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream( s.getOutputStream());

            msg = new Mensagem();
            msg.setOp(TipoOperacao.DIVISAO);
            msg.setParametro1(op1);
            msg.setParametro2(op2);

            out.writeObject(msg);

            msg = (Mensagem) in.readObject();



        }catch (UnknownHostException e){
            System.out.println("Sock:"+e.getMessage());
        }catch (EOFException e){System.out.println("EOF:"+e.getMessage());
        }catch (IOException e){System.out.println("IO:"+e.getMessage());} catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}

        return msg.getParemetro1();
    }
}