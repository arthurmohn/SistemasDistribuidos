package Principal;

import java.math.BigDecimal;
import java.net.*;
import java.io.*;

public class TCPClient {
    public static void main (String args[]) {

        Socket s = null;
        try{
            TipoOperacao soma = TipoOperacao.SOMA;
            TipoOperacao div = TipoOperacao.DIVISAO;
            TipoOperacao sub = TipoOperacao.SUBTRACAO;
            TipoOperacao mult = TipoOperacao.MULTIPLICACAO;
            TipoOperacao req = TipoOperacao.REQUISICOES_ATENDIDA;

            int serverPort = 7896;
            s = new Socket(args[3], serverPort);
            ObjectInputStream in = new ObjectInputStream( s.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream( s.getOutputStream());

            BigDecimal[] valores = new BigDecimal[2];
            valores[0] = new BigDecimal(args[1]);
            valores[1] = new BigDecimal(args[2]);

            Mensagem msg = null;

            if(args[0].equalsIgnoreCase("SOMA"))
                msg = new Mensagem(soma, valores);

            if(args[0].equalsIgnoreCase( "SUBTRACAO"))
                msg = new Mensagem(sub, valores);

            if(args[0].equalsIgnoreCase( "DIVISAO"))
                msg = new Mensagem(div, valores);

            if(args[0].equalsIgnoreCase("MULTIPLICACAO"))
                msg = new Mensagem(mult, valores);

            if(args[0].equalsIgnoreCase( "REQUISICOES_ATENDIDAS"))
                msg = new Mensagem(req, valores);


            out.writeObject(msg);

            Mensagem msgIN = (Mensagem) in.readObject();

            System.out.println("Resultado: " + msgIN.getParemetro1()) ;
        }catch (UnknownHostException e){
            System.out.println("Sock:"+e.getMessage());
        }catch (EOFException e){System.out.println("EOF:"+e.getMessage());
        }catch (IOException e){System.out.println("IO:"+e.getMessage());} catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
    }
}