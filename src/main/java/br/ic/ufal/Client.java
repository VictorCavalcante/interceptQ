package br.ic.ufal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("*** CLIENT STARTED");

        String userInput = "";
        Socket clientSocket = new Socket("localhost", 9090);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());//Get OUT-handle
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//Get IN-handle

        // Get info
        System.out.println("> Let's get it started! :)\n");

        while (!userInput.equals("EXIT")) {
            System.out.println("> (type 'EXIT' to leave)");
            System.out.println("> Provide us with the request <METHOD URL>: ");
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            userInput = inFromUser.readLine();

            // Write to server
            System.out.println("WAITING TO SENT TO SERVER =>");
            outToServer.writeBytes(userInput + '\n');

            if (!userInput.equals("EXIT")) {
                //Print out
                System.out.println("FROM SERVER:");
                System.out.println("WAITING FOR SERVER <=");
                String result = inFromServer.readLine();
                System.out.println(result);
            }
        }

        clientSocket.close();
    }
}