# 🏗️ Arquitectura del Microservicio

## Diagrama de Capas

```
┌─────────────────────────────────────────────────────────────┐
│                     Cliente HTTP                             │
│            (Postman, cURL, Navegador, etc.)                 │
└──────────────────────┬──────────────────────────────────────┘
                       │ HTTP Requests
                       ▼
┌─────────────────────────────────────────────────────────────┐
│                   CAPA DE CONTROLADOR                        │
│                    BookController.java                       │
│                  (@RestController)                           │
│                                                              │
│  • GET    /api/libros                                       │
│  • GET    /api/libros/{id}                                  │
│  • POST   /api/libros                                       │
│  • PUT    /api/libros/{id}                                  │
│  • DELETE /api/libros/{id}                                  │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────────────┐
│                   CAPA DE SERVICIO                           │
│                    BookService.java                          │
│                     (@Service)                               │
│                                                              │
│  • Lógica de negocio                                        │
│  • Validaciones adicionales                                 │
│  • Transformaciones de datos                                │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────────────┐
│                  CAPA DE REPOSITORIO                         │
│                   BookRepository.java                        │
│                    (@Repository)                             │
│              (Extiende JpaRepository)                        │
│                                                              │
│  • findAll()                                                │
│  • findById()                                               │
│  • save()                                                   │
│  • deleteById()                                             │
│  • findByAutor()                                            │
│  • findByGenero()                                           │
└──────────────────────┬──────────────────────────────────────┘
                       │ JPA/Hibernate
                       ▼
┌─────────────────────────────────────────────────────────────┐
│                      ENTIDAD                                 │
│                     Book.java                                │
│                     (@Entity)                                │
│                                                              │
│  • id (Long)                                                │
│  • titulo (String)                                          │
│  • autor (String)                                           │
│  • anioPublicacion (Integer)                                │
│  • genero (String)                                          │
└──────────────────────┬──────────────────────────────────────┘
                       │ JDBC
                       ▼
┌─────────────────────────────────────────────────────────────┐
│               BASE DE DATOS ORACLE CLOUD                     │
│                     Tabla: BOOKS                             │
└─────────────────────────────────────────────────────────────┘
```

## Flujo de una Petición

### Ejemplo: Crear un nuevo libro (POST)

```
1. Cliente → POST /api/libros
   Body: { "titulo": "...", "autor": "...", ... }

2. BookController.crearLibro()
   ├─ Valida el request (@Valid)
   └─ Llama al servicio

3. BookService.crearLibro()
   ├─ Aplica lógica de negocio
   └─ Llama al repositorio

4. BookRepository.save()
   ├─ JPA/Hibernate traduce a SQL
   └─ Ejecuta INSERT en Oracle

5. Oracle Database
   ├─ Inserta registro en tabla BOOKS
   └─ Retorna el ID generado

6. Respuesta fluye de vuelta:
   Database → Repository → Service → Controller → Cliente
```

## Componentes Principales

### 1. **Book.java** (Entidad)
- Representa la tabla `BOOKS` en la base de datos
- Anotaciones JPA para mapeo objeto-relacional
- Validaciones con `@NotBlank`, `@NotNull`

### 2. **BookRepository.java** (Repositorio)
- Interfaz que extiende `JpaRepository`
- Proporciona métodos CRUD automáticamente
- Métodos personalizados para búsquedas específicas

### 3. **BookService.java** (Servicio)
- Lógica de negocio
- Manejo de transacciones (`@Transactional`)
- Intermediario entre controlador y repositorio

### 4. **BookController.java** (Controlador REST)
- Expone endpoints HTTP
- Maneja requests y responses
- Validación de entrada
- Códigos de estado HTTP apropiados

### 5. **GlobalExceptionHandler.java** (Manejo de Errores)
- Captura excepciones globalmente
- Respuestas de error consistentes
- Manejo de validaciones

## Tecnologías y Frameworks

```
┌──────────────────────────────────────────────┐
│          Spring Boot 3.2.0                   │
│                                              │
│  ┌────────────┐  ┌─────────────┐           │
│  │ Spring Web │  │ Spring Data │           │
│  │    MVC     │  │     JPA     │           │
│  └────────────┘  └─────────────┘           │
│                                              │
│  ┌────────────┐  ┌─────────────┐           │
│  │ Hibernate  │  │  Validation │           │
│  │    ORM     │  │     API     │           │
│  └────────────┘  └─────────────┘           │
└──────────────────────────────────────────────┘
               │
               ▼
      ┌─────────────────┐
      │  Oracle JDBC    │
      │     Driver      │
      └─────────────────┘
               │
               ▼
      ┌─────────────────┐
      │ Oracle Database │
      │   (Cloud)       │
      └─────────────────┘
```

## Patrones de Diseño Utilizados

### 1. **MVC (Model-View-Controller)**
- **Model**: `Book.java` (entidad)
- **View**: Respuestas JSON
- **Controller**: `BookController.java`

### 2. **Repository Pattern**
- Abstracción de la capa de datos
- `BookRepository` separa lógica de persistencia

### 3. **Service Layer Pattern**
- `BookService` encapsula lógica de negocio
- Desacopla controlador de repositorio

### 4. **Dependency Injection**
- Spring gestiona dependencias automáticamente
- Uso de `@Autowired` en constructores

### 5. **DTO Pattern** (implícito)
- Entidad `Book` actúa como DTO
- En proyectos más grandes se separarían

## Características de Seguridad y Calidad

### ✅ Validaciones
- Validación de entrada con Bean Validation
- Manejo de errores con `GlobalExceptionHandler`

### ✅ Transaccionalidad
- `@Transactional` en capa de servicio
- Rollback automático en caso de error

### ✅ Separación de Responsabilidades
- Cada capa tiene una responsabilidad única
- Código mantenible y escalable

### ✅ RESTful API
- Verbos HTTP correctos (GET, POST, PUT, DELETE)
- Códigos de estado apropiados
- Estructura de URLs clara

## Posibles Mejoras Futuras

### 🔐 Seguridad
```java
// Implementar Spring Security
@EnableWebSecurity
public class SecurityConfig { ... }
```

### 📝 Documentación API
```xml
<!-- Swagger/OpenAPI -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
</dependency>
```

### 🧪 Testing
```java
// Tests unitarios y de integración
@SpringBootTest
public class BookServiceTest { ... }
```

### 📊 Métricas y Monitoreo
```xml
<!-- Spring Boot Actuator -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

### 🐳 Containerización
```dockerfile
# Dockerfile
FROM openjdk:17-jdk-slim
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

## Conclusión

Este microservicio sigue las mejores prácticas de desarrollo con Spring Boot:
- ✅ Arquitectura en capas clara
- ✅ Separación de responsabilidades
- ✅ Código limpio y mantenible
- ✅ API RESTful estándar
- ✅ Manejo robusto de errores
- ✅ Uso de JPA para persistencia

