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
https://github.com/DanielOchoa1214/Lab6-AREP.git
```

Luego muevete al directorio creado y desde ahi ejecuta este comando

```
docker-compose up -d
```

Ya que la aplicación haya iniciado, puedes dirigirte a tu navegador de preferencia y entrar en http://localhost:8080 para ver la app corriendo, en ella encontraras una muy bonita página que cree con mucho esfuerzo donde puedes enviar los logs

<img width="451" alt="Screenshot 2023-09-29 at 7 06 37 PM" src="https://github.com/DanielOchoa1214/Lab6-AREP/assets/77862016/7c4f6db6-96d9-483e-9cb9-106b58f8e2b0">

## Corriendo los tests

### Test de integración

Para probar que el desarrollo de la aplicación fuera correcto sé probo cada funcionalidad en ella corriendo, para ello enviamos un log y verificamos que saliera junto con los ultimos creados

<img width="452" alt="Screenshot 2023-09-29 at 7 05 03 PM" src="https://github.com/DanielOchoa1214/Lab6-AREP/assets/77862016/9a3911db-bb6c-4e8b-8eac-e3476815547d">

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

Para simular la arquitectura especificada en el taller dentro de un mismo proyecto se separaron los componentes en paquetes diferentes, uno para los LogService y otro para el balanceador RoundRobin. Ademas, se tienen los archivos Dockerfile y docker-compose que especidica como se construiran los contenedores y que haran cada uno de ellos.

## Arquitectura

La arquitectura de este taller es presentada con el siguiente diagrama 

<img width="908" alt="Screenshot 2023-09-29 at 7 11 56 PM" src="https://github.com/DanielOchoa1214/Lab6-AREP/assets/77862016/010a84b3-0056-45d7-972d-21b2aceffbea">

En donde se especifican los siguientes componentes: 

Security Group: Grupo de seguridad de la instancia en AWS que lproteje los puertos de la maquina (firewal de nivel 1)
AWS-EC2: Instancia basica de una maquina virtual en la nube de Amazon
Docker Engine: Motor de los coontenedores instalado en la instancia
APP-LB-RoundRobin: Balanceador de cargas que implementa un algoritmo estatico de RoundRobin donde se rotan los servidorespor cada peticion
LogService: Servidor que almacenara en la base de datos el log, y devolvera los ultimos 10
MongoDB: Contenedor con la base de datos de mongo

## Como crear la imagenes y desplegar el proyecto


## Video



https://github.com/DanielOchoa1214/Lab6-AREP/assets/77862016/c95c1af3-9679-45ff-a306-4f50bff2e8b1



## Agradecimientos

* A nuestro querido profesor de Arquitectura empresariales Daniel Benavides
* Jorge, el mejor monitor 
* Figo

