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
docker run -d -p 37000:6000 --name lab5 dano1214/lab5arep  
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

## Version

1.0-SNAPSHOT

## Autores

Daniel Sebastián Ochoa Urrego - [DanielOchoa1214](https://github.com/DanielOchoa1214)

## Licencia

GNU General Public License family

## Diseño

En la aplicación separamos las clases de manera lógica usando los paquetes para abstraer la arquitectura dada en el enunciado. Según este tenemos que crear 3 componentes, los cuales fueron creados de la siguiente manera: 

* Web Client - Para separar lógicamente el cliente web creamos un paquete "webclient" donde tenemos la clase HttpServer, la cual es la encargada de administrar las peticiones del cliente y enviarle las respuestas a travez de los Sockets.
* Web Server Facade - Ahora, para este componente se creo el paquete "apifacade" en donde tenemos las clases HttpConnection y Cache, las cuales en conjunto son las escargadas de consultar la información de la película buscada, ya sea haciendo una petición a la API esterna o con la información guardada en cache.
* Concurrent Java Test Client - Para este componente usamos la estructura por defecto que Maven nos provee para hacer tests, por lo que si navegamos hasta el final de la carpeta test del proyecto podremos encontrar la clase de prueba de la fachada en donde probramos la correctitud de la información buscada por la API externa y la resistencia a la concurrencia del cache.

### Arquitectura

Como se especificó anteriormente, a grandes rasgos creamos la arquitectura separando lógicamente los componentes en paquetes, y en este laboratorio se mantuvo ese patrón, por lo que para ser capaces de enviar respuestas leyendo archivos se crearon los siguientes paquetes dentro del Web Client:

* File Handlers - Para este componente creamos el paquete "filehandlers" para poder responder acordemente a las peticiones hechas por el cliente encargado de leer los archivos que se le pida y devolverlos, ya sean archivos de texto, imágenes o mensajes de error.
* REST Handler - Para este componente creamos el paquete "resthandler" que es el encargado de enrutar con nuestro "Backend" (que sería la fachada) las peticiones REST que se le hagan a nuestro servidor, en este caso la búsqueda de películas.
* MiniSpring - Para este componente creamos el paquete "springrestclient" donde dejamos toda la lógica necesaria para guardar funcionalidades en el código para luego buscarlas y correrlas cuando sea necesario

### Extencibilidad

Para ilustrar la extencibilidad de la aplicación se me ocurren 2 ejemplos:

* Si se quisiera cambiar el proveedor externo de información sobre las películas, solo deberiamos cambiar el valor de la variable "GET_URL" de la clase HttpConnection y todo deberia seguir funcionando normalmente
* Si se quisieran añadir funcionalidades a la fachada solo se deberia crear un nuevo metodo dentro de la clase HttpConnection siguiendo los patrones de nombramiento con los verbos HTTP y tomar en cuenta el nuevo path dentro del cliente web para correr cada método según se necesite.

### Patrones

Se puede observar un patrón de diseño y un patrón que arquitectura

* El patrón de diseño corresponde al patrón Singleton, el cual se implementó, ya que dentro de la aplicación se debe tener (por ahora) solo un cache en donde guardemos las películas
* El patrón de arquitectura seria la implementación a bajo nivel de un MOM, ya que nuestra fachada sirve en resumidas cuentas como un intermediario que hace peticiones a la API externa y construye la respuesta de manera que sea mas fácilmente legible por el cliente Web 

### Modularización

En la aplicación cada clase tiene una funcion en especifico 

* HttpServer: Es el Cliente Web, el encargado de manejar los Sockets, las peticiones recividas y acorde a ellas dar la respuesta apropiada al navegador
* HttpConnection: Es la clase principal de la fachada, es la encargada de hacer las peticiones al API externa y transformar la respuesta en un objeto mas facilmente manejable por el cliente web.
* Cache: Es la clase encargada de simular un cache en la fachada, este se encarga de guardar y obtener una película en él, además de mirar si la película ya ha sido buscada antes.
  
## Agradecimientos

* A nuestro querido profesor de Arquitectura empresariales Daniel Benavides
* Figo

