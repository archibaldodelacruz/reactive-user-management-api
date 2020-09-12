# reactive-user-management-api

Este proyecto implementa un servicio de autenticacion simple y basica. Utilizando el stack reactivo de spring (project reactor, webflux)

## Para ejecutar

Para construir el proyecto. 
```
gradlew build
```

Para arrancar el proyecto. 
```
java -Dspring.profiles.active=dev -Xms64m -Xmx256m -jar build/libs/reactive-user-management-api-0.0.1-SNAPSHOT.jar
```