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

    private static final String[] servers = new String[]{"10.5.0.5", "10.5.0.6", "10.5.0.7"};
    private static int currentServer = 0;
    private static final String USER_AGENT = "Mozilla/5.0";
    public static void main(String[] args) {
        port(getPort());
        get("/index.html", (req, res) -> getIndex());
        get("/", (req, res) -> getIndex());
        get("/log/:log", (req, res) -> saveLog(req.params("log")));
    }

    private static String getIndex(){
        return """
                 <!DOCTYPE html>
                 <html>
                                
                 <head>
                     <title>Taller 6 AREP</title>
                     <meta charset="UTF-8">
                     <meta name="viewport" content="width=device-width, initial-scale=1.0">
                     <style>
                         table, th, td {
                             border: 1px solid black;
                             border-collapse: collapse;
                         }
                     </style>
                 </head>
                                
                 <body>
                     <h1>Profe pongame 5</h1>
                     <h2>Ingresa un log, y te traigo los ultimos 10 :D </h2>
                     <form>
                         <label for="log">Ingresa el log: </label><br>
                         <input type="text" id="log" name="name" value="Holi">
                         <br><br>
                         <input type="button" value="Submit" onclick="loadGetLogs()">
                     </form>
                     <hr>
                     <div id="getreslog" style="display: none;">
                         <table style="border: 1px solid black;">
                             <thead>
                                 <tr>
                                     <th>Log</th>
                                     <th>Created</th>
                                 </tr>
                             </thead>
                             <tbody id="table"></tbody>
                         </table>
                     </div>
                     <script>
                         let loadGetLogs = () => {
                             let nameVar = document.getElementById("log").value;
                             fetch("/log/" + nameVar).then(res => res.json())
                                 .then((res) => {
                                     let searchDiv = document.getElementById("table");
                                     buildLogTable(searchDiv, res);
                                     document.getElementById("getreslog").setAttribute("style", "display: block");
                                 })
                         };
                                
                         let buildLogTable = (container, logs) => {
                             container.innerHTML = ""
                             for (let i = 0; i < logs.length; i++) {
                                 let row = document.createElement("tr");
                                 let logData = document.createElement("td");
                                 logData.innerText = logs[i]["log"];
                                 row.appendChild(logData);
                                 let createdData = document.createElement("td");
                                 createdData.innerText = logs[i]["created"];
                                 row.appendChild(createdData);
                                 container.appendChild(row);
                             }
                         };
                                
                         let createHTMLElement = (tag, content, container) => {
                             let element = document.createElement(tag);
                             element.innerText = content;
                             container.appendChild(element);
                         };
                     </script>
                 </body>
                                
                 </html>
                """;
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
        System.out.println("http://" + servers[getNextRoundRobin()] + ":6000/log/" + log);
        return "http://" + servers[getNextRoundRobin()] + ":6000/log/" + log;
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
