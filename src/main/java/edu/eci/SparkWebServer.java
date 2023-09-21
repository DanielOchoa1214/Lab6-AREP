package edu.eci;

import edu.eci.webclient.filehandlers.FileHandler;

import static spark.Spark.*;

public class SparkWebServer {

    public static void main(String... args){
        port(getPort());
        get("hello", (req,res) -> "Hello Docker!");
        get("/cos/:value", (req, res) -> Math.cos(Double.parseDouble(req.params("value"))));
        get("/sin/:value", (req, res) -> Math.sin(Double.parseDouble(req.params("value"))));
        get("/palindrome/:str", (req, res) -> isPalindrome(req.params("str")));
        get("/magnitude", (req, res) -> magnitude(req.queryMap().get("x").value(), req.queryMap().get("y").value()));
        get("/index", (req, res) -> getIndex());
        get("/", (req, res) -> getIndex());
    }

    private static double magnitude(String x, String y){
        double xNum = Double.parseDouble(x);
        double yNum = Double.parseDouble(y);
        double x2 = xNum * xNum;
        double y2 = yNum * yNum;
        double mag2 = x2 + y2;
        return Math.sqrt(mag2);
    }

    private static boolean isPalindrome(String str){
        String reversed = new StringBuilder(str).reverse().toString();
        return str.equals(reversed);
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static String getIndex(){
        return "<!DOCTYPE html>\n" +
                "                <html>\n" +
                "                    <head>\n" +
                "                        <title>Form Example</title>\n" +
                "                        <meta charset=\"UTF-8\">\n" +
                "                        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "                    </head>\n" +
                "                    <body>\n" +
                "                        <h1>Profe pongame 5</h1>\n" +
                "                        <h2>Coseno: </h2>\n" +
                "                        <form action=\"/hello\">\n" +
                "                            <label for=\"name\">Ingresa el valor del coseno</label><br>\n" +
                "                            <input type=\"text\" id=\"cos\" name=\"name\" value=\"0\"><br><br>\n" +
                "                            <input type=\"button\" value=\"Submit\" onclick=\"loadGetCos()\">\n" +
                "                        </form>\n" +
                "                        <div id=\"getrescos\"></div>\n" +
                "                        <script>\n" +
                "                            function loadGetCos() {\n" +
                "                                let nameVar = document.getElementById(\"cos\").value;\n" +
                "                                const xhttp = new XMLHttpRequest();\n" +
                "                                xhttp.onload = function() {\n" +
                "                                    document.getElementById(\"getrescos\").innerHTML =\n" +
                "                                    this.responseText;\n" +
                "                                }\n" +
                "                                xhttp.open(\"GET\", \"/cos/\"+nameVar);\n" +
                "                                xhttp.send();\n" +
                "                            }\n" +
                "                        </script>\n" +
                "                        <h2>Seno: </h2>\n" +
                "                        <form action=\"/hello\">\n" +
                "                            <label for=\"name\">Ingresa el valor del seno</label><br>\n" +
                "                            <input type=\"text\" id=\"sin\" name=\"name\" value=\"0\"><br><br>\n" +
                "                            <input type=\"button\" value=\"Submit\" onclick=\"loadGetSin()\">\n" +
                "                        </form>\n" +
                "                        <div id=\"getressin\"></div>\n" +
                "                        <script>\n" +
                "                            function loadGetSin() {\n" +
                "                                let nameVar = document.getElementById(\"sin\").value;\n" +
                "                                const xhttp = new XMLHttpRequest();\n" +
                "                                xhttp.onload = function() {\n" +
                "                                    document.getElementById(\"getressin\").innerHTML =\n" +
                "                                    this.responseText;\n" +
                "                                }\n" +
                "                                xhttp.open(\"GET\", \"/sin/\"+nameVar);\n" +
                "                                xhttp.send();\n" +
                "                            }\n" +
                "                        </script>\n" +
                "                        <h2>Palindrome: </h2>\n" +
                "                        <form action=\"/hello\">\n" +
                "                            <label for=\"name\">Ingresa una cadena y te digo si es palindrome</label><br>\n" +
                "                            <input type=\"text\" id=\"pal\" name=\"name\" value=\"aba\"><br><br>\n" +
                "                            <input type=\"button\" value=\"Submit\" onclick=\"loadGetPal()\">\n" +
                "                        </form>\n" +
                "                        <div id=\"getrespal\"></div>\n" +
                "                        <script>\n" +
                "                            function loadGetPal() {\n" +
                "                                let nameVar = document.getElementById(\"pal\").value;\n" +
                "                                const xhttp = new XMLHttpRequest();\n" +
                "                                xhttp.onload = function() {\n" +
                "                                    document.getElementById(\"getrespal\").innerHTML =\n" +
                "                                    this.responseText;\n" +
                "                                }\n" +
                "                                xhttp.open(\"GET\", \"/palindrome/\"+nameVar);\n" +
                "                                xhttp.send();\n" +
                "                            }\n" +
                "                        </script>\n" +
                "                        <h2>Magnitud: </h2>\n" +
                "                        <form action=\"/hello\">\n" +
                "                            <label for=\"name\">Ingresa el valor x del vector</label><br>\n" +
                "                            <input type=\"text\" id=\"x\" name=\"name\" value=\"1\"><br><br>\n" +
                "                            <label for=\"name\">Ingresa el valor y del vector</label><br>\n" +
                "                            <input type=\"text\" id=\"y\" name=\"name\" value=\"1\"><br><br>\n" +
                "                            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMag()\">\n" +
                "                        </form>\n" +
                "                        <div id=\"getresmag\"></div>\n" +
                "                        <script>\n" +
                "                            function loadGetMag() {\n" +
                "                                let x = document.getElementById(\"x\").value;\n" +
                "                                let y = document.getElementById(\"y\").value;\n" +
                "                                const xhttp = new XMLHttpRequest();\n" +
                "                                xhttp.onload = function() {\n" +
                "                                    document.getElementById(\"getresmag\").innerHTML =\n" +
                "                                    this.responseText;\n" +
                "                                }\n" +
                "                                xhttp.open(\"GET\", \"/magnitude?x=\"+x+\"&y=\"+y);\n" +
                "                                xhttp.send();\n" +
                "                            }\n" +
                "                        </script>\n" +
                "                    </body>\n" +
                "                </html>";
    }

}