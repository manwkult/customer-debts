# Proyecto Base Implementando Clean Architecture

## Antes de Iniciar

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por ?ltimo el inicio y configuraci?n de la aplicaci?n.

## Infrastructure

### Helpers
En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

Estas utilidades no est?n arraigadas a objetos concretos, se realiza el uso de generics para modelar comportamientos gen?ricos de los diferentes objetos de persistencia que puedan existir, este tipo de implementaciones se realizan basadas en el patr?n de dise?o [Unit of Work y Repository](https://medium.com/@krzychukosobudzki/repository-design-pattern-bc490b256006) 

Estas clases no puede existir solas y debe heredarse su compartimiento en los **Driven Adapters**

## Driven Adapters
Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest, soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos interactuar.

## Entry Points
Los entry points representan los puntos de entrada de la aplicaci?n o el inicio de los flujos de negocio.