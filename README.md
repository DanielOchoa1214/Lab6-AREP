# Taller 5 AREP - Daniel Sebastian Ochoa Urrego

En este taller implementamos una aplicacion basica usando Spark Java para publicar servisios REST simples, ademas usamos Docker y DockerHub como metodo para contenerizar la aplicacion

## Iniciando

Estas instrucciones te ayudarán a tener una copia de este proyecto corriendo en tu máquina local, en donde podras hacer pruebas o desarrollar sobre él 

### Prerrequisitos

* Git 
* Java
* Maven
* Docker

Si aún no tienes instaladas estas tecnologias, los siguientes tutoriales te pueden ayudar

* Git: https://www.youtube.com/watch?v=4xqVv2lTo40
* Java: https://www.youtube.com/watch?v=BG2OSaxWX4E
* Maven: https://www.youtube.com/watch?v=1QfiyR_PWxU
* Docker: https://www.youtube.com/watch?v=ZO4KWQfUBBc

### Instalando el proyecto

Para hacer una copia local del proyecto, debes abrir tu terminal, dirigirte al directorio donde quieras que este el proyecto y usar el siguiente comando

```
git clone https://github.com/DanielOchoa1214/Lab4-AREP.git
```

Luego muevete al directorio creado y desde ahi ejecuta este comando

```
mvn exec:java
```

Ya que la aplicación haya iniciado, puedes dirigirte a tu navegador de preferencia y entrar en http://localhost:4567 para ver la app corriendo, en ella encontraras una muy bonita página que cree con mucho esfuerzo donde puedes realizar diversas operaciones 

