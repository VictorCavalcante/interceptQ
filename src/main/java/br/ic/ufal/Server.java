package br.ic.ufal;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        int port = 9090;

        System.out.println("### SERVER STARTED");

        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket connectionSocket = serverSocket.accept();
            ServerClientThread cliConnectionThread = new ServerClientThread(connectionSocket);
            cliConnectionThread.start();
       }
   }
}