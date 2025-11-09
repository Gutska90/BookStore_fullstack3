# 🎨 Frontend Angular - Sistema de Gestión de Biblioteca

## 📋 Descripción

Aplicación web desarrollada en **Angular** que permite gestionar libros de una biblioteca, comunicándose con el microservicio Spring Boot mediante API REST.

## ✅ Funcionalidades Implementadas

### CRUD Completo
- ✅ **GET**: Obtener todos los libros
- ✅ **GET**: Obtener un libro por ID
- ✅ **POST**: Agregar nuevo libro
- ✅ **PUT**: Actualizar libro existente
- ✅ **DELETE**: Eliminar libro

### Características
- ✅ Lista de libros con diseño de tarjetas
- ✅ Formulario para crear/editar libros
- ✅ Validaciones en el frontend
- ✅ Navegación con Angular Router
- ✅ Diseño responsive
- ✅ Comunicación HTTP con el backend
- ✅ Manejo de errores

## 🛠️ Tecnologías Utilizadas

- **Angular 19** (última versión)
- **TypeScript**
- **HttpClient** para comunicación con API
- **Router** para navegación
- **FormsModule** para formularios
- **CSS** para estilos

## 📂 Estructura del Proyecto

```
biblioteca-frontend/
├── src/
│   ├── app/
│   │   ├── components/
│   │   │   ├── book-list/           # Lista de libros
│   │   │   │   ├── book-list.component.ts
│   │   │   │   ├── book-list.component.html
│   │   │   │   └── book-list.component.css
│   │   │   └── book-form/           # Formulario crear/editar
│   │   │       ├── book-form.component.ts
│   │   │       ├── book-form.component.html
│   │   │       └── book-form.component.css
│   │   ├── models/
│   │   │   └── book.model.ts        # Modelo de datos
│   │   ├── services/
│   │   │   └── book.service.ts      # Servicio HTTP
│   │   ├── app.ts                   # Componente principal
│   │   ├── app.routes.ts            # Configuración de rutas
│   │   └── app.config.ts            # Configuración de la app
│   ├── styles.css                   # Estilos globales
│   └── index.html                   # HTML principal
├── package.json
└── angular.json
```

## 🚀 Instalación y Ejecución

### 1. Instalar Dependencias
```bash
cd biblioteca-frontend
npm install
```

### 2. Ejecutar en Modo Desarrollo
```bash
npm start
```

La aplicación estará disponible en: `http://localhost:4200`

### 3. Compilar para Producción
```bash
npm run build
```

Los archivos compilados estarán en: `dist/biblioteca-frontend/`

## 🔗 Integración con Backend

### URL del Backend
```typescript
private apiUrl = 'http://localhost:8080/api/libros';
```

### Endpoints Utilizados
- `GET http://localhost:8080/api/libros/all` - Listar todos
- `GET http://localhost:8080/api/libros/{id}` - Obtener por ID
- `POST http://localhost:8080/api/libros` - Crear
- `PUT http://localhost:8080/api/libros/{id}` - Actualizar
- `DELETE http://localhost:8080/api/libros/{id}` - Eliminar

## 📱 Uso de la Aplicación

### Ver Lista de Libros
1. Abrir `http://localhost:4200`
2. Se muestra la lista de todos los libros

### Agregar Nuevo Libro
1. Click en "Agregar Nuevo Libro"
2. Llenar el formulario
3. Click en "Crear"

### Editar Libro
1. Click en "Editar" en cualquier libro
2. Modificar los campos
3. Click en "Actualizar"

### Eliminar Libro
1. Click en "Eliminar" en cualquier libro
2. Confirmar la eliminación

## 🎨 Diseño

- **Minimalista**: Diseño limpio y simple
- **Responsive**: Funciona en desktop y móvil
- **Tarjetas**: Cada libro se muestra en una tarjeta
- **Colores**: Verde para acciones positivas, rojo para eliminar

## ✅ Requisitos Cumplidos

- [x] Desarrollado en Angular
- [x] Comunicación con microservicio Spring Boot
- [x] CRUD completo (GET, POST, PUT, DELETE)
- [x] Muestra todos los atributos del libro (ID, título, autor, año, género)
- [x] Usa la URL del microservicio local
- [x] Patrón de diseño: MVC (Model-View-Controller)
- [x] Arquetipo: Componentes standalone de Angular

## 🏗️ Patrón de Diseño Aplicado

### MVC (Model-View-Controller)

**Model:**
- `book.model.ts` - Define la estructura de datos

**View:**
- `book-list.component.html` - Vista de lista
- `book-form.component.html` - Vista de formulario

**Controller:**
- `book-list.component.ts` - Lógica de lista
- `book-form.component.ts` - Lógica de formulario
- `book.service.ts` - Servicio de comunicación HTTP

## 📝 Notas

- El backend debe estar ejecutándose en `http://localhost:8080`
- CORS está habilitado en el backend (`@CrossOrigin(origins = "*")`)
- La aplicación Angular usa standalone components (Angular 19)

---

**🎉 ¡Frontend completo y funcionando!**

