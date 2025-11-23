# Sistema de Gestión de Laboratorios

Sistema completo de gestión de laboratorios y resultados de análisis desarrollado con arquitectura de microservicios, Spring Boot y Angular.

## 📋 Tabla de Contenidos

- [Características](#características)
- [Arquitectura](#arquitectura)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Configuración](#configuración)
- [Ejecución](#ejecución)
- [Documentación de APIs](#documentación-de-apis)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Endpoints](#endpoints)
- [Seguridad](#seguridad)
- [Base de Datos](#base-de-datos)

## 🚀 Características

### Backend (Microservicios)
- ✅ **Microservicio de Usuarios**: Gestión de usuarios, autenticación JWT, roles
- ✅ **Microservicio de Laboratorios**: CRUD completo de laboratorios
- ✅ **Microservicio de Resultados**: Gestión de resultados de análisis
- ✅ **Validación de Contraseñas**: 6 validaciones implementadas
- ✅ **Paginación**: Endpoints con soporte de paginación
- ✅ **Swagger/OpenAPI**: Documentación automática de APIs
- ✅ **Seguridad JWT**: Autenticación y autorización con tokens

### Frontend (Angular)
- ✅ **Páginas de Autenticación**: Login, Registro, Recuperar Contraseña
- ✅ **Páginas Internas**: Dashboard, Perfil, Laboratorios, Resultados
- ✅ **Validación de Formularios**: Validaciones en tiempo real
- ✅ **Manejo de Estados**: Loading, errores, confirmaciones
- ✅ **Responsive Design**: Interfaz adaptable

## 🏗️ Arquitectura

```
┌─────────────────┐
│  Frontend       │
│  (Angular)      │
│  Puerto: 4200   │
└────────┬────────┘
         │
         ├─────────────────┬─────────────────┐
         │                 │                 │
┌────────▼────────┐ ┌─────▼──────┐ ┌───────▼──────┐
│ Microservicio   │ │Microservicio│ │ Microservicio│
│   Usuarios      │ │Laboratorios │ │  Resultados  │
│   Puerto: 8081  │ │ Puerto: 8082│ │ Puerto: 8083 │
└────────┬────────┘ └─────┬──────┘ └───────┬──────┘
         │                 │                 │
         └─────────────────┴─────────────────┘
                           │
                  ┌────────▼────────┐
                  │  Oracle DB     │
                  │  Puerto: 1521   │
                  └─────────────────┘
```

## 📦 Requisitos

- **Java**: 17 o superior
- **Maven**: 3.6 o superior
- **Node.js**: 18 o superior
- **npm**: 9 o superior
- **Oracle Database**: 21c o superior (o usar Docker)
- **Docker** (opcional): Para ejecutar Oracle y los microservicios

## 🔧 Instalación

### 1. Clonar el Repositorio

```bash
git clone <url-del-repositorio>
cd fullstack3
```

### 2. Configurar Base de Datos Oracle

#### Opción A: Oracle Local

1. Instalar Oracle Database 21c
2. Crear usuario y base de datos
3. Ejecutar el script SQL:

```bash
sqlplus SYSTEM/Oracle123@localhost:1521/XEPDB1 @database-laboratorios-setup.sql
```

#### Opción B: Oracle con Docker

```bash
docker run -d --name oracle-db \
  -p 1521:1521 \
  -e ORACLE_PWD=Oracle123 \
  container-registry.oracle.com/database/express:21.3.0-xe
```

### 3. Compilar Microservicios

```bash
# Microservicio de Usuarios
cd microservicio-usuarios
mvn clean package -DskipTests

# Microservicio de Laboratorios
cd ../microservicio-laboratorios
mvn clean package -DskipTests

# Microservicio de Resultados
cd ../microservicio-resultados
mvn clean package -DskipTests
```

### 4. Instalar Dependencias del Frontend

```bash
cd ../laboratorios-frontend
npm install
```

## ⚙️ Configuración

### Configuración de Base de Datos

Editar `application.properties` en cada microservicio:

```properties
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/XEPDB1
spring.datasource.username=SYSTEM
spring.datasource.password=Oracle123
```

### Configuración de JWT

El secreto JWT está configurado en `application.properties`:

```properties
jwt.secret=laboratorios-secret-key-para-jwt-token-generacion-segura-2024
jwt.expiration=86400000
```

**⚠️ IMPORTANTE**: En producción, cambiar el secreto JWT por uno seguro.

## 🚀 Ejecución

### Opción 1: Ejecución Manual

#### Iniciar Microservicios

```bash
# Terminal 1 - Microservicio Usuarios
cd microservicio-usuarios
mvn spring-boot:run

# Terminal 2 - Microservicio Laboratorios
cd microservicio-laboratorios
mvn spring-boot:run

# Terminal 3 - Microservicio Resultados
cd microservicio-resultados
mvn spring-boot:run
```

#### Iniciar Frontend

```bash
cd laboratorios-frontend
ng serve
```

### Opción 2: Docker Compose

```bash
# Construir y ejecutar todos los servicios
docker-compose up -d

# Ver logs
docker-compose logs -f

# Detener servicios
docker-compose down
```

## 📚 Documentación de APIs

Una vez iniciados los microservicios, accede a la documentación Swagger:

- **Usuarios**: http://localhost:8081/swagger-ui.html
- **Laboratorios**: http://localhost:8082/swagger-ui.html
- **Resultados**: http://localhost:8083/swagger-ui.html

### Endpoints JSON:

- **Usuarios**: http://localhost:8081/api-docs
- **Laboratorios**: http://localhost:8082/api-docs
- **Resultados**: http://localhost:8083/api-docs

## 📁 Estructura del Proyecto

```
fullstack3/
├── microservicio-usuarios/          # Microservicio de usuarios y autenticación
│   ├── src/main/java/
│   │   └── com/laboratorios/usuarios/
│   │       ├── controller/         # Controladores REST
│   │       ├── service/             # Lógica de negocio
│   │       ├── repository/          # Acceso a datos
│   │       ├── entity/              # Entidades JPA
│   │       ├── dto/                 # Data Transfer Objects
│   │       ├── config/              # Configuraciones (Security, Swagger)
│   │       └── exception/           # Manejo de excepciones
│   └── pom.xml
│
├── microservicio-laboratorios/      # Microservicio de laboratorios
│   └── [estructura similar]
│
├── microservicio-resultados/         # Microservicio de resultados
│   └── [estructura similar]
│
├── laboratorios-frontend/            # Frontend Angular
│   ├── src/app/
│   │   ├── components/              # Componentes Angular
│   │   ├── services/                # Servicios HTTP
│   │   ├── models/                  # Modelos TypeScript
│   │   ├── guards/                  # Guards de autenticación
│   │   └── validators/               # Validadores personalizados
│   └── package.json
│
├── database-laboratorios-setup.sql   # Script de base de datos
├── docker-compose.yml                # Configuración Docker
└── README.md                         # Este archivo
```

## 🔌 Endpoints Principales

### Microservicio de Usuarios (Puerto 8081)

```
POST   /api/auth/login              # Iniciar sesión
GET    /api/usuarios                # Listar usuarios (paginado)
GET    /api/usuarios/{id}           # Obtener usuario por ID
POST   /api/usuarios                # Crear usuario
PUT    /api/usuarios/{id}           # Actualizar usuario
DELETE /api/usuarios/{id}           # Eliminar usuario
```

### Microservicio de Laboratorios (Puerto 8082)

```
GET    /api/laboratorios            # Listar laboratorios (paginado)
GET    /api/laboratorios/activos    # Listar activos (paginado)
GET    /api/laboratorios/{id}       # Obtener por ID
POST   /api/laboratorios            # Crear laboratorio
PUT    /api/laboratorios/{id}       # Actualizar laboratorio
DELETE /api/laboratorios/{id}       # Eliminar laboratorio
```

### Microservicio de Resultados (Puerto 8083)

```
GET    /api/resultados              # Listar resultados (paginado)
GET    /api/resultados/{id}         # Obtener por ID
GET    /api/resultados/paciente/{id} # Por paciente (paginado)
GET    /api/resultados/laboratorio/{id} # Por laboratorio (paginado)
POST   /api/resultados              # Crear resultado
PUT    /api/resultados/{id}         # Actualizar resultado
DELETE /api/resultados/{id}         # Eliminar resultado
```

## 🔐 Seguridad

### Autenticación JWT

1. **Login**: Obtener token desde `/api/auth/login`
2. **Uso del Token**: Incluir en header `Authorization: Bearer <token>`
3. **Validación**: Los microservicios de Laboratorios y Resultados validan el token automáticamente

### Roles de Usuario

- **ADMINISTRADOR**: Acceso completo al sistema
- **PACIENTE**: Puede ver sus propios resultados
- **TECNICO_LABORATORIO**: Puede gestionar resultados

### Validaciones de Contraseña

Las contraseñas deben cumplir:
1. ✅ Mínimo 8 caracteres
2. ✅ Máximo 50 caracteres
3. ✅ Al menos una letra mayúscula
4. ✅ Al menos una letra minúscula
5. ✅ Al menos un número
6. ✅ Al menos un carácter especial (!@#$%^&*)

## 💾 Base de Datos

### Tablas

- **USUARIOS**: Información de usuarios y autenticación
- **LABORATORIOS**: Información de laboratorios
- **RESULTADOS**: Resultados de análisis

### Script SQL

El archivo `database-laboratorios-setup.sql` contiene:
- Creación de tablas
- Secuencias para IDs
- Triggers automáticos
- Índices para optimización
- Datos iniciales (3 laboratorios de ejemplo)

## 🧪 Pruebas

### Probar APIs con Postman

1. Importar la colección de Postman (si está disponible)
2. Configurar variables de entorno:
   - `base_url_usuarios`: http://localhost:8081
   - `base_url_laboratorios`: http://localhost:8082
   - `base_url_resultados`: http://localhost:8083
3. Ejecutar flujo completo:
   - Login → Obtener token
   - Usar token en requests a Laboratorios y Resultados

### Probar Frontend

1. Acceder a http://localhost:4200
2. Registrar un nuevo usuario
3. Iniciar sesión
4. Navegar por las diferentes secciones

## 🐛 Solución de Problemas

### Error de Conexión a Base de Datos

- Verificar que Oracle esté ejecutándose
- Verificar credenciales en `application.properties`
- Verificar que el puerto 1521 esté disponible

### Error de Compilación

- Verificar versión de Java: `java -version` (debe ser 17+)
- Limpiar y recompilar: `mvn clean install`

### Error de CORS

- Los microservicios tienen `@CrossOrigin(origins = "*")` configurado
- Si persiste, verificar configuración de proxy en Angular

## 📝 Notas Adicionales

- Los microservicios usan la misma base de datos Oracle
- El frontend se comunica con los 3 microservicios
- Swagger está disponible en todos los microservicios
- La paginación está habilitada en endpoints de listado (tamaño por defecto: 10)

## 👥 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto es parte de una actividad formativa académica.

## 📧 Contacto

Para preguntas o soporte, contactar al equipo de desarrollo.

---

**Desarrollado con ❤️ usando Spring Boot y Angular**
