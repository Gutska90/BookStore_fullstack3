# 📚 Sistema de Gestión de Biblioteca - FullStack

## 🎯 Actividad Formativa 2
**"Aplicando un patrón de diseño a nuestro desarrollo"**

---

## 📋 Descripción

Aplicación web **FullStack** completa para la gestión de libros de una biblioteca, desarrollada con:

- **Backend:** Spring Boot 3.2.0 + MySQL/Oracle
- **Frontend:** Angular 19
- **Patrón de Diseño:** MVC (Model-View-Controller)
- **API REST:** Comunicación HTTP entre capas

---

## ✨ Características Principales

### 🔧 Backend (Spring Boot)
- ✅ API REST completa con CRUD
- ✅ Conexión a Base de Datos (MySQL/Oracle)
- ✅ DTOs y validaciones
- ✅ Manejo de excepciones global
- ✅ Paginación y ordenamiento
- ✅ CORS habilitado
- ✅ Búsquedas avanzadas

### 🎨 Frontend (Angular)
- ✅ Interfaz moderna y responsive
- ✅ Lista de libros con tarjetas
- ✅ Formularios de crear/editar
- ✅ Validaciones en tiempo real
- ✅ Navegación con Router
- ✅ Comunicación HTTP con backend
- ✅ Manejo de errores

---

## 🛠️ Tecnologías Utilizadas

| Capa | Tecnología | Versión |
|------|------------|---------|
| Backend | Java | 17 |
| Backend | Spring Boot | 3.2.0 |
| Backend | Spring Data JPA | 3.2.0 |
| Backend | MySQL | 8.0 |
| Backend | Maven | 3.9+ |
| Frontend | Angular | 19 |
| Frontend | TypeScript | 5.6+ |
| Frontend | Node.js | 18+ |

---

## 📂 Estructura del Proyecto

```
fullstack3/
│
├── src/main/java/com/biblioteca/microservicio/
│   ├── controller/          # Controladores REST
│   ├── service/             # Lógica de negocio
│   ├── repository/          # Acceso a datos (JPA)
│   ├── entity/              # Entidades JPA
│   ├── dto/                 # Data Transfer Objects
│   ├── mapper/              # Conversión Entity-DTO
│   └── exception/           # Manejo de excepciones
│
├── biblioteca-frontend/
│   └── src/app/
│       ├── components/      # Componentes Angular
│       ├── services/        # Servicios HTTP
│       ├── models/          # Modelos de datos
│       └── app.routes.ts    # Configuración de rutas
│
├── database-setup.sql       # Script de base de datos
├── pom.xml                  # Configuración Maven
└── README.md                # Este archivo
```

---

## 🚀 Inicio Rápido

### 1️⃣ Clonar Repositorio
```bash
git clone https://github.com/Gutska90/BookStore_fullstack3.git
cd fullstack3
```

### 2️⃣ Configurar Base de Datos
```sql
CREATE DATABASE biblioteca_db;
USE biblioteca_db;
SOURCE database-setup.sql;
```

### 3️⃣ Ejecutar Backend
```bash
mvn clean install
mvn spring-boot:run
```
✅ Backend: http://localhost:8080

### 4️⃣ Ejecutar Frontend
```bash
cd biblioteca-frontend
npm install
npm start
```
✅ Frontend: http://localhost:4200

---

## 📡 API REST - Endpoints

### Base URL: `http://localhost:8080/api/libros`

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/all` | Obtener todos los libros |
| GET | `/{id}` | Obtener libro por ID |
| POST | `/` | Crear nuevo libro |
| PUT | `/{id}` | Actualizar libro |
| DELETE | `/{id}` | Eliminar libro |

### Ejemplo de Petición
```bash
# GET - Obtener todos
curl http://localhost:8080/api/libros/all

# POST - Crear nuevo
curl -X POST http://localhost:8080/api/libros \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "El Principito",
    "autor": "Antoine de Saint-Exupéry",
    "anioPublicacion": 1943,
    "genero": "Fábula"
  }'
