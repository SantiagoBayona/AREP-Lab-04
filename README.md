# TALLER 3: TALLER DE ARQUITECTURAS DE SERVIDORES DE APLICACIONES, META PROTOCOLOS DE OBJETOS, PATRÓN IOC, REFLEXIÓN

Servidor Web (tipo Apache) en Java capaz de entregar páginas html e imágenes tipo PNG. Igualmente el servidor provee un framework IoC para la construcción de aplicaciones web a partir de POJOS.

### Prerrequisitos
- Java
- Maven

### Instalación

1. Clonar el repositorio

```
git clone https://github.com/SantiagoBayona/AREP-Lab-03
```

2. Dentro del directorio del proyecto lo construimos

```
mvn package
```

## Ejecución

1. Corremos el servidor

```
mvn exec:java -"Dexec.mainClass"="edu.escuelaing.HttpServer"
```

## - Linux/MacOs

```
java -cp target/classes edu.escuelaing.arep.ASE.app.main
```

2. Ingresamos a la página mediante esta URL en un navegador

```
https://localhost:35000
```

## Pruebas

Para probar el servidor debemos solicitarle alguno de los recursos

![src/main/resources/img/](readme/Carpeta.png)

```
https://localhost:35000/imgg.png
```

Al hacerlo vemos que el recurso solicitado carga en el navegador

![src/main/resources/img/](readme/Prueba.png)

![src/main/resources/img/](readme/index.png)


## Construido con

* Java
* Maven
* Git
