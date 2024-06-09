# Proyecto de Galaga y Persistencia de Datos

# Descripción General
Este repositorio contiene dos paquetes principales: GalagaGameProject y PersistenceProject.

# GalagaGameProject
El paquete GalagaGameProject incluye un juego desarrollado con tres niveles. Cada nivel presenta un número específico de enemigos y diferentes condiciones para avanzar al siguiente nivel. Los datos del juego, como el nombre de usuario, la puntuación, el nivel y la salud, se guardan y se envían a nuestro proyecto de persistencia, PersistenceProject.

# PersistenceProject
El paquete PersistenceProject está basado en Spring Boot y se encarga de almacenar y gestionar los datos del juego. Este paquete no solo guarda los datos, sino que también permite visualizarlos. Los datos se pueden visualizar tanto en MySQL como en una URL creada específicamente para este propósito.

# Visualización de Datos
Para visualizar los datos guardados, puedes acceder a la siguiente URL:
http://localhost:8080/api/game/getAllScores.

# Instrucciones
1. Iniciar el Servidor: Antes de acceder a la URL mencionada, asegúrate de que el servidor de Spring Boot (PersistenceProject) esté en funcionamiento.
2. Acceder a los Datos: Una vez que el servidor esté activo, puedes navegar a la URL para ver todos los puntajes y detalles almacenados.

# Instalación y Ejecución
1. Clona este repositorio en tu máquina local.
2. Navega a cada uno de los paquetes (GalagaGameProject y PersistenceProject) e instala las dependencias necesarias.
3. Ejecuta el servidor de Spring Boot en el paquete PersistenceProject.
4. Ejecuta el juego en el paquete GalagaGameProject.
