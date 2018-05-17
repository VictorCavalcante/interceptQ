package br.ic.ufal;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        int port = 9090;
        Cache cacheRegistry = new Cache();
        ServerSocket serverSocket = new ServerSocket(port);
        ServerRequestUtil serverRequestUtil = new ServerRequestUtil(cacheRegistry);

        System.out.println("### SERVER STARTED");
        while (true) {
            Socket connectionSocket = serverSocket.accept();
            ServerClientThread cliConnectionThread = new ServerClientThread(connectionSocket, serverRequestUtil);
            cliConnectionThread.start();
       }
   }
}