```

---

## 🏗️ Patrón de Diseño: MVC

### Model (Modelo)
- `Book.java` - Entidad JPA
- `book.model.ts` - Modelo TypeScript
- Define la estructura de datos

### View (Vista)
- `book-list.component.html` - Lista de libros
- `book-form.component.html` - Formulario
- Presentación visual

### Controller (Controlador)
- `BookController.java` - Endpoints REST
- `BookService.java` - Lógica de negocio
- `book.service.ts` - Servicio HTTP Angular
- Lógica de control

---

## 📊 Base de Datos

### Tabla: books

| Campo | Tipo | Descripción |
|-------|------|-------------|
| id | BIGINT | ID único (PK) |
| titulo | VARCHAR(255) | Título del libro |
| autor | VARCHAR(255) | Autor |
| anio_publicacion | INT | Año de publicación |
| genero | VARCHAR(100) | Género literario |

### Datos de Prueba
- 9 libros precargados
- Géneros variados
- Autores clásicos y contemporáneos

---

## 🧪 Pruebas

### Backend
```bash
# Probar API
curl http://localhost:8080/api/libros/all

# Verificar salud
curl http://localhost:8080/actuator/health
```

### Frontend
1. Abrir http://localhost:4200
2. Verificar lista de libros
3. Crear nuevo libro
4. Editar libro existente
5. Eliminar libro

---

## 📚 Documentación Adicional

- 📖 [README-ACTIVIDAD-FORMATIVA-2.md](README-ACTIVIDAD-FORMATIVA-2.md) - Documentación completa
- 🚀 [INSTRUCCIONES-EJECUCION.md](INSTRUCCIONES-EJECUCION.md) - Guía de instalación
- 🎨 [FRONTEND-ANGULAR-README.md](FRONTEND-ANGULAR-README.md) - Documentación del frontend
- 📮 [POSTMAN-COLLECTION-README.md](POSTMAN-COLLECTION-README.md) - Colección Postman

---

## 🔗 Enlaces

- **Repositorio:** https://github.com/Gutska90/BookStore_fullstack3
- **Backend API:** http://localhost:8080/api/libros
- **Frontend Web:** http://localhost:4200

---

## ✅ Requisitos Cumplidos

- [x] Aplicación Angular desarrollada
- [x] Comunicación con microservicio Spring Boot
- [x] CRUD completo (GET, POST, PUT, DELETE)
- [x] Muestra todos los atributos del libro
- [x] Usa URL del microservicio local
- [x] Patrón MVC implementado
- [x] Diseño responsive
- [x] Validaciones implementadas
- [x] Manejo de errores
- [x] Documentación completa

---

## 🐛 Solución de Problemas

### Backend no inicia
```bash
# Verificar puerto 8080
lsof -i :8080
kill -9 [PID]
```

### Frontend no inicia
```bash
# Limpiar caché
cd biblioteca-frontend
rm -rf node_modules package-lock.json
npm install
```

### Error de conexión a BD
- Verificar que MySQL esté ejecutándose
- Revisar credenciales en `application.properties`

---

## 👨‍💻 Desarrollo

### Compilar Backend
```bash
mvn clean package
```

### Compilar Frontend
```bash
cd biblioteca-frontend
npm run build
```

### Ejecutar Tests
```bash
mvn test
```

---

## 📝 Notas Importantes

1. **Orden de inicio:** Backend primero, luego Frontend
2. **Base de datos:** Debe estar ejecutándose
3. **CORS:** Ya configurado en el backend
4. **Puertos:** 8080 (backend), 4200 (frontend)

---

## 🎓 Información Académica

**Institución:** DuocUC  
**Asignatura:** Desarrollo de Aplicaciones FullStack  
**Actividad:** Formativa 2  
**Tipo:** Individual  
**Fecha:** Noviembre 2025

---

## 📄 Licencia

Este proyecto es de uso académico para la asignatura de Desarrollo FullStack.

---

## 🤝 Contribuciones

Proyecto individual desarrollado para actividad formativa.

---

## 📧 Contacto

Para consultas sobre el proyecto, revisar la documentación o contactar al profesor de la asignatura.

---

**🎉 Proyecto FullStack completado exitosamente**

*Sistema de Gestión de Biblioteca - Backend Spring Boot + Frontend Angular*

---

## 🚀 Quick Start

```bash
# 1. Clonar
git clone https://github.com/Gutska90/BookStore_fullstack3.git
cd fullstack3

# 2. Backend
mvn spring-boot:run

# 3. Frontend (nueva terminal)
cd biblioteca-frontend
npm install && npm start

# 4. Abrir navegador
open http://localhost:4200
```

---

**✨ ¡Listo para usar!**
