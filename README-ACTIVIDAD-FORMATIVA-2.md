# 📚 Actividad Formativa 2: Aplicando un Patrón de Diseño

## 👨‍💻 Información del Proyecto

**Asignatura:** Desarrollo de Aplicaciones FullStack  
**Actividad:** Formativa 2 - "Aplicando un patrón de diseño a nuestro desarrollo"  
**Fecha:** Noviembre 2025  
**Tipo:** Individual

---

## 📋 Descripción General

Aplicación web completa (FullStack) para la **gestión de libros de una biblioteca**, desarrollada con:
- **Backend:** Spring Boot + MySQL/Oracle
- **Frontend:** Angular 19
- **Patrón de Diseño:** MVC (Model-View-Controller)
- **Comunicación:** API REST

---

## ✅ Requisitos Cumplidos

### Backend (Spring Boot)
- ✅ Microservicio de la Actividad Formativa 1
- ✅ CRUD completo implementado
- ✅ Conexión a Base de Datos (MySQL/Oracle)
- ✅ API REST con todos los métodos HTTP
- ✅ CORS habilitado para comunicación con frontend

### Frontend (Angular)
- ✅ Aplicación web desarrollada en Angular 19
- ✅ Comunicación con microservicio mediante HTTP
- ✅ Implementación de CRUD completo:
  - **GET:** Obtener todos los libros
  - **GET:** Obtener libro por ID
  - **POST:** Agregar nuevo libro
  - **PUT:** Actualizar libro existente
  - **DELETE:** Eliminar libro
- ✅ Muestra todos los atributos: ID, título, autor, año, género
- ✅ Diseño responsive y moderno
- ✅ Validaciones en formularios

### Patrón de Diseño
- ✅ **MVC (Model-View-Controller)** implementado
- ✅ Separación clara de responsabilidades
- ✅ Componentes standalone de Angular (arquetipo moderno)

---

## 🛠️ Tecnologías Utilizadas

### Backend
- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- MySQL 8.0 / Oracle 19c
- Maven
- Hibernate

### Frontend
- Angular 19 (última versión)
- TypeScript
- HTML5 + CSS3
- HttpClient
- Angular Router
- FormsModule

---

## 📂 Estructura del Proyecto

```
fullstack3/
├── src/main/java/com/biblioteca/microservicio/    # Backend Spring Boot
│   ├── controller/                                # Controladores REST
│   │   └── BookController.java
│   ├── service/                                   # Lógica de negocio
│   │   └── BookService.java
│   ├── repository/                                # Acceso a datos
│   │   └── BookRepository.java
│   ├── entity/                                    # Entidades JPA
│   │   └── Book.java
│   ├── dto/                                       # Data Transfer Objects
│   ├── mapper/                                    # Conversión Entity-DTO
│   └── exception/                                 # Manejo de excepciones
│
├── biblioteca-frontend/                           # Frontend Angular
│   ├── src/app/
│   │   ├── components/                           # Componentes visuales
│   │   │   ├── book-list/                        # Lista de libros
│   │   │   └── book-form/                        # Formulario crear/editar
│   │   ├── services/                             # Servicios HTTP
│   │   │   └── book.service.ts
│   │   ├── models/                               # Modelos de datos
│   │   │   └── book.model.ts
│   │   └── app.routes.ts                         # Configuración de rutas
│   └── package.json
│
├── database-setup.sql                            # Script de base de datos
├── pom.xml                                       # Configuración Maven
└── README-ACTIVIDAD-FORMATIVA-2.md              # Este archivo
```

---

## 🏗️ Patrón de Diseño: MVC

### Model (Modelo)
**Ubicación:** `book.model.ts`, `Book.java`

Define la estructura de datos del libro:
```typescript
interface Book {
  id?: number;
  titulo: string;
  autor: string;
  anioPublicacion: number;
  genero: string;
}
```

### View (Vista)
**Ubicación:** `*.component.html`, `*.component.css`

- `book-list.component.html` - Vista de lista de libros
- `book-form.component.html` - Vista de formulario

### Controller (Controlador)
**Ubicación:** `*.component.ts`, `*.service.ts`, `BookController.java`

**Frontend:**
- `book-list.component.ts` - Controla la lista
- `book-form.component.ts` - Controla el formulario
- `book.service.ts` - Maneja comunicación HTTP

**Backend:**
- `BookController.java` - Endpoints REST
- `BookService.java` - Lógica de negocio

---

## 🚀 Instalación y Ejecución

### 1. Clonar el Repositorio
```bash
git clone https://github.com/Gutska90/BookStore_fullstack3.git
cd fullstack3
```

