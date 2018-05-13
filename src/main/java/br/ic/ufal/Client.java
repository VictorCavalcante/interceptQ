package br.ic.ufal;

public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("SINGLE THREAD CLIENT");

        HttpClientHook http = new HttpClientHook();

        System.out.println("Testing 1 - Send Http GET request");
        http.sendGet();

        System.out.println("\nTesting 2 - Send Http POST request");
        http.sendPost();

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