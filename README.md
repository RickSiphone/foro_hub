<div align="center">
    <h1>Challenge ONE - Java Back End - API Rest Foro Hub</h1>
</div>
 
 ## Descripción del proyecto
Este es el proyecto final para poder graduarme del Programa ONE (Oracle Next Education), el cual consta de una API Rest que puede ser consumida por distintos clientes para trabajar con una base de datos dedicada a publicaciones de dudas y respuestas a estas publicaciones.
Además de esto, cuenta con:
* Una implementación de seguridad para autenticar a los usuarios (Autenticación STATELESS) que intenten consumir la API y poder otorgarles un JSON Web Token con fecha de expiración para poder mandar peticiones de distintos tipos.
* Manejo de códigos de estados de respuesta HTTP.
* Documentación dinámica para entender como utilizar la API.
* Implementación de reglas de negocio propuestas para este Challenge.

 ## Índice
 
 * [Funciones](#Funciones)

 * [Demostración](#Demostración)
 
 * [Como acceder al proyecto](#Como-acceder-al-proyecto)
 
 * [Tecnologías utilizadas](#Tecnologías-utilizadas)
 

## :hammer:[Características de la aplicación y demostración](#Características-de-la-aplicación-y-demostración)
### [Funciones](#Funciones)
Tópicos (Publicaciones de dudas) - Ruta principal ("topicos")
* `registrarTopico`: Recibe como argumento un JSON para poder registrar el tópico en la base de datos

* `verTopicoEspecifico`: Se genera un endpoint de manera dinámica para cada tópico y al acceder a él se despliega información más detallada

* `listarTopicos`: Muestra todos los tópicos almacenados en la base de datos

* `actualizarTopico`: Se pueden modificar ciertos campos del tópico para actualizar su información mandando un JSON con los datos necesarios

* `eliminarTopico`: De una forma similar al de verTopicoEspecifico se puede eliminar algun tópico mandando su id

Respuestas - Ruta principal ("respuestas")

* Al igual que los tópicos, tiene los mismos endpoints para realizar lo mismo pero esta vez enfocado a las respuestas

### [Demostración](#Demostración)
En caso de no saber como consumir la API se puede visitar la documentación que la propia API ofrece, para ello debes ingresar al endpoint "/swagger-ui.html"

![image](https://github.com/user-attachments/assets/8121de9d-847c-4cb6-81c9-06d710e55c78)


**Paso 1. Iniciar la API**: Dependiendo del método que deseas seguir debes iniciar la API para poder enviar peticiones

**Paso 2. Interactuar con la API**: Primero se debe acceder a la ruta "http://localhost:8080/login" y enviar en un JSON el nombre de usuario y la contraseña para que la API te devuelva un JWT (token) el cual deberas agregar en el header de cada solicitud para que la API no la descarte.
Es importante recordar que este token dura 2 horas a partir del momento en que se generó. Para poder probar las solicitudes se puede utilizar Postman, Insomnia o en la propia Documentación dinámica mencionada anteriormente
![image](https://github.com/user-attachments/assets/090e1900-655e-4d0b-8258-d2b2641c00f7)


**Paso 3. Acceder a cualquiera de los endpoints disponibles para trabajar con la API y la base de datos**: Para esta demostración se listaran los tópicos.
![image](https://github.com/user-attachments/assets/b687dea3-4cbe-4474-840a-94322e56c5e8)

**Paso 4. Eliminar tópico**: Como se puede apreciar en la imagen anterior, hay un tópico diseñado para poder eliminarlo 
![image](https://github.com/user-attachments/assets/1f55478c-5e95-4044-b289-83995880436e)

Prueba de que se eliminó:
![image](https://github.com/user-attachments/assets/3aec3d82-d849-400d-a473-f47d96efcdeb)


**Paso 5. Ver información detallada de una respuesta**: Para finalizar la demostración, mostraré la información detallada de una respuesta dada a una publicación
![image](https://github.com/user-attachments/assets/d01cbea6-70f0-4a4a-b327-d7563f951d15)


## 👓[Como acceder al proyecto](#Como-acceder-al-proyecto)

**Paso 1. Descargar el proyecto**

**Paso 2. Descomprimir el proyecto**

A partir de aqui hay dos formas para ejecutar el proyecto

**Paso 3.1. Usando algún IDE como por ejemplo Intellij para poder abrir el proyecto y así poder ejecutarlo**
**Paso 3.2. Ejecutarlo desde la terminal**: Para hacer esto, hay 2 formas de ejecutar el JAR

***Paso 3.2.1. Sin indicar el perfil**: Basta con ejecutar el comando Java -jar forohub-0.0.1-SNAPSHOT.jar

***Paso 3.2.2. Indicando el perfil**: Para este proyecto solo está definido el perfil de producción, pero en caso de poner más perfiles solo lo sustituyes por el nombre de ese perfil. Poner el siguiente comando:
java -DDB_HOST=localhost -DDB_CHALLENGE=nombreDeLaBaseDeDatos -DDB_USER=nombreUsuarioDBMS -DDB_PASSWORD=contraseñaDBMS  -jar forohub-0.0.1-SNAPSHOT.jar –spring.profiles.active=prod


**Paso 4. LISTO!!**: La API ya se abrá montado dependiendo de la forma de ejecución que hayas seleccionado

NOTA: Es importante recordar que la base de datos ya debe haber sido creada antes de ejecutar la API para que esta ya solo tenga que crear las tablas necesarias.


## 🧑‍💻[Tecnologías utilizadas](#Tecnologías-utilizadas)

Para la realización de este proyecto se utilizó:
* Lenguaje de programación: Java
* JDK 17.0.10
* IDE IntelliJ IDEA Community Edition 2024.1.2

Dependencias utilizadas:
*	Spring Boot DevTools
*	Lombok
*	Spring Web
*	Spring Data JPA
*	MySQL Driver
*	Flyway Migration
*	Validation
*	Spring Security
*	JWT de auth0
