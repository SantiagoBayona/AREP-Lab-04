package edu.escuelaing;

@Componente
public class Servicio {

    @GetMapping("/hello")
    public static String hola(String arg){
        return "Hola " + arg.split("=")[1];
    }

    @GetMapping("/hellopost")
    public static String post(String arg){
        return "hola post " + arg;
    }

    @RequestMapping(path = "/POJO")
    public static String index(String arg) {
        return "Example endpoint with POJOs";
    }

    @RequestMapping(path = "/helloreq")
    public static String request(String arg) {
        return "RequestMapping";
    }

    @RequestMapping(path = "/img.png")
    public static String imgPng(String arg) {
        return "/img.png";
    }

    @RequestMapping(path = "/imgg.png")
    public static String imgPng2(String arg) {
        return "/imgg.png";
    }

    @RequestMapping(path = "/index.html")
    public static String html(String arg) {
        return "/index.html";
    }

    @RequestMapping(path = "/app")
    public static String app(String arg) {
        return "/app";
    }
}
