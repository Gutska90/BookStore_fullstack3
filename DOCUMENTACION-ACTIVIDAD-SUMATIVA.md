# 🏥 Sistema de Gestión de Laboratorios y Resultados de Análisis

## 📋 Descripción del Caso de Negocio

Sistema backend para la gestión de laboratorios clínicos y sus resultados de análisis, permitiendo el control de usuarios con diferentes roles y la gestión completa de resultados de análisis clínicos.

## 🎯 Microservicios a Desarrollar

### ✅ Microservicio 1: Control de Usuarios
**Responsabilidades:**
- CRUD completo de usuarios (crear, modificar, eliminar)
- Sistema de inicio de sesión
- Gestión de roles (al menos 2 roles diferentes)
- Autenticación y autorización

**Roles Definidos:**
1. **ADMINISTRADOR**: Control total del sistema, gestión de usuarios y laboratorios
2. **TÉCNICO**: Gestión de resultados de análisis, consulta de datos
3. **PACIENTE**: Consulta de sus propios resultados (opcional, para futuras implementaciones)

### ✅ Microservicio 2: Gestión de Resultados de Análisis
**Responsabilidades:**
- Almacenamiento de resultados de análisis clínicos
- Consulta de resultados
- Asociación con laboratorios
- Asociación con pacientes/usuarios
- CRUD completo de resultados

## 🗄️ Modelo de Datos

### Tabla: USUARIOS
- ID (PK)
- Nombre completo
- Email (único)
- Contraseña (encriptada)
- Rol (ADMINISTRADOR, TÉCNICO, PACIENTE)
- Estado (activo/inactivo)
- Fecha creación
- Fecha actualización

### Tabla: LABORATORIOS
- ID (PK)
- Nombre del laboratorio
- Dirección
- Teléfono
- Email de contacto
- Estado (activo/inactivo)
- Fecha creación
- Fecha actualización

### Tabla: RESULTADOS_ANALISIS
- ID (PK)
- ID Usuario/Paciente (FK)
- ID Laboratorio (FK)
- Tipo de análisis
- Fecha de realización
- Fecha de resultado
- Valores/Resultados (JSON o texto)
- Observaciones
- Estado (pendiente, completado, cancelado)
- Fecha creación
- Fecha actualización

## 🔐 Requerimientos de Roles

### ADMINISTRADOR
- ✅ Crear, modificar, eliminar usuarios
- ✅ Crear, modificar, eliminar laboratorios
- ✅ Crear, modificar, eliminar resultados
- ✅ Consultar todos los resultados
- ✅ Gestionar roles de usuarios

### TÉCNICO
- ✅ Consultar resultados
- ✅ Crear, modificar resultados (sus propios)
- ✅ Consultar laboratorios
- ❌ No puede gestionar usuarios
- ❌ No puede gestionar laboratorios

### PACIENTE (futuro)
- ✅ Consultar sus propios resultados
- ❌ No puede crear/modificar resultados
- ❌ No puede gestionar usuarios/laboratorios

## 📊 Laboratorios Predefinidos (Mínimo 3)

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

- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Oracle Database** (Base de datos principal)
- **Spring Security** (para autenticación)
- **JWT** (JSON Web Tokens para sesiones)
- **BCrypt** (encriptación de contraseñas)
- **Jakarta Validation**

## 📡 Endpoints REST

### Microservicio de Usuarios
- `POST /api/usuarios` - Crear usuario
- `GET /api/usuarios` - Listar usuarios (según rol)
- `GET /api/usuarios/{id}` - Obtener usuario por ID
- `PUT /api/usuarios/{id}` - Actualizar usuario
- `DELETE /api/usuarios/{id}` - Eliminar usuario
- `POST /api/auth/login` - Iniciar sesión
- `POST /api/auth/logout` - Cerrar sesión

### Microservicio de Resultados
- `POST /api/resultados` - Crear resultado
- `GET /api/resultados` - Listar resultados (con filtros)
- `GET /api/resultados/{id}` - Obtener resultado por ID
- `GET /api/resultados/usuario/{userId}` - Resultados de un usuario
- `GET /api/resultados/laboratorio/{labId}` - Resultados de un laboratorio
- `PUT /api/resultados/{id}` - Actualizar resultado
- `DELETE /api/resultados/{id}` - Eliminar resultado

## 📝 Datos de Prueba

### Usuarios de Prueba
- Admin: admin@lab.com / password: admin123
- Técnico: tecnico@lab.com / password: tecnico123
- Paciente: paciente@lab.com / password: paciente123

### Laboratorios de Prueba
- Laboratorio Clínico Central
- Laboratorio de Patología
- Laboratorio de Genética

### Resultados de Prueba
- Mínimo 3 resultados asociados a diferentes usuarios y laboratorios

