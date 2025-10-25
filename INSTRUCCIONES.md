# 📚 Instrucciones para Ejecutar el Microservicio

## Pasos Rápidos de Configuración

### 1️⃣ Configurar Base de Datos Oracle Cloud

Antes de ejecutar el microservicio, debes configurar tu conexión a Oracle Cloud:

1. Abre el archivo: `src/main/resources/application.properties`
2. Reemplaza los valores entre corchetes con tus credenciales:

```properties
spring.datasource.url=jdbc:oracle:thin:@//[HOST]:[PORT]/[SERVICE_NAME]
spring.datasource.username=[TU_USUARIO]
spring.datasource.password=[TU_PASSWORD]
```

**Ejemplo real:**
```properties
spring.datasource.url=jdbc:oracle:thin:@//adb.us-ashburn-1.oraclecloud.com:1522/mydb_high
spring.datasource.username=admin
spring.datasource.password=MiPassword123
```

### 2️⃣ Compilar el Proyecto

Abre una terminal en la carpeta del proyecto y ejecuta:

```bash
mvn clean install
```

Este comando:
- Descarga todas las dependencias necesarias
- Compila el código
- Ejecuta las validaciones

### 3️⃣ Ejecutar el Microservicio

Una vez compilado, ejecuta:

```bash
mvn spring-boot:run
```

Si todo está correcto, verás en la consola:
```
==============================================
Microservicio Biblioteca iniciado correctamente
Puerto: 8080
API Base URL: http://localhost:8080/api/libros
==============================================
```

### 4️⃣ Probar la API

Abre tu navegador o Postman y prueba:

**Obtener todos los libros:**
```
GET http://localhost:8080/api/libros
```

**Crear un libro (usando Postman o cURL):**
```bash
curl -X POST http://localhost:8080/api/libros \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "El Principito",
    "autor": "Antoine de Saint-Exupéry",
    "anioPublicacion": 1943,
    "genero": "Fábula"
  }'
```

## 📦 Importar Colección de Postman

1. Abre Postman
2. Click en "Import"
3. Selecciona el archivo: `Biblioteca-API.postman_collection.json`
4. ¡Listo! Tendrás todos los endpoints listos para probar

## 🔧 Solución de Problemas

### Error: "Cannot connect to Oracle database"
- Verifica tus credenciales en `application.properties`
- Asegúrate de tener acceso de red a Oracle Cloud
- Verifica que el puerto 1521 o 1522 no esté bloqueado

### Error: "Port 8080 already in use"
Cambia el puerto en `application.properties`:
```properties
server.port=8081
```

### Error al compilar con Maven
Asegúrate de tener instalado:
- Java 17 o superior: `java -version`
- Maven 3.6 o superior: `mvn -version`

## 📝 Endpoints Principales

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/api/libros` | Obtener todos los libros |
| GET | `/api/libros/{id}` | Obtener libro por ID |
| POST | `/api/libros` | Crear nuevo libro |
| PUT | `/api/libros/{id}` | Actualizar libro |
| DELETE | `/api/libros/{id}` | Eliminar libro |

## 🎯 Completar la Actividad

Para entregar tu actividad:

1. ✅ Verifica que todos los endpoints funcionen correctamente
2. ✅ Asegúrate de que la conexión a Oracle Cloud esté activa
3. ✅ Sube tu código a un repositorio Git
4. ✅ Comparte el repositorio con tu docente

### Crear Repositorio Git

```bash
# Inicializar repositorio
git init

# Agregar archivos
git add .

# Hacer commit
git commit -m "Microservicio de biblioteca - Actividad Formativa 1"

# Conectar con GitHub (reemplaza con tu URL)
git remote add origin https://github.com/TU_USUARIO/biblioteca-microservicio.git

# Subir código
git push -u origin main
```

## 🎓 Recursos Adicionales

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Oracle JDBC Driver](https://www.oracle.com/database/technologies/appdev/jdbc.html)

¡Éxito con tu actividad! 🚀

