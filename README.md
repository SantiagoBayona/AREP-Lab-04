# TALLER 4: TALLER DE ARQUITECTURAS DE SERVIDORES DE APLICACIONES, META PROTOCOLOS DE OBJETOS, PATRÓN IOC, REFLEXIÓN

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

## En Linux/MacOs

```
java -cp target/classes edu.escuelaing.arep.ASE.app.main
```

2. Ingresamos a la página mediante esta URL en un navegador

```
https://localhost:35000
```
![img 1](https://github.com/SantiagoBayona/AREP-Lab-04/assets/64861204/082a2c3c-2ed9-4fff-9201-8c91c592fcb7)

## Pruebas

Para probar el servidor debemos hacerle alguna petición

![img 2](https://github.com/SantiagoBayona/AREP-Lab-04/assets/64861204/ddecf8ad-5267-46b7-af59-200c0ebd15e7)

```
https://localhost:35000/imgg.png
```

Al hacerlo vemos que el recurso solicitado carga en el navegador

![img 3](https://github.com/SantiagoBayona/AREP-Lab-04/assets/64861204/e38d6b0d-9aec-4b0f-ba92-5d19b6a03b7c)

También le podemos hacer otor tipo de peticiones

```
https://localhost:35000/POJO
```

![img 4](https://github.com/SantiagoBayona/AREP-Lab-04/assets/64861204/42a7860b-3db7-4667-a090-be4fe0ee1803)

## Construido con

* Java
* Maven
* Git
