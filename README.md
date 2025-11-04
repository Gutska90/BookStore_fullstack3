# 🏥 Sistema de Gestión de Laboratorios y Resultados de Análisis

## 📋 Descripción del Proyecto

Sistema backend desarrollado en **Spring Boot** para la gestión de laboratorios clínicos y sus resultados de análisis. 

**Actividad Sumativa - Experiencia 1: Programando nuestro BackEnd**

---

## ✅ Microservicios Implementados (2 de 3 requeridos)

### 1. 🔐 Microservicio de Control de Usuarios
- CRUD completo (Crear, Leer, Actualizar, Eliminar)
- Sistema de inicio de sesión con JWT
- Gestión de roles: ADMINISTRADOR, TECNICO, PACIENTE
- Encriptación de contraseñas con BCrypt

### 2. 📊 Microservicio de Gestión de Resultados de Análisis
- Almacenamiento de resultados
- Consulta con múltiples filtros
- CRUD completo
- Asociación con usuarios y laboratorios

---

## 🛠️ Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Spring Security**
- **Oracle Database** (Base de datos principal)
- **JWT** (JSON Web Tokens)
- **BCrypt** (Encriptación)
- **Maven**

---

## 🚀 Instalación y Ejecución

### 1. Requisitos Previos
- Java 17+
- Maven 3.6+
- Oracle Database 18c+

### 2. Configurar Base de Datos

#### Ejecutar Script SQL en Oracle:
```bash
sqlplus usuario/password@servidor @database-setup-laboratorios-oracle.sql
```

#### Configurar Credenciales en `application.properties`:
```properties
spring.datasource.url=jdbc:oracle:thin:@//HOST:PORT/SERVICE_NAME
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD
```

### 3. Compilar y Ejecutar
```bash
mvn clean install
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

---

## 📡 Endpoints REST

### 🔐 Autenticación
- `POST /api/auth/login` - Iniciar sesión (retorna token JWT)
- `POST /api/auth/logout` - Cerrar sesión

### 👥 Usuarios
- `POST /api/usuarios` - Crear usuario
- `GET /api/usuarios` - Listar todos
- `GET /api/usuarios/{id}` - Obtener por ID
- `PUT /api/usuarios/{id}` - Actualizar
- `DELETE /api/usuarios/{id}` - Eliminar
- `GET /api/usuarios/rol/{rol}` - Buscar por rol

### 📊 Resultados de Análisis
- `POST /api/resultados` - Crear resultado
- `GET /api/resultados` - Listar (paginado)
- `GET /api/resultados/{id}` - Obtener por ID
- `PUT /api/resultados/{id}` - Actualizar
- `DELETE /api/resultados/{id}` - Eliminar
- `GET /api/resultados/usuario/{usuarioId}` - Por usuario
- `GET /api/resultados/laboratorio/{labId}` - Por laboratorio
- `GET /api/resultados/estado/{estado}` - Por estado
- `GET /api/resultados/tipo?tipo=...` - Por tipo de análisis
- `GET /api/resultados/fechas?inicio=...&fin=...` - Por rango de fechas

### 🏥 Laboratorios
- `POST /api/laboratorios` - Crear laboratorio
- `GET /api/laboratorios` - Listar todos
- `GET /api/laboratorios/{id}` - Obtener por ID

---

## 📝 Datos de Prueba

El script SQL incluye:

### Usuarios (3)
| Email | Contraseña | Rol |
|-------|------------|-----|
| admin@lab.com | admin123 | ADMINISTRADOR |
| tecnico@lab.com | tecnico123 | TECNICO |
| paciente@lab.com | paciente123 | PACIENTE |

### Laboratorios (3)
1. Laboratorio Clínico Central
2. Laboratorio de Patología
3. Laboratorio de Genética

### Resultados de Análisis (3)
- Hemograma Completo
- Biopsia de Tejido
- Prueba Genética BRCA

---

## 🧪 Pruebas con Postman

1. Importar la colección: `Laboratorios-API.postman_collection.json`
2. Configurar URL base: `http://localhost:8080`
3. Probar endpoints en el siguiente orden:
   - Login (obtener token)
   - Crear/listar usuarios
   - Crear/listar laboratorios
   - Crear/consultar resultados

---

## 📂 Estructura del Proyecto

```
src/main/java/com/laboratorio/microservicio/
├── LaboratorioMicroservicioApplication.java  # Clase principal
├── config/
│   └── SecurityConfig.java                   # Spring Security
├── controller/
│   ├── AuthController.java                   # Autenticación
│   ├── UsuarioController.java                # CRUD Usuarios
│   ├── LaboratorioController.java            # CRUD Laboratorios
│   └── ResultadoAnalisisController.java      # CRUD Resultados
├── dto/                                       # Data Transfer Objects
├── entity/                                    # Entidades JPA
│   ├── Usuario.java
│   ├── Laboratorio.java
│   └── ResultadoAnalisis.java
├── exception/                                 # Manejo de errores
├── mapper/                                    # Conversión DTO-Entity
├── repository/                                # Acceso a datos
├── service/                                   # Lógica de negocio
└── util/
    └── JwtUtil.java                          # Utilidades JWT
```

---

## 📚 Documentación Adicional

- `README-ACTIVIDAD-SUMATIVA.md` - Detalles de la actividad
- `DOCUMENTACION-ACTIVIDAD-SUMATIVA.md` - Especificaciones técnicas
- `CHECKLIST-ENTREGA.md` - Lista de verificación
- `VERIFICACION-REQUERIMIENTOS.md` - Cumplimiento de requerimientos

---

## ✅ Requisitos Cumplidos

- [x] Framework Spring Boot ✅
- [x] Repositorio Git ✅
- [x] Conexión a Oracle Database ✅
- [x] Controladores RESTful (GET, POST, PUT, DELETE) ✅
- [x] Mínimo 3 registros por tabla ✅
- [x] Validación con Postman ✅
- [x] 2 microservicios desarrollados ✅
- [x] Al menos 2 roles de usuario ✅
- [x] Caso: Gestión de Laboratorios ✅

---

## 👨‍💻 Autor

**Gustavo** - Desarrollo Full Stack

## 📄 Licencia

Proyecto académico - Duoc UC

---

**🔗 Repositorio:** https://github.com/Gutska90/BookStore_fullstack3

**🎉 ¡Sistema completo y listo para entregar!**

