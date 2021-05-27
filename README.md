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

## Domain

### Model

### UseCase

## Applications

### App Service

## Domain-Driven Design - Hexagonal

![All text](https://miro.medium.com/max/1718/1*yR4C1B-YfMh5zqpbHzTyag.png "Domain-Driven Design - Hexagonal")

## CI / CD - Github Actions

### Github Actions para el CI / CD

En el aplicativo se creo una pipeline de github actions que corre cuando se realiza un merge con la rama main.

El pipeline genera el build del proyecto, la imagen de docker y la sube al container registry de Dockerhub. Una vez realizado esto se reemplazan los datos de ambiente en los manifiestos .yaml de Kubernetes para posteriormente ser aplicados y desplegados en el cluster de Digital Ocean.

[Modulo Administrativo de Deudas de Clientes](http://138.197.231.63/api/v1/swagger-ui.html)

![](https://miro.medium.com/max/3404/1*k99_arb0x9B7LI4I5hhCPw.png)

## Infrastructura [Kubernetes](http://138.197.231.63/api/v1/swagger-ui.html "Modulo Administrativo de Deudas de Clientes")

[![Kubernetes](https://cdn.filestackcontent.com/RlUuJIVESsOwxSF6qcD9?auto=compress,format)](http://138.197.231.63/api/v1/swagger-ui.html)
