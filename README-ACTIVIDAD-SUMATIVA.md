# 🏥 Sistema de Gestión de Laboratorios y Resultados de Análisis

## 📋 Descripción del Proyecto

Sistema backend desarrollado en **Spring Boot** para la gestión de laboratorios clínicos y sus resultados de análisis. Este proyecto cumple con los requerimientos de la **Actividad Sumativa - Experiencia 1: Programando nuestro BackEnd**.

## ✅ Requerimientos Cumplidos

### 🔧 Funcionalidades Implementadas

1. **✅ Microservicio de Control de Usuarios**
   - CRUD completo (Crear, Leer, Actualizar, Eliminar)
   - Sistema de inicio de sesión con JWT
   - Gestión de roles (ADMINISTRADOR, TECNICO, PACIENTE)
   - Autenticación con BCrypt

2. **✅ Microservicio de Gestión de Resultados de Análisis**
   - Almacenamiento de resultados
   - Consulta de resultados con múltiples filtros
   - Asociación con usuarios y laboratorios
   - CRUD completo

3. **✅ Base de Datos Oracle**
   - Script SQL completo con tablas, secuencias e índices
   - Datos de prueba (mínimo 3 registros por tabla)
   - Relaciones entre tablas configuradas

4. **✅ API RESTful**
   - Endpoints GET, POST, PUT, DELETE implementados
   - Validaciones con Jakarta Validation
   - Manejo de errores global
   - Respuestas consistentes

## 🎯 Caso de Negocio: Gestión de Laboratorios y Resultados de Análisis

### Roles Implementados

1. **ADMINISTRADOR**
   - Control total del sistema
   - Gestión de usuarios y laboratorios
   - Acceso completo a resultados

2. **TÉCNICO**
   - Gestión de resultados de análisis
   - Consulta de datos
   - Acceso limitado

3. **PACIENTE**
   - Consulta de sus propios resultados
   - Acceso limitado

### Laboratorios Predefinidos (3)

1. **Laboratorio Clínico Central**
   - Especialidad: Análisis generales
   - Servicios: Hematología, Bioquímica, Microbiología

2. **Laboratorio de Patología**
   - Especialidad: Estudios histológicos
   - Servicios: Biopsias, Citologías, Anatomía patológica

3. **Laboratorio de Genética**
   - Especialidad: Análisis genéticos
   - Servicios: Pruebas genéticas, Cariotipos, ADN

## 🛠️ Tecnologías Utilizadas

- **Java 17+**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Spring Security**
- **Oracle Database**
- **JWT (JSON Web Tokens)**
- **BCrypt** (encriptación de contraseñas)
- **Maven**
- **Jakarta Validation**

## 📡 Endpoints de la API

### 🔐 Autenticación

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/auth/login` | Iniciar sesión |
| POST | `/api/auth/logout` | Cerrar sesión |

### 👥 Usuarios

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/usuarios` | Crear usuario |
| GET | `/api/usuarios` | Listar usuarios |
| GET | `/api/usuarios/{id}` | Obtener usuario por ID |
| PUT | `/api/usuarios/{id}` | Actualizar usuario |
| DELETE | `/api/usuarios/{id}` | Eliminar usuario |
| GET | `/api/usuarios/rol/{rol}` | Buscar por rol |

### 📊 Resultados de Análisis

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/api/resultados` | Crear resultado |
| GET | `/api/resultados` | Listar resultados (paginado) |
| GET | `/api/resultados/{id}` | Obtener resultado por ID |
| PUT | `/api/resultados/{id}` | Actualizar resultado |
| DELETE | `/api/resultados/{id}` | Eliminar resultado |
| GET | `/api/resultados/usuario/{usuarioId}` | Resultados de un usuario |
| GET | `/api/resultados/laboratorio/{labId}` | Resultados de un laboratorio |
| GET | `/api/resultados/estado/{estado}` | Resultados por estado |
| GET | `/api/resultados/tipo?tipo=...` | Resultados por tipo |
| GET | `/api/resultados/fechas?inicio=...&fin=...` | Resultados por rango de fechas |

## 🚀 Instalación y Configuración

### 1. Requisitos Previos

- Java 17 o superior
- Maven 3.6+
- Oracle Database 18c o superior
- Puerto 8080 disponible

### 2. Configurar Base de Datos Oracle

```bash
# Ejecutar script SQL
sqlplus usuario/password@servidor @database-setup-laboratorios-oracle.sql
```

O desde SQL Developer:
- Abrir `database-setup-laboratorios-oracle.sql`
- Ejecutar todo el script

### 3. Configurar Aplicación

Editar `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:oracle:thin:@//HOST:PORT/SERVICE_NAME
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD
```

### 4. Ejecutar la Aplicación

```bash
mvn clean install
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

