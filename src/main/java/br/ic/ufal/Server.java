package br.ic.ufal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.stream.Stream;

public class Server {

    public static void main(String[] args) throws Exception {
        int port = 9090;
        String clientSentence;
        String capitalizedSentence;

        System.out.println("SINGLE-THREAD SERVER");
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket connectionSocket = serverSocket.accept();

            //Get OUT-handle
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            //Get IN-handle
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));


//            Stream<String> lines = inFromClient.lines();
//            lines.forEach((String s) -> {
//                System.out.println(s);
//            });
//            inFromClient.close();
//            lines.close();

            System.out.println("DOING REQUEST (server): ");
            HttpClientHook http = new HttpClientHook();
            String response = http.sendGet("https://viacep.com.br/ws/01001000/json", null);
            System.out.println("GOT RES: ");
            System.out.println(response);
            outToClient.writeBytes(response);
       }
   }
}