package br.ic.ufal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("SINGLE THREAD CLIENT");

//        Map<String, String> map = new HashMap<>();
//        map.put("User-Agent", "Mozilla/5.0");
//        http.sendGet("https://viacep.com.br/ws/01001000/json/", null);
        //        System.out.println("\nTesting 2 - Send Http POST request");
//        http.sendPost("https://selfsolve.apple.com/wcResults.do", null);

        HttpClientHook http = new HttpClientHook();

        System.out.println("Testing 1 - Send Http GET request");
        String response = http.executePost("http://localhost:9090/ws/01001000/json", null);
        System.out.println("THE ACTUAL THING:");
        System.out.println(response);


//        Socket clientSocket = new Socket("localhost", 9090);
//
//        //Get OUT-handle
//        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
//        //Get IN-handle
//        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//
//        // Get info
//        System.out.println("What do you want me to yell back at you?\n");
//        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
//        String modifiedSentence;
//        String sentence = inFromUser.readLine();
//
//        //Exchange info
//        outToServer.writeBytes(sentence + '\n');
//        modifiedSentence = inFromServer.readLine();
//
//        //Print out
//        System.out.println("FROM SERVER: " + modifiedSentence);
//        clientSocket.close();
    }
}