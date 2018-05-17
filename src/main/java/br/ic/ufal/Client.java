package br.ic.ufal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("SINGLE THREAD CLIENT");
        System.out.println("Testing 1 - Send Http GET request");

        Socket clientSocket = new Socket("localhost", 9090);

        //Get OUT-handle
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        //Get IN-handle
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Get info
        System.out.println("Digite o cep:");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String requestUrl = inFromUser.readLine();
        String response;

        //Exchange info
        outToServer.writeBytes(requestUrl + '\n');

        response = inFromServer.readLine();

        //Print out
        System.out.println("FROM SERVER: " + response);
        clientSocket.close();

    }
}