![image](https://github.com/DanielOchoa1214/Lab5-AREP/assets/77862016/3fee9afa-760b-4109-aaea-3c8210324692)

Ademas, el laboratorio esta subido en DockerHub, con Docker instalado, podras correr la aplicacion con el siguiente comando

```
docker run -d -p 37000:6000 --name lab5 dano1214/lab5arepbono
```

## Corriendo los tests

### Test de integración

Para probar que el desarrollo de la aplicacion fura correcto se probo cada funcionalidad en ella corriendo, primero probamos el coseno

![image](https://github.com/DanielOchoa1214/Lab5-AREP/assets/77862016/9f7ab7b7-00d1-4901-affc-ba5436df622e)

Luego el seno

![image](https://github.com/DanielOchoa1214/Lab5-AREP/assets/77862016/8dbf0aa7-66df-4bc3-9595-8c46748277e6)

El palindrome

![image](https://github.com/DanielOchoa1214/Lab5-AREP/assets/77862016/0fcac65e-b20d-4446-bfef-eef75369b500)

Y el calcular la magnitud de un vector de 2 dimensiones

![image](https://github.com/DanielOchoa1214/Lab5-AREP/assets/77862016/7d49d562-650a-4b7c-b534-5da8bcddb00a)

## Documentacion

Para visualizar la documentación del proyecto solo debes correr el siguiente comando desde el directorio raiz del proyecto 

```
mvn javadoc:javadoc
```

Y en la siguiente ruta encontrarás el archivo index.html en donde si lo abres desde el navegador podras ver toda la documentación

```
./target/site/apidocs
```

## Construido con

* Amor
* [Maven](https://maven.apache.org/) - Administrador de dependencias
* [IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/) - IDE de desarrollo
* [Docker](https://www.docker.com/) - Software de contenedores

## Version

1.0-SNAPSHOT

## Autores

Daniel Sebastián Ochoa Urrego - [DanielOchoa1214](https://github.com/DanielOchoa1214)

## Licencia

GNU General Public License family

## Diseño

En terminos de diseño el proyecto es muy basico, simplemente tiene una clase que mapea ciertos metodos a unos endpoints y en cada uno de ellos responde con diferentes funcionalidades

## Arquitectura

En terminos de componentes, en este proyecto podria verse como si tubiera 2, el contenedor y el computador fisico, en donde el contenedor corre corre la aplicacion dentro del computador en un entorno independiente y se mapean puertos del PC fisico al del contenedor, graficamente podria verse de la siguiente manera



## Como crear imagenes y subirlas a DockerHub

En este laboratorio diseñamos una aplicacion muy simple en Java la cual con ayuda de la libreria Spark pubilcamos metodos a traves de peticiones REST. Hasta ese punto no hay nada nuevo respecto a lo hecho anteriormente, lo que cambia en este laboratorio, es que aprenderendimos a usar Docker para correr nuestra aplicacion desde un contenedor ademas como publicarlo en DockerHub para poder tener el contendor desde cualquier cliente con Docker instalado. 

Para ello lo primero que hicimos despues de terminar la aplicacion fue crear el siguiente archivo en donde definiriamos la estructura y propiedades de nuestro contenedor

<img width="609" alt="Screenshot 2023-09-21 at 5 35 14 PM" src="https://github.com/DanielOchoa1214/Lab5-AREP/assets/77862016/adbac42f-4af6-4688-9a68-281b60529709">

En donde cada linea corremos los siguientes comandos:

FROM: Toma de base la imagen openjdk:17 en nuestro contenedor, esto para no tener que instalar Java desde 0
WORKDIR: Assigna el path base del disco duro virtual de nuestro contenedor 
COPY: Copia los contenidos de la primera carpeta de nuestro computador al directorio de la imagen del contenedor
CMD: Corre el comando especificado en la lista, cada entrada en la lista es una porcion del comando separado por espacion

Y luego con el siguiente comando creamos la imagen con las especificaciones del archivo Dockerfile

```
docker build --tag lab5arep .
```

Y para verificar que la imagen se haya creado correctamente usamos el siguiente comando

```
docker images
```

El cual muestra una tabla como la siguiente (Los valores de "REPOSITORY" deberia ser solo "lab5arep" en tu PC)

<img width="554" alt="Screenshot 2023-09-21 at 5 42 24 PM" src="https://github.com/DanielOchoa1214/Lab5-AREP/assets/77862016/34d3952f-6511-4fd3-971a-79f97c9181d7">

Y luego para crear un contenedor en base a la imagen usamos el siguiente comando

```
docker run -d -p [PUERTO]:6000 --name [NOMBRE] lab5arep
```

Donde reemplazamos con lo siguiente:

[PUERTO] : El puerto fisico de la maquina donde queremos que corra
[NOMBRE] : El nombre que le damos al contenedor

Ademas el comando se puede explicar por partes de la siguiente manera: 

-d Continua con la ejecucion del contenedor independientemente de la consola donde se corrio el comando 
-p Especificamos el puerto donde correra el servidor, en el ejemplo estamos mapeando uno cualquiera de nuestra maquina con el puerto 6000 del contenedor
--name Especificamos el nombre de nuestro contenedor

Y para revisar que todo estubiera corriendo de manera adecuado corrimos el siguiente comando 

```
docker ps
```

El cual deberia mostrar una tabla como la siguiente

<img width="1061" alt="Screenshot 2023-09-21 at 5 51 08 PM" src="https://github.com/DanielOchoa1214/Lab5-AREP/assets/77862016/b17967e7-5d13-4ec7-a68e-9fab2a211c78">

<hr>

Ademas vimos una manera diferente de crear los contenedores, y fue creandolos con un archivo .yml

<img width="505" alt="Screenshot 2023-09-21 at 5 55 32 PM" src="https://github.com/DanielOchoa1214/Lab5-AREP/assets/77862016/54d94c6c-624e-4e61-979c-e4a254df4ebb">

En donde creamos 2 contenedores, uno llamado web, que usara como base a nusestro archivo Dockerfile para crearse, y otro llamado db, el cual usara imagenes de DockerHub de mongodb para construirse

<hr>

Y por ultimo subimos las imagenes a DockerHub, para ello primero creamos un respositorio en su pagina web https://hub.docker.com/ 

Luego llamamos el siguiente comando para crear una referencia local al respositorio

```
docker tag [NombreLocal] [Nombre Repositorio]
```

Un ejemplo seria

```
docker tag lab5repo dano1214/lab5arepbono
```

Y luego solo debemos hacer el push, como ser haria en git, para ello primero iniciamos sesion con docker en la consola donde estemos con el siguiente comando

```
docker login
```

Y por ultimo hacemos el push

```
docker push [Nombre Imagen]:tag
```

Ejemplo

```
docker push dano1214/lab5arepbono:latest
```

Y si todo salio bien puedes en docker hub tu repositorio deberia verse algo asi

<img width="1277" alt="Screenshot 2023-09-21 at 6 07 07 PM" src="https://github.com/DanielOchoa1214/Lab5-AREP/assets/77862016/ba0523dd-0019-4d9c-94f7-3d3f32842ddd">

Ya llegando aca, solo usamos el comando presentado en la seccion de instalando para bajar la imagen del repositorio y correrla en nuestro computador

## Agradecimientos

* A nuestro querido profesor de Arquitectura empresariales Daniel Benavides
* Jorge, el mejor monitor 
* Figo

