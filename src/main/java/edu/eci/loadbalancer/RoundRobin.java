package edu.eci.loadbalancer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import static spark.Spark.*;

public class RoundRobin {

    private static final String[] servers = new String[]{"localhost", "localhost", "localhost"};
    private static int currentServer = 0;
    private static final String USER_AGENT = "Mozilla/5.0";
    public static void main(String[] args) {
        port(getPort());
        get("/index.html", (req, res) -> Files.readAllBytes(Path.of("src/main/resources/public/index.html")));
        get("/", (req, res) -> Files.readAllBytes(Path.of("src/main/resources/public/index.html")));
        get("/log/:log", (req, res) -> saveLog(req.params("log")));
    }

    private static String saveLog(String log) throws IOException {
        HttpURLConnection con = makeGetRequest(log);

        StringBuffer response = new StringBuffer();
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            response = buildResponse(con);
            System.out.println(response); // print result
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println(currentServer);
        return response.toString();
    }

    /*
    Method that takes in the raw response from the API and parses it to a more easily handled StringBuffer
     */
    private static StringBuffer buildResponse(HttpURLConnection con) throws IOException {
        StringBuffer response = new StringBuffer();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response;
    }

    /*
    Method that establishes the connection to the external API
     */
    private static HttpURLConnection makeGetRequest(String log) throws IOException {
        URL obj = new URL(createUrl(log));
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        return con;
    }

    private static int getNextRoundRobin(){
        int nextServer = currentServer % 3;
        currentServer++;
        return nextServer;
    }

    private static String createUrl(String log){
        return "http://" + servers[getNextRoundRobin()] + ":35000/log/" + log;
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
