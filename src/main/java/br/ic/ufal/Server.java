package br.ic.ufal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
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


            StringBuilder stringBuilder = new StringBuilder(1000);
            Scanner scanner = new Scanner(connectionSocket.getInputStream());


            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }
            System.out.println(stringBuilder.toString());

//            System.out.println(inFromClient);
        }
    }
}