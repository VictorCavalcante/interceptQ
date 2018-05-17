package br.ic.ufal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws Exception {
        System.out.println("SINGLE-THREAD SERVER");

        int port = 9090;
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket connectionSocket = serverSocket.accept();

            //Get OUT-handle
            DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
            //Get IN-handle
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

            String[] requestInfo = inFromClient.readLine().split(" ");

            if (requestInfo.length != 2) {
                System.out.println("Invalid entry!");
                connectionSocket.close();
                break;
            }

            String reqMethod = requestInfo[0];
            String reqUrl = requestInfo[1];

            System.out.println("DOING REQUEST (server): ");
            HttpClientHook http = new HttpClientHook();
            String response = http.sendGet(reqUrl, null);

            //write back to Socket
            outToClient.writeBytes(response);
            //close resources
            outToClient.close();
            connectionSocket.close();
       }
   }
}