package br.ic.ufal;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class HttpClientHook {

    private final String USER_AGENT = "Mozilla/5.0";
    private Cache cacheRegistry = new Cache();

    public void writeReqOnFile(String method, String url, int responseCode, String responseBody, boolean cached) {
        try(FileWriter fw = new FileWriter("request-log.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {

            String timeNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            out.println("---------------------------------------------\n"
                    + "Method: " + method + "\n"
                    + "URL: " + url + "\n"
                    + "Cached: " + String.valueOf(cached) + "\n"
                    + "Response Code: " + responseCode + "\n"
                    + "Response Body: " + responseBody + "\n"
                    + "Time: " + timeNow);
            System.out.println("[Request logged at " + timeNow + "]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // HTTP GET request
    protected String sendGet(String url, Map<String, String> headers) throws Exception {

        String resFromCache = cacheRegistry.getFromCache(url);
        if(resFromCache != null) {
            System.out.println("> Fetched cached request successfully");
            writeReqOnFile("GET", url, 200, resFromCache, true);
            return resFromCache;
        }

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT); // todo: add more headers...

        System.out.println("> Making 'GET' request to URL: " + url);
        System.out.println("> Response Code: " + con.getResponseCode());

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println("> Response Body:");
        System.out.println(response.toString());
        writeReqOnFile("GET", url, con.getResponseCode(), response.toString(), false);
        cacheRegistry.saveToCache(url, response.toString());

        return response.toString();
    }

    // HTTP POST request
    protected String sendPost(String url, Map<String, String> headers) throws Exception {

        String resFromCache = cacheRegistry.getFromCache(url);
        if(resFromCache != null) {
            System.out.println("> Fetched cached request successfully");
            writeReqOnFile("POST", url, 200, resFromCache, true);
            return resFromCache;
        }

        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

//        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
//        con.setDoOutput(true);
//        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//        wr.writeBytes(urlParameters);
//        wr.flush();
//        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("> Making 'POST' request to URL: " + url);
//        System.out.println("Post parameters : " + urlParameters);
        System.out.println("> Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        System.out.println(response.toString());
        writeReqOnFile("POST", url, con.getResponseCode(), response.toString(), false);
        cacheRegistry.saveToCache(url, response.toString());

        return response.toString();
    }

}