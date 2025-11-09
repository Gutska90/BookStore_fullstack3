# 📮 Colección Postman - API Biblioteca

## 📋 Descripción

Colección de Postman con todas las peticiones para probar el microservicio de biblioteca.

## 🔗 Endpoints Disponibles

### Base URL
```
http://localhost:8080/api/libros
```

---

## 📚 Peticiones Disponibles

### 1. GET - Obtener Todos los Libros
```
GET http://localhost:8080/api/libros/all
```

**Respuesta:**
```json
[
  {
    "id": 1,
    "titulo": "Cien años de soledad",
    "autor": "Gabriel García Márquez",
    "anioPublicacion": 1967,
    "genero": "Realismo mágico"
  }
]
```

---

### 2. GET - Obtener Libro por ID
```
GET http://localhost:8080/api/libros/1
```

**Respuesta:**
```json
{
  "id": 1,
  "titulo": "Cien años de soledad",
  "autor": "Gabriel García Márquez",
  "anioPublicacion": 1967,
  "genero": "Realismo mágico"
}
```

---

### 3. POST - Crear Nuevo Libro
```
POST http://localhost:8080/api/libros
Content-Type: application/json
```

**Body:**
```json
{
  "titulo": "Nuevo Libro de Prueba",
  "autor": "Autor Test",
  "anioPublicacion": 2024,
  "genero": "Prueba"
}
```

**Respuesta:**
```json
{
  "id": 11,
  "titulo": "Nuevo Libro de Prueba",
  "autor": "Autor Test",
  "anioPublicacion": 2024,
  "genero": "Prueba"
}
```

---

### 4. PUT - Actualizar Libro
```
PUT http://localhost:8080/api/libros/11
Content-Type: application/json
```

**Body:**
```json
{
  "titulo": "Libro Actualizado",
  "autor": "Autor Modificado",
  "anioPublicacion": 2024,
  "genero": "Actualizado"
}
```

**Respuesta:**
```json
{
  "id": 11,
  "titulo": "Libro Actualizado",
  "autor": "Autor Modificado",
  "anioPublicacion": 2024,
  "genero": "Actualizado"
}
```

---

### 5. DELETE - Eliminar Libro
```
DELETE http://localhost:8080/api/libros/11
```

**Respuesta:**
```
204 No Content
```

---

### 6. GET - Buscar por Autor
```
GET http://localhost:8080/api/libros/autor/Gabriel García Márquez/all
```

---

### 7. GET - Buscar por Género
```
GET http://localhost:8080/api/libros/genero/Novela/all
```

---

## 🧪 Importar Colección a Postman

### Opción 1: Importar desde archivo
1. Abrir Postman
2. Click en "Import"
3. Seleccionar archivo `Biblioteca-API.postman_collection.json`
4. Click en "Import"

### Opción 2: Crear manualmente
1. Crear nueva colección "Biblioteca API"
2. Agregar las peticiones listadas arriba
3. Configurar headers y body según se indica

---

## ✅ Pruebas Recomendadas

### Secuencia de Pruebas
1. **GET /all** - Verificar que hay libros
2. **GET /{id}** - Obtener un libro específico
3. **POST** - Crear nuevo libro
4. **GET /all** - Verificar que se creó
5. **PUT /{id}** - Actualizar el libro creado
6. **GET /{id}** - Verificar actualización
7. **DELETE /{id}** - Eliminar el libro
8. **GET /all** - Verificar eliminación

---

## 📝 Notas

- Todos los endpoints requieren que el backend esté ejecutándose
- El Content-Type debe ser `application/json` para POST y PUT
- Los IDs son auto-generados por la base de datos
- CORS está habilitado para todas las peticiones

---

## 🔧 Comandos cURL Alternativos

### GET Todos
```bash
curl http://localhost:8080/api/libros/all
```

### POST Crear
```bash
curl -X POST http://localhost:8080/api/libros \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Test",
    "autor": "Test Author",
    "anioPublicacion": 2024,
    "genero": "Test"
  }'
```

### PUT Actualizar
```bash
curl -X PUT http://localhost:8080/api/libros/1 \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Updated",
    "autor": "Updated Author",
    "anioPublicacion": 2024,
    "genero": "Updated"
  }'
```

### DELETE Eliminar
```bash
curl -X DELETE http://localhost:8080/api/libros/1
```

---

**📮 Colección lista para usar en Postman**

