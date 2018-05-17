package br.ic.ufal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerClientThread extends Thread {

    Socket clientSocket;

    ServerClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        DataOutputStream outToClient = null; //Get OUT-handle
        BufferedReader inFromClient = null; //Get IN-handle

        try {
            outToClient = new DataOutputStream(clientSocket.getOutputStream());
            inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean connected = true;

        while (connected) {
            try {
                System.out.println("WAITING FOR CLIENT <=");
                String requestInput = inFromClient.readLine();

                if (!requestInput.equals("EXIT")) {
                    String[] requestParams = requestInput.split(" ");

                    if (requestParams.length != 2) {
                        connected = false;
                        throw new Exception("Invalid entry!");
                    }

                    String reqMethod = requestParams[0];
                    String reqUrl = requestParams[1];

                    System.out.println("DOING REQUEST (server): ");
                    HttpClientHook http = new HttpClientHook();
                    String response = http.sendGet(reqUrl, null);

                    //write back to Socket
                    System.out.println("WAITING TO SENT TO CLIENT =>");
                    outToClient.writeBytes(response + '\n');
                } else {
                    connected = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                connected = false;
            }
        }

        try {

            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
