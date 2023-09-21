package edu.escuelaing;

@Componente
public class Servicio {

    @GetMapping("/hello")
    public static String hola(String arg){
        return "Hola " + arg;
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
}
