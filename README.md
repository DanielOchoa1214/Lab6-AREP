# Taller 6 AREP - Daniel Sebastian Ochoa Urrego

En este taller implementaremos un servicio de logs con una arquitectura mas compleja que antes usando Docker y una instancia EC2 de AWS

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

Además, el laboratorio está subido en DockerHub, con Docker instalado, podrás correr la aplicación con el siguiente comando

```
docker run -d -p 37000:6000 --name lab5 dano1214/lab5arepbono
```

## Corriendo los tests

### Test de integración

Para probar que el desarrollo de la aplicación fuera correcto sé probo cada funcionalidad en ella corriendo, primero probamos el coseno

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

En términos de diseño el proyecto es muy básico, simplemente tiene una clase que mapea ciertos métodos a unos endpoints y en cada uno de ellos responde con diferentes funcionalidades

## Arquitectura

En términos de componentes, en este proyecto podría verse como si tuviera 2, el contenedor y el computador físico, en donde el contenedor corre la aplicación dentro del computador en un entorno independiente y se mapean puertos del PC físico al del contenedor, gráficamente podría verse de la siguiente manera

![WhatsApp Image 2023-09-21 at 6 17 08 PM](https://github.com/DanielOchoa1214/Lab5-AREP/assets/77862016/fafeaaf1-f327-4a8d-9041-803a41f85074)

## Como crear imagenes y subirlas a DockerHub

En este laboratorio diseñamos una aplicación muy simple en Java la cual con ayuda de la librería Spark publicamos métodos a través de peticiones REST. Hasta ese punto no hay nada nuevo respecto a lo hecho anteriormente, lo que cambia en este laboratorio, es que aprenderemos a usar Docker para correr nuestra aplicación desde un contenedor además como publicarlo en DockerHub para poder tener el contendor desde cualquier cliente con Docker instalado. 

Para ello lo primero que hicimos después de terminar la aplicación fue crear el siguiente archivo en donde definiríamos la estructura y propiedades de nuestro contenedor
<img width="609" alt="Screenshot 2023-09-21 at 5 35 14 PM" src="https://github.com/DanielOchoa1214/Lab5-AREP/assets/77862016/adbac42f-4af6-4688-9a68-281b60529709">

En donde cada línea corremos los siguientes comandos:

FROM: Toma de base la imagen openjdk:17 en nuestro contenedor, esto para no tener que instalar Java desde 0
WORKDIR: Asigna el path base del disco duro virtual de nuestro contenedor 
COPY: Copia los contenidos de la primera carpeta de nuestro computador al directorio de la imagen del contenedor
CMD: Corre el comando especificado en la lista, cada entrada en la lista es una porción del comando separado por espacion

Y luego con el siguiente comando creamos la imagen con las especificaciones del archivo Dockerfile

```
docker build --tag lab5arep .
```

Y para verificar que la imagen se haya creado correctamente usamos el siguiente comando

```
docker images
```

El cual muestra una tabla como la siguiente (Los valores de "REPOSITORY" debería ser solo "lab5arep" en tu PC)

<img width="554" alt="Screenshot 2023-09-21 at 5 42 24 PM" src="https://github.com/DanielOchoa1214/Lab5-AREP/assets/77862016/34d3952f-6511-4fd3-971a-79f97c9181d7">

Y luego para crear un contenedor con base en la imagen usamos el siguiente comando

```
docker run -d -p [PUERTO]:6000 --name [NOMBRE] lab5arep
```

Donde reemplazamos con lo siguiente:

[PUERTO] : El puerto físico de la máquina donde queremos que corra
[NOMBRE] : El nombre que le damos al contenedor

Además el comando se puede explicar por partes de la siguiente manera: 

-d Continua con la ejecución del contenedor independientemente de la consola donde se corrió el comando 
-p Especificamos el puerto donde correrá el servidor, en el ejemplo estamos mapeando uno cualquiera de nuestra maquina con el puerto 6000 del contenedor
--name Especificamos el nombre de nuestro contenedor

Y para revisar que todo estuviera corriendo de manera adecuada corrimos el siguiente comando

```
docker ps
```

El cual debería mostrar una tabla como la siguiente

<img width="1061" alt="Screenshot 2023-09-21 at 5 51 08 PM" src="https://github.com/DanielOchoa1214/Lab5-AREP/assets/77862016/b17967e7-5d13-4ec7-a68e-9fab2a211c78">

<hr>

Además vimos una manera diferente de crear los contenedores, y fue creándolos con un archivo .yml

<img width="505" alt="Screenshot 2023-09-21 at 5 55 32 PM" src="https://github.com/DanielOchoa1214/Lab5-AREP/assets/77862016/54d94c6c-624e-4e61-979c-e4a254df4ebb">

En donde creamos 2 contenedores, uno llamado web, que usara como base a nuestro archivo Dockerfile para crearse, y otro llamado db, el cual usara imágenes de DockerHub de mongodb para construirse

<hr>

Y por último subimos las imágenes a DockerHub, para ello primero creamos un repositorio en su página web https://hub.docker.com/ 

Luego llamamos el siguiente comando para crear una referencia local al repositorio

```
docker tag [NombreLocal] [Nombre Repositorio]
```

Un ejemplo sería

```
docker tag lab5repo dano1214/lab5arepbono
```

Y luego solo debemos hacer el push, como ser haría en git, para ello primero iniciamos sesión con docker en la consola donde estemos con el siguiente comando

```
docker login
```

Y por último hacemos el push

```
docker push [Nombre Imagen]:tag
```

Ejemplo

```
docker push dano1214/lab5arepbono:latest
```

Y si todo salió bien puedes en DockerHub tu repositorio debería verse algo así

<img width="1277" alt="Screenshot 2023-09-21 at 6 07 07 PM" src="https://github.com/DanielOchoa1214/Lab5-AREP/assets/77862016/ba0523dd-0019-4d9c-94f7-3d3f32842ddd">

Ya llegando acá, solo usamos el comando presentado en la sección de instalando para bajar la imagen del repositorio y correrla en nuestro computador

## Agradecimientos

* A nuestro querido profesor de Arquitectura empresariales Daniel Benavides
* Jorge, el mejor monitor 
* Figo

