# Proyecto Base Implementando Clean Architecture

## Antes de Iniciar

Empezaremos por explicar los diferentes componentes del proyectos y partiremos de los componentes externos, continuando con los componentes core de negocio (dominio) y por Último el inicio y configuración de la aplicación.

# Arquitectura

![Clean Architecture](https://miro.medium.com/max/1400/1*ZdlHz8B0-qu9Y-QO3AXR_w.png)

## Domain

Es el módulo más interno de la arquitectura, pertenece a la capa del dominio y encapsula la lógica y reglas del negocio mediante modelos y entidades del dominio.

## Usecases

Este módulo gradle perteneciente a la capa del dominio, implementa los casos de uso del sistema, define lógica de aplicación y reacciona a las invocaciones desde el módulo de entry points, orquestando los flujos hacia el módulo de entities.

## Infrastructure

### Helpers

En el apartado de helpers tendremos utilidades generales para los Driven Adapters y Entry Points.

### Driven Adapters

Los driven adapter representan implementaciones externas a nuestro sistema, como lo son conexiones a servicios rest,
soap, bases de datos, lectura de archivos planos, y en concreto cualquier origen y fuente de datos con la que debamos
interactuar.

### Entry Points

Los entry points representan los puntos de entrada de la aplicación o el inicio de los flujos de negocio.

## CI / CD - Github Actions

### Github Actions para el CI / CD

![](https://miro.medium.com/max/3404/1*k99_arb0x9B7LI4I5hhCPw.png)

En el aplicativo se creo una pipeline de github actions que corre cuando se realiza un merge con la rama main.

El pipeline genera el build del proyecto, la imagen de docker y la sube al container registry de Dockerhub. Una vez realizado esto se reemplazan los datos de ambiente en los manifiestos .yaml de Kubernetes para posteriormente ser aplicados y desplegados en el cluster de Digital Ocean.

[Modulo Administrativo de Deudas de Clientes](http://138.197.231.63/api/v1/swagger-ui.html)

## Infraestructura sobre Kubernetes

[![Kubernetes](https://cdn.filestackcontent.com/RlUuJIVESsOwxSF6qcD9?auto=compress,format)](http://138.197.231.63/api/v1/swagger-ui.html)

### Cluster de Kubernetes en Digital Ocean

Se crea un cluster de kubernetes para el despliegue de los diferentes componentes de la API y se accede a la API a travez de un balancedor de carga que la misma nube provee

## Estructura de Directorio

```bash
├── applications
│   └── app-service
│       ├── build.gradle
│       └── src
│           ├── main
│           │   ├── java
│           │   │   └── co
│           │   │       └── com
│           │   │           └── evertec
│           │   │               ├── config
│           │   │               │   ├── SpringSecurityConfig.java
│           │   │               │   ├── SwaggerConfig.java
│           │   │               │   └── UseCaseConfig.java
│           │   │               └── MainApplication.java
│           │   └── resources
│           │       ├── application.yaml
│           │       ├── i18n
│           │       │   ├── messages_en.properties
│           │       │   └── messages.properties
│           │       └── log4j2.properties
│           └── test
│               ├── java
│               └── resources
├── build.gradle
├── deployment
│   ├── deployment-postgres.yaml
│   ├── deployment-redis.yaml
│   └── k8
│       ├── configmap.yaml
│       └── deployment.yaml
├── Dockerfile
├── domain
│   ├── model
│   │   ├── build.gradle
│   │   └── src
│   │       ├── main
│   │       │   └── java
│   │       │       └── co
│   │       │           └── com
│   │       │               └── evertec
│   │       │                   └── model
│   │       │                       ├── Authentication.java
│   │       │                       ├── common
│   │       │                       │   └── Constants.java
│   │       │                       ├── CustomerDebt.java
│   │       │                       ├── enumerator
│   │       │                       │   └── UserEnum.java
│   │       │                       ├── gateway
│   │       │                       │   ├── CustomerDebtGateway.java
│   │       │                       │   └── JWTGateway.java
│   │       │                       └── security
│   │       │                           ├── Authorities.java
│   │       │                           └── User.java
│   │       └── test
│   │           └── java
│   └── usecase
│       ├── build.gradle
│       └── src
│           ├── main
│           │   └── java
│           │       └── co
│           │           └── com
│           │               └── evertec
│           │                   └── usecase
│           │                       ├── AuthUseCase.java
│           │                       ├── CustomerDebtUseCase.java
│           │                       └── util
│           │                           └── Util.java
│           └── test
│               └── java
│                   └── co
│                       └── com
│                           └── evertec
│                               └── usecase
│                                   └── impl
│                                       └── CustomerDebtUseCaseTest.java
├── gradle
│   └── wrapper
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradle.properties
├── gradlew
├── gradlew.bat
├── infrastructure
│   ├── driven-adapters
│   │   └── postgres-repository
│   │       ├── build.gradle
│   │       └── src
│   │           ├── main
│   │           │   └── java
│   │           │       └── co
│   │           │           └── com
│   │           │               └── evertec
│   │           │                   └── postgres
│   │           │                       ├── adapter
│   │           │                       │   └── CustomerDebtRepositoryAdapter.java
│   │           │                       ├── dao
│   │           │                       │   └── CustomerDebtDAO.java
│   │           │                       ├── entity
│   │           │                       │   ├── audit
│   │           │                       │   │   └── Audit.java
│   │           │                       │   └── CustomerDebtEntity.java
│   │           │                       ├── exception
│   │           │                       │   └── RepositoryResponseEntityExceptionHandler.java
│   │           │                       └── service
│   │           │                           └── CustomerDebtService.java
│   │           └── test
│   │               └── java
│   │                   └── co
│   │                       └── com
│   │                           └── evertec
│   │                               └── postgres
│   │                                   ├── adapter
│   │                                   │   └── CustomerDebtRepositoryAdapterTest.java
│   │                                   └── service
│   │                                       └── CustomerDebtServiceTest.java
│   ├── entry-points
│   │   └── api-rest
│   │       ├── build.gradle
│   │       └── src
│   │           ├── main
│   │           │   └── java
│   │           │       └── co
│   │           │           └── com
│   │           │               └── evertec
│   │           │                   ├── apirest
│   │           │                   │   ├── AuthRestController.java
│   │           │                   │   ├── CustomerDebtRestController.java
│   │           │                   │   └── HealthRestController.java
│   │           │                   └── exception
│   │           │                       └── RestResponseEntityExceptionHandler.java
│   │           └── test
│   │               └── java
│   │                   └── co
│   │                       └── com
│   │                           └── evertec
│   │                               └── apirest
│   │                                   ├── CustomerDebtRestControllerTest.java
│   │                                   └── HealthRestControllerTest.java
│   ├── helpers
│   │   └── customer-debts-log
│   │       ├── build.gradle
│   │       └── src
│   │           └── main
│   │               └── java
│   │                   └── co
│   │                       └── com
│   │                           └── evertec
│   │                               └── CustomerDebtsLog.java
│   └── security
│       └── jwt-security
│           ├── build.gradle
│           └── src
│               └── main
│                   └── java
│                       └── co
│                           └── com
│                               └── evertec
│                                   ├── adapter
│                                   │   └── JWTAdapter.java
│                                   ├── entrypoint
│                                   │   └── JWTAuthorizationEntryPoint.java
│                                   ├── exception
│                                   │   └── JWTAuthenticationException.java
│                                   ├── filter
│                                   │   └── JWTAuthorizationFilter.java
│                                   ├── mixin
│                                   │   └── SimpleGrantedAuthorityMixin.java
│                                   └── services
│                                       ├── impl
│                                       │   └── JWTServiceImpl.java
│                                       └── JWTService.java
├── lombok.config
├── main.gradle
├── README.md
└── settings.gradle
```

## Arquitectura

 - spring boot
 - gradle
 - validations
 - i18n
 - redis
 - postgres
 - swagger
 - log4j2
 - jwt

## Datos

username: admin
password: admin
