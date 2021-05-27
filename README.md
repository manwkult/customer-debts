# Proyecto Base Implementando Clean Architecture

## Antes de Iniciar

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por Último el inicio y configuración de la aplicación.

## Infrastructure

### Helpers
En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

### Driven Adapters
Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest, soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos interactuar.

### Entry Points
Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## _Domain-Driven Design_

![All text](https://miro.medium.com/max/1718/1*yR4C1B-YfMh5zqpbHzTyag.png "Title")

## Infrastructura [Kubernetes](http://138.197.231.63/api/v1/swagger-ui.html "Heading link")

[![Kubernetes](https://cdn.filestackcontent.com/RlUuJIVESsOwxSF6qcD9?auto=compress,format)](http://138.197.231.63/api/v1/swagger-ui.html)
