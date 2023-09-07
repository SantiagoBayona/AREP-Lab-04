package edu.escuelaing;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ComponentLoader {

    static Map<String, Method> servicios = new HashMap<>();

    public static void main(String args[]) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        cargarComponentes(args);

        System.out.println(ejecutar("/hello", "?name=Santiago&sn=Bayona"));
        System.out.println(ejecutar("/hellopost", "?name=Santiago&sn=Bayona"));
    }

    public static String ejecutar(String ruta, String param) throws InvocationTargetException, IllegalAccessException {
        return servicios.get(ruta).invoke(null, param) + "";
    }

    public static void cargarComponentes(String[] args) throws ClassNotFoundException {
        for(String arg : args){
            Class c = Class.forName(arg);
            if(c.isAnnotationPresent(Componente.class)){
                Method[] methods = c.getDeclaredMethods();
                for(Method method : methods ){
                    if(method.isAnnotationPresent(GetMapping.class)){
                        //Extraer el valor del parámetro
                        String ruta = method.getAnnotation(GetMapping.class).value();
                        //Extraer el nombre del método
                        System.out.println("Cargando método " + method.getName());
                        System.out.println("En" + ruta);
                        //Crear la lista de tipos del método
                        servicios.put(ruta, method);
                        //Obtener el método
                        //Agregar el método a la tabla de objetos ejecutables
                    }
                }
            }
        }
    }

}
