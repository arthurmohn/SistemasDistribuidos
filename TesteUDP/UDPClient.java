package TesteUDP;

import java.net.*;
import java.io.*;

public class UDPClient {
    public static void main(String args[]) {

        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket();
            byte[] m = args[0].getBytes();
            InetAddress aHost = InetAddress.getByName(args[1]);
            int serverPort = 6789;
            DatagramPacket request = new DatagramPacket(m, args[0].length(), aHost, serverPort);
            aSocket.send(request);
            System.out.println("Enviado: " + new String(request.getData()));
            byte[] buffer = new byte[100];
            DatagramPacket resposta = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(resposta);
            System.out.println("Resposta: " + new String(resposta.getData()));

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null)
                aSocket.close();
        }
    }
}