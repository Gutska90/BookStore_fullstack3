# 📦 Entrega Final - Actividad Formativa 2

## 📋 Información de Entrega

**Asignatura:** Desarrollo de Aplicaciones FullStack  
**Actividad:** Formativa 2 - "Aplicando un patrón de diseño a nuestro desarrollo"  
**Tipo:** Individual  
**Fecha de Entrega:** Noviembre 2025

---

## 📦 Archivos a Entregar

### 1. Archivo Comprimido (.zip)
📁 **Ubicación:** `/Users/user/fullstack3-actividad-formativa-2.zip`

**Contenido del ZIP:**
- ✅ Código fuente completo del backend (Spring Boot)
- ✅ Código fuente completo del frontend (Angular)
- ✅ Script de base de datos (`database-setup.sql`)
- ✅ Archivo `pom.xml` (configuración Maven)
- ✅ Archivo `package.json` (configuración npm)
- ✅ Documentación completa (README.md y otros .md)
- ✅ Colección de Postman

**Excluido del ZIP:**
- ❌ Carpeta `target/` (archivos compilados)
- ❌ Carpeta `node_modules/` (dependencias npm)
- ❌ Carpeta `.git/` (control de versiones)
- ❌ Archivos `.log` (logs)

### 2. Link de GitHub
🔗 **Repositorio:** https://github.com/Gutska90/BookStore_fullstack3

**Último commit:**
```
🎨 Frontend Angular + Backend Spring Boot - Actividad Formativa 2
```

---

## ✅ Checklist de Requisitos Cumplidos

### Backend (Spring Boot)
- [x] Desarrollado con Spring Boot
- [x] Conexión a Base de Datos (MySQL/Oracle)
- [x] CRUD completo implementado
- [x] Controladores REST (GET, POST, PUT, DELETE)
- [x] Estructura correcta (controller, service, repository, entity)
- [x] Manejo de excepciones
- [x] CORS habilitado

### Frontend (Angular)
- [x] Aplicación Angular desarrollada
- [x] Comunicación con microservicio
- [x] CRUD completo:
  - [x] GET: Obtener todos los libros
  - [x] GET: Obtener libro por ID
  - [x] POST: Agregar nuevo libro
  - [x] PUT: Actualizar libro existente
  - [x] DELETE: Eliminar libro
- [x] Muestra todos los atributos (ID, título, autor, año, género)
- [x] Usa URL del microservicio local
- [x] Diseño responsive

### Patrón de Diseño
- [x] Patrón MVC implementado
- [x] Separación de responsabilidades
- [x] Model: Entidades y modelos de datos
- [x] View: Componentes HTML/CSS
- [x] Controller: Lógica de control y servicios

### Base de Datos
- [x] Script SQL incluido
- [x] Al menos 3 registros por tabla
- [x] Tablas correctamente estructuradas
- [x] Datos de prueba cargados

### Documentación
- [x] README.md principal
- [x] README-ACTIVIDAD-FORMATIVA-2.md (documentación detallada)
- [x] INSTRUCCIONES-EJECUCION.md (guía de instalación)
- [x] FRONTEND-ANGULAR-README.md (documentación del frontend)
- [x] POSTMAN-COLLECTION-README.md (guía de API)

### Git
- [x] Código subido a GitHub
- [x] Commits descriptivos
- [x] Repositorio organizado
- [x] .gitignore configurado

---

## 🧪 Pruebas Realizadas

### Backend
✅ **GET /api/libros/all** - Obtener todos los libros  
✅ **GET /api/libros/{id}** - Obtener libro por ID  
✅ **POST /api/libros** - Crear nuevo libro  
✅ **PUT /api/libros/{id}** - Actualizar libro  
✅ **DELETE /api/libros/{id}** - Eliminar libro  

### Frontend
✅ **Carga de lista** - Lista de libros se muestra correctamente  
✅ **Navegación** - Routing funciona correctamente  
✅ **Crear libro** - Formulario de creación funciona  
✅ **Editar libro** - Formulario de edición funciona  
✅ **Eliminar libro** - Eliminación con confirmación funciona  
✅ **Validaciones** - Validaciones de formulario funcionan  

### Integración
✅ **Frontend-Backend** - Comunicación HTTP funciona  
✅ **CORS** - Configurado correctamente  
✅ **Errores** - Manejo de errores implementado  

---

## 📊 Estadísticas del Proyecto

### Backend
- **Lenguaje:** Java 17
- **Framework:** Spring Boot 3.2.0
- **Controladores:** 1 (BookController)
- **Servicios:** 1 (BookService)
- **Repositorios:** 1 (BookRepository)
- **Entidades:** 1 (Book)
- **DTOs:** 3 (CreateBookRequest, UpdateBookRequest, BookResponse)
- **Endpoints:** 13+ endpoints REST

### Frontend
- **Framework:** Angular 19
- **Componentes:** 2 (BookList, BookForm)
- **Servicios:** 1 (BookService)
- **Modelos:** 1 (Book)
- **Rutas:** 3 (lista, crear, editar)
- **Líneas de código:** ~500 líneas

### Base de Datos
- **Tablas:** 1 (books)
- **Registros de prueba:** 9 libros
- **Campos:** 5 (id, titulo, autor, anio_publicacion, genero)

---

## 🎯 Patrón de Diseño: MVC

