package Principal;

import java.io.*;
import java.math.BigDecimal;
import java.net.*;

public class TCPServer {

    static Integer reqServidor = 0;

    public static void main (String args[]) {
        try
        {
            int serverPort = 7896;
            ServerSocket listenSocket = new ServerSocket(serverPort);
            while(true)
            {
                Socket clientSocket = listenSocket.accept();
                Connection c = new Connection(clientSocket);
            }
        } catch(IOException e) {System.out.println("Listen :"+e.getMessage());}
    }
}