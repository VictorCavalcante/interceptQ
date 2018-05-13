package br.ic.ufal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        System.out.println("SINGLE THREAD CLIENT");

        Socket clientSocket = new Socket("localhost", 9090);

        //Get OUT-handle
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        //Get IN-handle
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Get info
        System.out.println("What do you want me to yell back at you?\n");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String modifiedSentence;
        String sentence = inFromUser.readLine();

        //Exchange info
        outToServer.writeBytes(sentence + '\n');
        modifiedSentence = inFromServer.readLine();

        //Print out
        System.out.println("FROM SERVER: " + modifiedSentence);
        clientSocket.close();
    }
}