## 📝 Datos de Prueba

### Usuarios

| Email | Contraseña | Rol |
|-------|------------|-----|
| admin@lab.com | admin123 | ADMINISTRADOR |
| tecnico@lab.com | tecnico123 | TECNICO |
| paciente@lab.com | paciente123 | PACIENTE |

### Laboratorios

1. Laboratorio Clínico Central
2. Laboratorio de Patología
3. Laboratorio de Genética

### Resultados

- 3 resultados de prueba incluidos en el script SQL

## 🧪 Testing con Postman

1. Importar la colección: `Laboratorios-API.postman_collection.json`
2. Configurar el entorno base: `http://localhost:8080`
3. Ejecutar las peticiones de prueba

### Flujo Recomendado de Pruebas

1. **Login**: Autenticar con usuario admin
2. **Crear Usuario**: Crear un nuevo usuario técnico
3. **Crear Resultado**: Crear un resultado de análisis
4. **Consultar Resultados**: Listar y filtrar resultados
5. **Actualizar Resultado**: Modificar un resultado existente
6. **Eliminar Resultado**: Eliminar un resultado

## 📊 Estructura del Proyecto

```
src/main/java/com/laboratorio/microservicio/
├── config/
│   └── SecurityConfig.java          # Configuración Spring Security
├── controller/
│   ├── AuthController.java          # Autenticación
│   ├── UsuarioController.java       # CRUD Usuarios
│   └── ResultadoAnalisisController.java  # CRUD Resultados
├── dto/
│   ├── CreateUsuarioRequest.java
│   ├── UsuarioResponse.java
│   ├── LoginRequest.java
│   ├── LoginResponse.java
│   ├── CreateResultadoAnalisisRequest.java
│   └── ResultadoAnalisisResponse.java
├── entity/
│   ├── Usuario.java
│   ├── Laboratorio.java
│   └── ResultadoAnalisis.java
├── exception/
│   ├── UsuarioNotFoundException.java
│   ├── LaboratorioNotFoundException.java
│   ├── ResultadoAnalisisNotFoundException.java
│   └── GlobalExceptionHandler.java
├── mapper/
│   ├── UsuarioMapper.java
│   └── ResultadoAnalisisMapper.java
├── repository/
│   ├── UsuarioRepository.java
│   ├── LaboratorioRepository.java
│   └── ResultadoAnalisisRepository.java
├── service/
│   ├── UsuarioService.java
│   └── ResultadoAnalisisService.java
├── util/
│   └── JwtUtil.java                 # Utilidades JWT
└── LaboratorioMicroservicioApplication.java
```

## 📚 Archivos de Configuración

- `database-setup-laboratorios-oracle.sql` - Script SQL para Oracle
- `application.properties` - Configuración de la aplicación
- `pom.xml` - Dependencias Maven
- `Laboratorios-API.postman_collection.json` - Colección Postman

## ✅ Checklist de Entregables

- [x] Código fuente del microservicio desarrollado
- [x] Script de base de datos Oracle
- [x] Al menos 3 registros en cada tabla
- [x] CRUD completo implementado
- [x] Controladores RESTful (GET, POST, PUT, DELETE)
- [x] Sistema de usuarios con roles
- [x] Colección Postman para pruebas
- [x] Documentación completa

## 🎓 Actividad Académica

**Actividad Sumativa - Experiencia 1**: "Programando nuestro BackEnd"

Este proyecto cumple con todos los requerimientos establecidos:
- ✅ Framework Spring Boot
- ✅ Repositorio Git
- ✅ Conexión a Oracle Database
- ✅ Controladores RESTful
- ✅ Mínimo 3 registros por tabla
- ✅ Validación con Postman

## 👨‍💻 Autor

**Gustavo** - Estudiante de Desarrollo Full Stack

## 📄 Licencia

Este proyecto es parte de una actividad académica y está disponible para fines educativos.

---

**¡Sistema completo y listo para entregar!** 🎉

