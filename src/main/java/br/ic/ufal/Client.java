package br.ic.ufal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("*** CLIENT STARTED");

        String userInput = "";
        Socket clientSocket = new Socket("localhost", 9090);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());//Get OUT-handle
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//Get IN-handle

        // Get info
        System.out.println("> Let's get started!");
        System.out.println("> (type 'EXIT' to leave)\n");

        while (!userInput.equals("EXIT")) {
            System.out.println("---------------------------------------------");
            System.out.println("> Provide us with the request <METHOD URL>: ");
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            userInput = inFromUser.readLine();

            // Check params
            String[] requestParams = userInput.split(" ");
            if (!userInput.equals("EXIT") && requestParams.length != 2) {
                System.out.println("> Invalid entry, try again");
            } else {
                // Write to server
                outToServer.writeBytes(userInput + '\n');

                if (!userInput.equals("EXIT")) {
                    System.out.println("> Making request...");
                    //Print out
                    String result = inFromServer.readLine();
                    System.out.println("> Response:");
                    System.out.println(result);
                }
            }

        }

        System.out.println("\n> See you soon!");
        clientSocket.close();
    }
}