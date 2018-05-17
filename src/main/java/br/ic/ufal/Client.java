package br.ic.ufal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("SINGLE THREAD CLIENT");

        Socket clientSocket = new Socket("localhost", 9090);

        //Get OUT-handle
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        //Get IN-handle
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Get info
        System.out.println("> Let's get it started!");
        System.out.print("> Provide us with the request <METHOD URL>: ");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String requestInput = inFromUser.readLine();

        //Exchange info
        outToServer.writeBytes(requestInput + '\n');

        //Print out
        System.out.println("FROM SERVER:");
        Stream<String> response = inFromServer.lines();
        response.forEach(System.out::println);

        clientSocket.close();
    }
}