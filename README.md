# TALLER 4: TALLER DE ARQUITECTURAS DE SERVIDORES DE APLICACIONES, META PROTOCOLOS DE OBJETOS, PATRÓN IOC, REFLEXIÓN

Servidor Web (tipo Apache) en Java capaz de entregar páginas html e imágenes tipo PNG. Igualmente el servidor provee un framework IoC para la construcción de aplicaciones web a partir de POJOS.

## Prerrequisitos
- Java
- Maven

## Instalación

1. Clonar el repositorio

```
git clone https://github.com/SantiagoBayona/AREP-Lab-04
```

2. Dentro del directorio del proyecto lo construimos

```
mvn clean package install
```

## Diseño y estructura

### @RequestMapping
Se definió la anotación RequestMapping con la que se marcarán los métodos que atiende el servicio web definidos en una clase de servicios

![image](https://github.com/SantiagoBayona/AREP-Lab-04/assets/64861204/67774d79-3545-466a-927c-62b8480abf10)

## Ejecución

1. Corremos el servidor

```
mvn exec:java -"Dexec.mainClass"="edu.escuelaing.HttpServer"
```

### En Linux/MacOs

```
java -cp target/classes edu.escuelaing.HttpServer
```

2. Ingresamos a la página mediante esta URL en un navegador

```
https://localhost:35000
```
![img 1](https://github.com/SantiagoBayona/AREP-Lab-04/assets/64861204/082a2c3c-2ed9-4fff-9201-8c91c592fcb7)

## Pruebas

Para probar el servidor debemos hacerle alguna petición

![img 2](https://github.com/SantiagoBayona/AREP-Lab-04/assets/64861204/ddecf8ad-5267-46b7-af59-200c0ebd15e7)

Por ejemplo, solicitarle una imagen

```
https://localhost:35000/imgg.png
```

Al hacerlo vemos que el recurso solicitado carga en el navegador

![img 3](https://github.com/SantiagoBayona/AREP-Lab-04/assets/64861204/e38d6b0d-9aec-4b0f-ba92-5d19b6a03b7c)

También le podemos hacer otro tipo de peticiones

```
https://localhost:35000/POJO
```

![img 4](https://github.com/SantiagoBayona/AREP-Lab-04/assets/64861204/42a7860b-3db7-4667-a090-be4fe0ee1803)

```
https://localhost:35000/index.html
```

![img 7](https://github.com/SantiagoBayona/AREP-Lab-04/assets/64861204/156cf8f2-1ff4-4102-8de4-6f9b9aa58718)

## Aplicación de prueba

Se construyó una aplicación sencilla usando uno de los métodos definidos en la construcción del framework

![img 6](https://github.com/SantiagoBayona/AREP-Lab-04/assets/64861204/152a3412-e0aa-46bc-8e3c-02ad24de7210)

## Construido con

* Java
* Maven
* Git
* HTML & CSS
* JS
