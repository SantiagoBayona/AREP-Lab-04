package edu.escuelaing;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;


public class HttpServer {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String inputLine = null;
            String outputLine = "";

            boolean firstLine = true;
            String uriString = "";

            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                if (firstLine) {
                    firstLine = false;
                    uriString = inputLine.split(" ")[1];
                }
                if (!in.ready()) {
                    break;
                }
            }
            System.out.println("URI: " + uriString);
            String responseBody = "";

            ComponentLoader.cargarComponentes(new String[]{"edu.escuelaing.Servicio"});

            if (uriString.startsWith("/hello?")) {
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: application/json\r\n"
                        + "\r\n"
                        + ComponentLoader.ejecutar("/hello", uriString);
            }else if (uriString.startsWith("/hellopost")){
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: application/json\r\n"
                        + "\r\n"
                        + ComponentLoader.ejecutar("/hellopost", uriString);
            }else if (uriString.startsWith("/POJO")){
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: application/json\r\n"
                        + "\r\n"
                        + ComponentLoader.ejecutar("/POJO", uriString);
            }else if (uriString.startsWith("/helloreq")){
                outputLine = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: application/json\r\n"
                        + "\r\n"
                        + ComponentLoader.ejecutar("/helloreq", uriString);
            }else if (uriString.startsWith("/img.png") || uriString.startsWith("/imgg.png") || uriString.startsWith("/index.html")){
                outputLine = searchFile(uriString, responseBody, outputLine, clientSocket);
            }else if (uriString.startsWith("/app")){
                outputLine = app();
            }else {
                outputLine = getIndexResponse();
            }
            out.println(outputLine);

            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    private static String searchFile(String path, String responseBody, String outputLine, Socket clientSocket) throws IOException {
        if (path != null && !getFile(path).equals("Not Found")) {
            responseBody = getFile(path);
            outputLine = getLine(responseBody);
        } else if (path != null && path.split("\\.")[1].equals("jpg") || path.split("\\.")[1].equals("png")) {
            OutputStream outputStream = clientSocket.getOutputStream();
            File file = new File("src/main/resources/img/" + path);
            BufferedImage bufferedImage = ImageIO.read(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            ImageIO.write(bufferedImage, path.split("\\.")[1], byteArrayOutputStream);
            outputLine = getImg("");
            dataOutputStream.writeBytes(outputLine);
            dataOutputStream.write(byteArrayOutputStream.toByteArray());
            System.out.println(outputLine);
        }
        return outputLine;
    }

    public static String getFile(String route) {
        Path file = FileSystems.getDefault().getPath("src/main/resources/img", route);
        Charset charset = Charset.forName("US-ASCII");
        String web = "";
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                web += line + "\n";
            }
        } catch (IOException x) {
            web = "Not Found";
        }
        return web;
    }
    private static String getImg(String responseBody) {
        System.out.println("response Body" + responseBody);
        return "HTTP/1.1 200 OK \r\n"
                + "Content-Type: image/png\r\n"
                + "\r\n";
    }

    public static String getLine(String responseBody) {
        return "HTTP/1.1 200 OK \r\n"
                + "Content-Type: text/html \r\n"
                + "\r\n"
                + "\n"
                + responseBody;
    }

    public static String getIndexResponse() {
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>Form Example</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <h1>Form with GET</h1>\n"
                + "        <form action=\"/hello\">\n"
                + "            <label for=\"name\">Name:</label><br>\n"
                + "            <input type=\"text\" id=\"name\" name=\"name\" value=\"John\"><br><br>\n"
                + "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n"
                + "        </form> \n"
                + "        <div id=\"getrespmsg\"></div>\n"
                + "\n"
                + "        <script>\n"
                + "            function loadGetMsg() {\n"
                + "                let nameVar = document.getElementById(\"name\").value;\n"
                + "                const xhttp = new XMLHttpRequest();\n"
                + "                xhttp.onload = function() {\n"
                + "                    document.getElementById(\"getrespmsg\").innerHTML =\n"
                + "                    this.responseText;\n"
                + "                }\n"
                + "                xhttp.open(\"GET\", \"/hello?name=\"+nameVar);\n"
                + "                xhttp.send();\n"
                + "            }\n"
                + "        </script>\n"
                + "\n"
                + "        <h1>Form with POST</h1>\n"
                + "        <form action=\"/hellopost\">\n"
                + "            <label for=\"postname\">Name:</label><br>\n"
                + "            <input type=\"text\" id=\"postname\" name=\"name\" value=\"John\"><br><br>\n"
                + "            <input type=\"button\" value=\"Submit\" onclick=\"loadPostMsg(postname)\">\n"
                + "        </form>\n"
                + "        \n"
                + "        <div id=\"postrespmsg\"></div>\n"
                + "        \n"
                + "        <script>\n"
                + "            function loadPostMsg(name){\n"
                + "                let url = \"/hellopost?name=\" + name.value;\n"
                + "\n"
                + "                fetch (url, {method: 'POST'})\n"
                + "                    .then(x => x.text())\n"
                + "                    .then(y => document.getElementById(\"postrespmsg\").innerHTML = y);\n"
                + "            }\n"
                + "        </script>\n"
                + "    </body>\n"
                + "</html>";
    }

    public static String app(){
        return  "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\n" +
                "\r\n" +
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Mi Página</title>\n" +
                "    <style>\n" +
                "        /* Estilos CSS para mejorar la apariencia */\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        header {\n" +
                "            background-color: #333;\n" +
                "            color: #fff;\n" +
                "            padding: 10px 0;\n" +
                "        }\n" +
                "        footer {\n" +
                "            background-color: #333;\n" +
                "            color: #fff;\n" +
                "            padding: 10px 0;\n" +
                "            position: absolute;\n" +
                "            bottom: 0;\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "        .container {\n" +
                "            margin: 50px auto;\n" +
                "            max-width: 400px;\n" +
                "            padding: 20px;\n" +
                "            border: 1px solid #ccc;\n" +
                "            border-radius: 5px;\n" +
                "        }\n" +
                "        h1 {\n" +
                "            font-size: 24px;\n" +
                "        }\n" +
                "        input[type=\"text\"] {\n" +
                "            width: 100%;\n" +
                "            padding: 10px;\n" +
                "            margin: 10px 0;\n" +
                "            border: 1px solid #ccc;\n" +
                "            border-radius: 5px;\n" +
                "        }\n" +
                "        button {\n" +
                "            background-color: #333;\n" +
                "            color: #fff;\n" +
                "            border: none;\n" +
                "            padding: 10px 20px;\n" +
                "            border-radius: 5px;\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <header>\n" +
                "        <h1>App de prueba</h1>\n" +
                "    </header>\n" +
                "    <div class=\"container\">\n" +
                "        <h2>Enviar Saludo</h2>\n" +
                "        <input type=\"text\" id=\"nombre\" placeholder=\"Escribe tu nombre\">\n" +
                "        <button onclick=\"enviarSaludo()\">Enviar</button>\n" +
                "        <p id=\"resultado\"></p>\n" +
                "    </div>\n" +
                "    <footer>\n" +
                "        App de prueba\n" +
                "    </footer>\n" +
                "\n" +
                "    <script>\n" +
                "        function enviarSaludo() {\n" +
                "            var nombre = document.getElementById(\"nombre\").value;\n" +
                "            if (nombre.trim() !== \"\") {\n" +
                "                nombre = encodeURIComponent(nombre);\n" +
                "                fetch('/hello?arg=' + nombre)\n" +
                "                    .then(response => response.text())\n" +
                "                    .then(data => {\n" +
                "                        document.getElementById(\"resultado\").textContent = data;\n" +
                "                    })\n" +
                "                    .catch(error => {\n" +
                "                        console.error('Error:', error);\n" +
                "                    });\n" +
                "            }\n" +
                "        }\n" +
                "    </script>\n" +
                "</body>\n" +
                "</html>\n";
    }
}