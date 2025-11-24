# Prueba Técnica - Arquitectura de Microservicios

## Descripción General

Este proyecto presenta una prueba técnica destinada a evaluar habilidades en **desarrollo Java** con enfoque en **Spring Boot** y microservicios.  
Se aplican buenas prácticas, arquitectura limpia, programación reactiva con **Spring WebFlux** y manejo de logs, excepciones y pruebas.

La solución consta de **dos microservicios**:

1. **Microservicio de Customer**  
   - Arquitectura por capas: Controller, Service, Repository.  
   - Maneja clientes (CRUD).  
   - Base de datos: `customerdb` (SQL Server).

2. **Microservicio de Account & Movements**  
   - Arquitectura **Hexagonal / Limpia**: Dominio, Aplicación, Adaptadores.  
   - Maneja cuentas y movimientos.  
   - Base de datos: `accountdb` (SQL Server).  
   - Se comunica con Customer Microservice para validar la existencia del cliente.

---

## Tecnologías y Herramientas

- Java 21
- Spring Boot 4
- Spring WebFlux
- JPA / Hibernate
- SQL Server
- Lombok
- Docker Compose
- Postman
- JUnit
- Programación reactiva (Flux, Mono)

---

## Arquitectura

### Microservicio Customer
- Arquitectura por capas:
  - Controller
  - Service
  - Repository
- CRUD para **Customer**

### Microservicio Account & Movements
- Arquitectura Hexagonal / Limpia:
  - **Domain**: Entidades, reglas de negocio.
  - **Application**: Casos de uso y puertos (interfaces).
  - **Adapter**: Controladores, persistencia, adaptadores de APIs externas.
- Lógica de negocio:
  - Validación de movimientos (valor > 0, saldo disponible, crédito/débito).
  - Generación de reportes por cuenta y cliente.
  - Conexión entre microservicios para validar existencia del Cliente al momento de crear Cuentas.
  - Validación de existencia de una cuenta antes de realizar un movimiento.

---
##Iniciar la Base de datos
  - Para simplicidad se usó docker-compose para crear la base de datos SQL Server mediante este link [Yaml Files](https://github.com/DavidUyaguariJ/yaml-files.git)
  - Archivo para levantar el docker-compose nombrado: docker-compose-sqlserver.yaml

## Endpoints del API

### Customer Microservice
- `/api/v1/customers` – CRUD de clientes.

### Account & Movements Microservice
- `/api/v1/accounts` – CRUD de cuentas.
- `/api/v1/movements` – CRUD de movimientos.
- `/api/v1/movements/reports/{client-id}?startDate=yyyy-MM-dd&endDate=yyyy-MM-dd` – Reporte de estado de cuenta (JSON).

---

## Configuración de la Base de Datos

- Bases de datos **SQL Server**: `customerdb` y `accountdb`.  
- Las credenciales y URL de conexión **no están hardcodeadas**. Se utilizan **variables de entorno** en `application.properties`:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
