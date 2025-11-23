# Arquetipo Maven - Microservicio Biblioteca

Este arquetipo Maven permite generar rápidamente un microservicio Spring Boot para gestión de biblioteca con Oracle Database y patrones de diseño implementados.

## Características

- ✅ Spring Boot 3.2.0
- ✅ Oracle Database (ojdbc8)
- ✅ Patrón Strategy para validaciones
- ✅ Patrón DTO para transferencia de datos
- ✅ Patrón Mapper para conversión de objetos
- ✅ Patrón Repository para acceso a datos
- ✅ CRUD completo (GET, POST, PUT, DELETE)
- ✅ Manejo global de excepciones
- ✅ Validaciones con Jakarta Validation

## Instalación del Arquetipo

El arquetipo ya está instalado en el repositorio local de Maven. Si necesitas reinstalarlo:

```bash
cd biblioteca-arquetipo
mvn clean install
```

## Uso del Arquetipo

### Generar un nuevo proyecto desde el arquetipo

```bash
mvn archetype:generate \
  -DarchetypeGroupId=com.biblioteca \
  -DarchetypeArtifactId=biblioteca-arquetipo \
  -DarchetypeVersion=1.0.0 \
  -DgroupId=com.biblioteca \
  -DartifactId=mi-microservicio-biblioteca \
  -Dversion=1.0.0 \
  -Dpackage=com.biblioteca.microservicio
```

O de forma interactiva:

```bash
mvn archetype:generate \
  -DarchetypeGroupId=com.biblioteca \
  -DarchetypeArtifactId=biblioteca-arquetipo \
  -DarchetypeVersion=1.0.0
```

### Parámetros del arquetipo

- `groupId`: Grupo del proyecto (ej: com.biblioteca)
- `artifactId`: Nombre del artefacto (ej: microservicio-biblioteca)
- `version`: Versión del proyecto (ej: 1.0.0)
- `package`: Paquete base Java (ej: com.biblioteca.microservicio)

## Estructura del Proyecto Generado

```
proyecto-generado/
├── pom.xml
├── src/
│   └── main/
│       ├── java/
│       │   └── com/biblioteca/microservicio/
│       │       ├── BibliotecaMicroservicioApplication.java
│       │       ├── controller/
│       │       │   └── BookController.java
│       │       ├── dto/
│       │       │   ├── BookResponse.java
│       │       │   ├── CreateBookRequest.java
│       │       │   └── UpdateBookRequest.java
│       │       ├── entity/
│       │       │   └── Book.java
│       │       ├── exception/
│       │       │   ├── BookNotFoundException.java
│       │       │   └── GlobalExceptionHandler.java
│       │       ├── mapper/
│       │       │   └── BookMapper.java
│       │       ├── repository/
│       │       │   └── BookRepository.java
│       │       ├── service/
│       │       │   └── BookService.java
│       │       └── strategy/
│       │           ├── ValidationStrategy.java
│       │           ├── TituloValidationStrategy.java
│       │           ├── AutorValidationStrategy.java
│       │           ├── AnioValidationStrategy.java
│       │           └── BookValidationContext.java
│       └── resources/
│           ├── application.properties
│           └── database-setup.sql
```

## Configuración

### 1. Configurar Base de Datos Oracle

Edita `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/XEPDB1
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD
```

### 2. Ejecutar Script de Base de Datos

Ejecuta el script SQL en Oracle:

```bash
sqlplus usuario/password@localhost:1521/XEPDB1 @src/main/resources/database-setup.sql
```

## Endpoints del API

- `GET /api/libros` - Obtener todos los libros
- `GET /api/libros/{id}` - Obtener un libro por ID
- `POST /api/libros` - Crear un nuevo libro
- `PUT /api/libros/{id}` - Actualizar un libro existente
- `DELETE /api/libros/{id}` - Eliminar un libro por ID

## Patrones de Diseño Implementados

### 1. Patrón Strategy
Implementado en el paquete `strategy` para validaciones flexibles:
- `ValidationStrategy`: Interfaz común
- `TituloValidationStrategy`: Valida títulos
- `AutorValidationStrategy`: Valida autores
- `AnioValidationStrategy`: Valida años
- `BookValidationContext`: Contexto que utiliza las estrategias

### 2. Patrón DTO
Separación entre entidades de dominio y objetos de transferencia:
- `BookResponse`: DTO de respuesta
- `CreateBookRequest`: DTO para crear
- `UpdateBookRequest`: DTO para actualizar

### 3. Patrón Mapper
Conversión entre entidades y DTOs:
- `BookMapper`: Convierte entre Book y DTOs

### 4. Patrón Repository
Abstracción del acceso a datos:
- `BookRepository`: Interfaz para operaciones CRUD

## Ejecutar el Proyecto

```bash
mvn spring-boot:run
```

El microservicio estará disponible en: `http://localhost:8080`

## Pruebas con Postman

Importa la colección de Postman incluida en el proyecto raíz para probar todos los endpoints.

## Versión

- Versión del Arquetipo: 1.0.0
- Spring Boot: 3.2.0
- Java: 17

