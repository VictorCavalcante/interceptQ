package br.ic.ufal;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Client {

    public static void main(String[] args) throws Exception {
//        Socket socket;
//        ObjectOutputStream oos;
//        ObjectInputStream ois;
//
//        //establish socket connection to server
//        socket = new Socket("localhost", 9090);
//        //write to socket using ObjectOutputStream
//        oos = new ObjectOutputStream(socket.getOutputStream());
//        System.out.println("Sending request to Socket Server");
//        //read the server response message
//        ois = new ObjectInputStream(socket.getInputStream());
//        String message = (String) ois.readObject();
//        System.out.println("Message: " + message);
//        //close resources
//        ois.close();
//        oos.close();

//        Map<String, String> map = new HashMap<>();
//        map.put("User-Agent", "Mozilla/5.0");
//        http.sendGet("https://viacep.com.br/ws/01001000/json/", null);
//                System.out.println("\nTesting 2 - Send Http POST request");
//        http.sendPost("https://selfsolve.apple.com/wcResults.do", null);

        System.out.println("SINGLE THREAD CLIENT");
        System.out.println("Testing 1 - Send Http GET request");

        HttpURLConnection connection = null;

        try {
            //Create connection
            URL url = new URL("http://localhost:9090/ws/01001000/json");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
//            connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
//            connection.setRequestProperty("Content-Language", "en-US");

            connection.setUseCaches(false);
            connection.setDoOutput(true);
            //Send request
//            DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
//            wr.writeBytes(urlParameters);
//            wr.close();

            //Get Response
            InputStream inputStream = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            System.out.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }

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