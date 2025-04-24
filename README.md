# Proyecto Gestión de Compañías

Este proyecto implementa una API RESTful en Java con Spring Boot para administrar información de compañías, aplicaciones y versiones. Forma parte de una prueba técnica para un puesto de desarrollador senior y aplica buenas prácticas como la arquitectura hexagonal, patrones de diseño (Strategy) y principios de DDD.

## Requisitos

- JDK 17 o superior
- Gradle 7.0 o superior

## Tecnologías

- **Spring Boot**: Framework principal para la creación de la API.
- **Hibernate/JPA**: Persistencia de datos.
- **MapStruct**: Mapeo entre entidades y DTOs.
- **Lombok**: Reducción de boilerplate code.
- **PostgreSQL** o H2 (configurable): Base de datos.

## Instalación

1. Clona este repositorio:
   - git clone https://github.com/anfega154/tecnichal-test
   - cd tecnichal-test

2.	Asegúrate de tener JDK 17 y Gradle instalados.
3.	Para instalar las dependencias, ejecuta: ./gradlew build


## Ejecución

Para ejecutar el proyecto, usa el siguiente comando: ./gradlew bootRun

## Rutas de la API

Rutas de la API

CRUD básico para Compañías
•	GET /api/company
Lista todas las compañías.
```json
{
  "timestamp": "2025-04-24T16:32:10.109625Z",
  "data": [
    {
      "id": 2,
      "code": "C002",
      "name": "Empresa Dos",
      "description": "Servicios digitales"
    },
    {
      "id": 3,
      "code": "COMP123",
      "name": "Empresa Demo",
      "description": "Descripción de la empresa demo"
    },
    {
      "id": 1,
      "code": "C001",
      "name": "Empresa Uno",
      "description": "Software financiero"
    },
    {
      "id": 6,
      "code": "COMP1234",
      "name": "Empresa Demo",
      "description": "Descripción de la empresa demo"
    }
  ]
}
```

•	GET /api/company/{code}
Obtiene una compañía por codigo.

```json
{
  "timestamp": "2025-04-24T16:32:10.109625Z",
  "data": {
    "id": 2,
    "code": "C002",
    "name": "Empresa Dos",
    "description": "Servicios digitales"
  }
}
```

•	POST /api/company
Crea una nueva compañía.
```json
{
  "code": "COMP1234",
  "name": "Empresa Demo",
  "description": "Descripción de la empresa demo"
}
```

•	DELETE /api/company/{id}
Elimina una compañía por ID.

```json
{
  "timestamp": "2025-04-24T16:32:10.109625Z",
  "data": "Company successfully deleted"
}
```

Búsqueda personalizada
•	GET /api/company/{code}/versions
Retorna detalles de una compañía con su nombre, nombre de la app y versión actual.

```json
{
  "timestamp": "2025-04-24T16:32:10.109625Z",
  "data": [
    {
      "codeCompany": "C002",
      "nameCompany": "Empresa Dos",
      "appName": "ServiciosApp",
      "version": "v1.0"
    }
  ]
}
```

## Arquitectura y Diseño

El proyecto sigue una **Arquitectura Hexagonal (Ports & Adapters)** junto con principios de **Domain-Driven Design (DDD)**. Esto permite mantener un sistema desacoplado, fácil de extender y mantener.

### Distribución de capas (basado en tu estructura real)

#### 1. Dominio (`domain`)
- Contiene el núcleo del negocio, completamente independiente de infraestructura externa.
- **`model/`**: Entidades como `Company`, `Application`, `Version`, `VersionCompany`.
- **`repository/`**: Interfaces puras que definen los contratos de persistencia, como `CompanyRepository`.

#### 2. Aplicación (`application.service`)
- Contiene la lógica de negocio y orquesta los casos de uso.
- Servicios como `CompanyService`, basados en los contratos del dominio.
- Clase base `BaseService` con lógica reutilizable y soporte de logging.

#### 3. Adaptadores (`adapter.controller`)
- Controladores REST para exponer la lógica al exterior.
- `CompanyController` maneja las rutas HTTP para la entidad `Company`.
- Paquete `exceptions` centraliza el manejo de errores personalizados con `GlobalException` y respuestas uniformes como `ValidationErrorResponse`.

#### 4. Infraestructura (`infrastructure.repository`)
- Implementaciones concretas de los contratos definidos en el dominio.
- `JpaCompanyRepositoryAdapter`: implementación con JPA de `CompanyRepository`.