### 2. Configurar Base de Datos

**Opción A: MySQL**
```sql
CREATE DATABASE biblioteca_db;
USE biblioteca_db;
-- Ejecutar database-setup.sql
```

**Opción B: Oracle**
```sql
-- Configurar en application.properties
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/XEPDB1
```

### 3. Ejecutar Backend
```bash
# Compilar y ejecutar
mvn clean install
mvn spring-boot:run

# Backend disponible en: http://localhost:8080
```

### 4. Ejecutar Frontend
```bash
cd biblioteca-frontend

# Instalar dependencias
npm install

# Ejecutar en modo desarrollo
npm start

# Frontend disponible en: http://localhost:4200
```

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
| GET | `/autor/{autor}/all` | Buscar por autor |
| GET | `/genero/{genero}/all` | Buscar por género |

### Ejemplo de Petición POST
```json
{
  "titulo": "El Principito",
  "autor": "Antoine de Saint-Exupéry",
  "anioPublicacion": 1943,
  "genero": "Fábula"
}
```

---

## 🎨 Capturas de Pantalla

### Vista de Lista de Libros
- Muestra todos los libros en formato de tarjetas
- Botones para editar y eliminar
- Botón para agregar nuevo libro

### Vista de Formulario
- Campos para: Título, Autor, Año, Género
- Validaciones en tiempo real
- Botones: Guardar y Cancelar

---

## 🧪 Pruebas Realizadas

### Backend
✅ GET todos los libros - Funciona correctamente  
✅ GET libro por ID - Funciona correctamente  
✅ POST crear libro - Funciona correctamente  
✅ PUT actualizar libro - Funciona correctamente  
✅ DELETE eliminar libro - Funciona correctamente  

### Frontend
✅ Carga de lista de libros - Funciona correctamente  
✅ Navegación entre vistas - Funciona correctamente  
✅ Crear nuevo libro - Funciona correctamente  
✅ Editar libro existente - Funciona correctamente  
✅ Eliminar libro - Funciona correctamente  
✅ Validaciones de formulario - Funciona correctamente  

### Integración Frontend-Backend
✅ Comunicación HTTP - Funciona correctamente  
✅ CORS configurado - Funciona correctamente  
✅ Manejo de errores - Funciona correctamente  

---

## 📊 Base de Datos

### Tabla: books

| Campo | Tipo | Descripción |
|-------|------|-------------|
| id | BIGINT | ID único (PK, AUTO_INCREMENT) |
| titulo | VARCHAR(255) | Título del libro |
| autor | VARCHAR(255) | Autor del libro |
| anio_publicacion | INT | Año de publicación |
| genero | VARCHAR(100) | Género literario |

### Datos de Prueba
El sistema incluye 9 libros de prueba:
- Cien años de soledad
- Don Quijote de la Mancha
- El Principito
- 1984
- Orgullo y Prejuicio
- Harry Potter y la Piedra Filosofal
- El Señor de los Anillos
- Crónica de una Muerte Anunciada
- La Sombra del Viento

---

## 📝 Documentación Adicional

- `FRONTEND-ANGULAR-README.md` - Documentación detallada del frontend
- `README.md` - Documentación general del proyecto
- `database-setup.sql` - Script completo de base de datos

---

## 🔗 Enlaces

- **Repositorio Git:** https://github.com/Gutska90/BookStore_fullstack3
- **Backend:** http://localhost:8080
- **Frontend:** http://localhost:4200
- **API Docs:** http://localhost:8080/api/libros

---

## ✅ Checklist de Entrega

- [x] Aplicación Angular desarrollada
- [x] Comunicación con microservicio Spring Boot
- [x] CRUD completo implementado (GET, POST, PUT, DELETE)
- [x] Muestra todos los atributos del libro
- [x] Usa URL del microservicio local
- [x] Patrón MVC implementado
- [x] Código subido a Git
- [x] ZIP con todos los archivos
- [x] Documentación completa

---

## 🎓 Conclusiones

Este proyecto demuestra la implementación exitosa de una aplicación FullStack utilizando:

1. **Separación de Responsabilidades:** Backend y Frontend independientes
2. **Patrón MVC:** Implementado correctamente en ambas capas
3. **API REST:** Comunicación estándar entre capas
4. **Buenas Prácticas:** Código limpio, organizado y documentado
5. **Tecnologías Modernas:** Angular 19 y Spring Boot 3.2.0

---

**🎉 Proyecto completado exitosamente**

*Desarrollado para la Actividad Formativa 2 - Desarrollo FullStack*