### Model (Modelo)
**Backend:**
- `Book.java` - Entidad JPA con anotaciones
- Define estructura de datos en BD

**Frontend:**
- `book.model.ts` - Interface TypeScript
- Define estructura de datos en frontend

### View (Vista)
**Frontend:**
- `book-list.component.html` - Vista de lista
- `book-list.component.css` - Estilos de lista
- `book-form.component.html` - Vista de formulario
- `book-form.component.css` - Estilos de formulario

### Controller (Controlador)
**Backend:**
- `BookController.java` - Endpoints REST
- `BookService.java` - Lógica de negocio
- `BookRepository.java` - Acceso a datos

**Frontend:**
- `book-list.component.ts` - Lógica de lista
- `book-form.component.ts` - Lógica de formulario
- `book.service.ts` - Servicio HTTP

---

## 📡 Endpoints Implementados

| Método | Endpoint | Descripción | Estado |
|--------|----------|-------------|--------|
| GET | `/api/libros/all` | Obtener todos | ✅ |
| GET | `/api/libros/{id}` | Obtener por ID | ✅ |
| POST | `/api/libros` | Crear nuevo | ✅ |
| PUT | `/api/libros/{id}` | Actualizar | ✅ |
| DELETE | `/api/libros/{id}` | Eliminar | ✅ |
| GET | `/api/libros/autor/{autor}/all` | Buscar por autor | ✅ |
| GET | `/api/libros/genero/{genero}/all` | Buscar por género | ✅ |

---

## 🚀 Instrucciones de Ejecución

### Requisitos Previos
- Java 17+
- Maven 3.6+
- Node.js 18+
- MySQL 8.0 o Oracle 19c

### Pasos de Ejecución

1. **Clonar repositorio:**
```bash
git clone https://github.com/Gutska90/BookStore_fullstack3.git
cd fullstack3
```

2. **Configurar base de datos:**
```sql
CREATE DATABASE biblioteca_db;
USE biblioteca_db;
SOURCE database-setup.sql;
```

3. **Ejecutar backend:**
```bash
mvn spring-boot:run
```

4. **Ejecutar frontend (nueva terminal):**
```bash
cd biblioteca-frontend
npm install
npm start
```

5. **Acceder a la aplicación:**
- Frontend: http://localhost:4200
- Backend API: http://localhost:8080/api/libros

---

## 📝 Documentación Incluida

1. **README.md** - Documentación principal del proyecto
2. **README-ACTIVIDAD-FORMATIVA-2.md** - Documentación detallada de la actividad
3. **INSTRUCCIONES-EJECUCION.md** - Guía paso a paso de instalación
4. **FRONTEND-ANGULAR-README.md** - Documentación específica del frontend
5. **POSTMAN-COLLECTION-README.md** - Guía de uso de la API con Postman
6. **ENTREGA-FINAL.md** - Este documento

---

## 🔗 Enlaces Importantes

- **Repositorio GitHub:** https://github.com/Gutska90/BookStore_fullstack3
- **Backend API:** http://localhost:8080
- **Frontend Web:** http://localhost:4200
- **Documentación API:** http://localhost:8080/api/libros

---

## 📦 Contenido del ZIP

```
fullstack3-actividad-formativa-2.zip
├── src/
│   └── main/
│       ├── java/com/biblioteca/microservicio/
│       │   ├── controller/
│       │   ├── service/
│       │   ├── repository/
│       │   ├── entity/
│       │   ├── dto/
│       │   ├── mapper/
│       │   └── exception/
│       └── resources/
│           └── application.properties
├── biblioteca-frontend/
│   └── src/
│       └── app/
│           ├── components/
│           ├── services/
│           ├── models/
│           └── app.routes.ts
├── database-setup.sql
├── pom.xml
├── README.md
├── README-ACTIVIDAD-FORMATIVA-2.md
├── INSTRUCCIONES-EJECUCION.md
├── FRONTEND-ANGULAR-README.md
├── POSTMAN-COLLECTION-README.md
└── ENTREGA-FINAL.md
```

---

## ✅ Verificación Final

- [x] Código compilado sin errores
- [x] Backend ejecutándose correctamente
- [x] Frontend ejecutándose correctamente
- [x] Integración frontend-backend verificada
- [x] CRUD funcionando completamente
- [x] Base de datos con datos de prueba
- [x] Documentación completa
- [x] Código subido a GitHub
- [x] ZIP creado y listo para entregar

---

## 🎓 Conclusión

El proyecto cumple con **todos los requisitos** de la Actividad Formativa 2:

1. ✅ Aplicación Angular desarrollada
2. ✅ Comunicación con microservicio Spring Boot
3. ✅ CRUD completo implementado
4. ✅ Patrón MVC aplicado correctamente
5. ✅ Base de datos configurada con datos de prueba
6. ✅ Documentación completa y detallada
7. ✅ Código organizado y limpio
8. ✅ Repositorio Git actualizado

---

## 📍 Ubicación de Archivos para Entrega

### Archivo ZIP
```
/Users/user/fullstack3-actividad-formativa-2.zip
```

### Link de GitHub
```
https://github.com/Gutska90/BookStore_fullstack3
```

---

**🎉 Proyecto completado y listo para entregar**

*Desarrollado para la Actividad Formativa 2 - Desarrollo FullStack*  
*DuocUC - Noviembre 2025*

