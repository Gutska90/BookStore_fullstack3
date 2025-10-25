# 📚 Microservicio Biblioteca - Spring Boot

## 🎯 Descripción del Proyecto

Microservicio desarrollado en **Spring Boot** para la gestión de libros en una biblioteca, implementando operaciones CRUD completas con optimizaciones avanzadas.

## ✨ Características Principales

### 🔧 Funcionalidades Básicas (Requeridas)
- ✅ **CRUD Completo**: GET, POST, PUT, DELETE para libros
- ✅ **Entidad Book** con atributos: ID, título, autor, año de publicación, género
- ✅ **Conexión a Base de Datos** (Oracle/MySQL)
- ✅ **JPA/Hibernate** para persistencia
- ✅ **Controlador REST** para manejo de solicitudes HTTP

### 🚀 Optimizaciones Implementadas
- ✅ **Paginación automática** en todos los endpoints
- ✅ **DTOs optimizados** para separación de capas
- ✅ **Validaciones robustas** con mensajes personalizados
- ✅ **Manejo de errores global** con respuestas consistentes
- ✅ **Auditoría automática** (timestamps de creación/actualización)
- ✅ **Control de concurrencia** (versioning)
- ✅ **Caché** para mejor performance
- ✅ **Logging detallado** para debugging
- ✅ **Índices de base de datos** para optimización

### 🔍 Funcionalidades Avanzadas
- ✅ **Búsqueda por término** (título, autor, género)
- ✅ **Búsqueda por rango de años**
- ✅ **Endpoints de estadísticas** (autores, géneros)
- ✅ **Búsquedas case-insensitive**
- ✅ **Compatibilidad con endpoints originales**

## 🛠️ Tecnologías Utilizadas

- **Java 17+**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Hibernate**
- **MySQL/Oracle**
- **Maven**
- **Caffeine Cache**
- **Jakarta Validation**

## 📋 Requisitos del Sistema

- Java 17 o superior
- Maven 3.6+
- MySQL 8.0+ o Oracle Database
- Puerto 8080 disponible

## 🚀 Instalación y Configuración

### 1. Clonar el Repositorio
```bash
git clone https://github.com/Gutska90/BookStore_fullstack3.git
cd BookStore_fullstack3
```

### 2. Configurar Base de Datos

#### Opción A: MySQL
```bash
# Crear base de datos
mysql -u root -p < database-setup-mysql.sql
```

#### Opción B: Oracle
```bash
# Ejecutar script SQL
sqlplus usuario/password@servidor < database-setup.sql
```

### 3. Configurar Aplicación

Editar `src/main/resources/application.properties`:

```properties
# Para MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca_db
spring.datasource.username=root
spring.datasource.password=tu_password

# Para Oracle
spring.datasource.url=jdbc:oracle:thin:@//host:port/service
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
```

### 4. Ejecutar la Aplicación
```bash
mvn clean install
mvn spring-boot:run
```

## 📡 Endpoints de la API

### 🔍 Operaciones CRUD Básicas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/libros` | Obtener todos los libros (con paginación) |
| GET | `/api/libros/{id}` | Obtener libro por ID |
| POST | `/api/libros` | Crear nuevo libro |
| PUT | `/api/libros/{id}` | Actualizar libro existente |
| DELETE | `/api/libros/{id}` | Eliminar libro por ID |

### 🔍 Búsquedas Avanzadas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/libros/buscar?termino=...` | Búsqueda por término |
| GET | `/api/libros/rango-anios?inicio=...&fin=...` | Búsqueda por rango de años |
| GET | `/api/libros/autor/{autor}` | Búsqueda por autor |
| GET | `/api/libros/genero/{genero}` | Búsqueda por género |
| GET | `/api/libros/anio/{anio}` | Búsqueda por año |

### 📊 Endpoints de Estadísticas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/libros/autores` | Lista de todos los autores |
| GET | `/api/libros/generos` | Lista de todos los géneros |

## 📝 Ejemplos de Uso

### Crear un Libro
```bash
curl -X POST http://localhost:8080/api/libros \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "El Quijote",
    "autor": "Miguel de Cervantes",
    "anioPublicacion": 1605,
    "genero": "Novela"
  }'
```

### Buscar Libros
```bash
# Búsqueda por término
curl "http://localhost:8080/api/libros/buscar?termino=quijote"

# Búsqueda por rango de años
curl "http://localhost:8080/api/libros/rango-anios?inicio=1600&fin=1700"
```

## 📚 Documentación Adicional

- [DEMO-COMPLETA.md](DEMO-COMPLETA.md) - Demostración completa de funcionalidades
- [OPTIMIZACIONES.md](OPTIMIZACIONES.md) - Detalles de optimizaciones implementadas
- [GUIA-RAPIDA.md](GUIA-RAPIDA.md) - Guía rápida de configuración
- [SETUP-MYSQL.md](SETUP-MYSQL.md) - Configuración específica para MySQL

## 🧪 Testing

El microservicio incluye ejemplos de testing en `DEMO-COMPLETA.md` que demuestran:
- Operaciones CRUD completas
- Búsquedas avanzadas
- Manejo de errores
- Validaciones

## 📊 Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/biblioteca/microservicio/
│   │   ├── controller/     # Controladores REST
│   │   ├── service/        # Lógica de negocio
│   │   ├── repository/    # Acceso a datos
│   │   ├── entity/        # Entidades JPA
│   │   ├── dto/           # Data Transfer Objects
│   │   ├── mapper/        # Mappers entre entidades y DTOs
│   │   └── exception/     # Manejo de excepciones
│   └── resources/
│       └── application.properties
├── database-setup.sql      # Script para Oracle
├── database-setup-mysql.sql # Script para MySQL
└── pom.xml                # Configuración Maven
```

## 🎓 Actividad Académica

Este proyecto fue desarrollado como parte de la **Actividad Individual Formativa 1** del curso de Microservicios, cumpliendo y superando todos los requerimientos establecidos:

- ✅ Microservicio Spring Boot funcional
- ✅ Operaciones CRUD implementadas
- ✅ Conexión a base de datos Oracle/MySQL
- ✅ Uso de JPA para persistencia
- ✅ Controlador REST para manejo HTTP
- ✅ Documentación completa
- ✅ Código optimizado y listo para producción

## 👨‍💻 Autor

**Gustavo** - Estudiante de Desarrollo Full Stack

## 📄 Licencia

Este proyecto es parte de una actividad académica y está disponible para fines educativos.

---

**¡El microservicio está listo para usar y cumple todos los requerimientos académicos!** 🎉