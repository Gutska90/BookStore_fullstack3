# 🚀 Instrucciones de Ejecución - Sistema Biblioteca FullStack

## ⚡ Guía Rápida de Inicio

### Requisitos Previos
- ✅ Java 17 o superior
- ✅ Maven 3.6+
- ✅ Node.js 18+ y npm
- ✅ MySQL 8.0 o Oracle 19c
- ✅ Git

---

## 📦 Paso 1: Clonar el Repositorio

```bash
git clone https://github.com/Gutska90/BookStore_fullstack3.git
cd fullstack3
```

---

## 🗄️ Paso 2: Configurar Base de Datos

### Opción A: MySQL (Recomendado para desarrollo)

1. **Crear la base de datos:**
```sql
CREATE DATABASE biblioteca_db;
USE biblioteca_db;
```

2. **Ejecutar el script:**
```bash
mysql -u root -p biblioteca_db < database-setup.sql
```

3. **Configurar credenciales en `src/main/resources/application.properties`:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca_db
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD
```

### Opción B: Oracle

1. **Configurar en `application.properties`:**
```properties
spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/XEPDB1
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_PASSWORD
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect
```

2. **Ejecutar el script SQL en Oracle SQL Developer**

---

## 🔧 Paso 3: Ejecutar Backend (Spring Boot)

### Terminal 1 - Backend

```bash
# Limpiar y compilar
mvn clean install

# Ejecutar el microservicio
mvn spring-boot:run
```

**Verificar que esté funcionando:**
```bash
# Debe mostrar la lista de libros en formato JSON
curl http://localhost:8080/api/libros/all
```

✅ **Backend corriendo en:** `http://localhost:8080`

---

## 🎨 Paso 4: Ejecutar Frontend (Angular)

### Terminal 2 - Frontend

```bash
# Ir a la carpeta del frontend
cd biblioteca-frontend

# Instalar dependencias (solo la primera vez)
npm install

# Ejecutar en modo desarrollo
npm start
```

✅ **Frontend corriendo en:** `http://localhost:4200`

---

## 🌐 Paso 5: Acceder a la Aplicación

1. **Abrir navegador:** http://localhost:4200
2. **Verás la lista de libros**
3. **Probar funcionalidades:**
   - Ver lista de libros
   - Crear nuevo libro
   - Editar libro existente
   - Eliminar libro

---

## 🧪 Verificación de Funcionamiento

### Backend
```bash
# Obtener todos los libros
curl http://localhost:8080/api/libros/all

# Obtener libro por ID
curl http://localhost:8080/api/libros/1

# Crear nuevo libro
curl -X POST http://localhost:8080/api/libros \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Nuevo Libro",
    "autor": "Autor Test",
    "anioPublicacion": 2024,
    "genero": "Prueba"
  }'
```

### Frontend
1. Abrir http://localhost:4200
2. Debe mostrar la lista de libros
3. Click en "Agregar Nuevo Libro"
4. Llenar formulario y guardar
5. Verificar que aparece en la lista

---

## 🛑 Detener la Aplicación

### Detener Backend
```bash
# En la terminal del backend, presionar:
Ctrl + C
```

### Detener Frontend
```bash
# En la terminal del frontend, presionar:
Ctrl + C
```

---

## 🐛 Solución de Problemas Comunes

### Error: "Port 8080 already in use"
```bash
# Encontrar el proceso
lsof -i :8080

# Matar el proceso
kill -9 [PID]
```

### Error: "Port 4200 already in use"
```bash
# Encontrar el proceso
lsof -i :4200

# Matar el proceso
kill -9 [PID]
```

### Error: "Cannot connect to database"
- Verificar que MySQL/Oracle esté ejecutándose
- Verificar credenciales en `application.properties`
- Verificar que la base de datos existe

### Error: "CORS policy blocked"
- Verificar que `@CrossOrigin(origins = "*")` esté en `BookController.java`
- Reiniciar el backend

### Error: "npm install fails"
```bash
# Limpiar caché de npm
npm cache clean --force

# Eliminar node_modules y reinstalar
rm -rf node_modules package-lock.json
npm install
```

---

## 📊 Puertos Utilizados

| Servicio | Puerto | URL |
|----------|--------|-----|
| Backend | 8080 | http://localhost:8080 |
| Frontend | 4200 | http://localhost:4200 |
| MySQL | 3306 | localhost:3306 |
| Oracle | 1521 | localhost:1521 |

---

## 🔄 Compilar para Producción

### Backend
```bash
mvn clean package
# JAR generado en: target/microservicio-biblioteca-3.0.0.jar

# Ejecutar JAR
java -jar target/microservicio-biblioteca-3.0.0.jar
```

### Frontend
```bash
cd biblioteca-frontend
npm run build
# Archivos generados en: dist/biblioteca-frontend/
```

---

## 📝 Notas Importantes

1. **Orden de inicio:** Primero el backend, luego el frontend
2. **Base de datos:** Debe estar ejecutándose antes del backend
3. **CORS:** Ya está configurado en el backend
4. **Datos de prueba:** Se cargan automáticamente con el script SQL

---

## ✅ Checklist de Verificación

- [ ] Java 17 instalado
- [ ] Maven instalado
- [ ] Node.js y npm instalados
- [ ] MySQL/Oracle ejecutándose
- [ ] Base de datos creada
- [ ] Script SQL ejecutado
- [ ] Backend compilado sin errores
- [ ] Backend ejecutándose en puerto 8080
- [ ] Frontend ejecutándose en puerto 4200
- [ ] Aplicación accesible en navegador
- [ ] CRUD funcionando correctamente

---

## 🆘 Soporte

Si encuentras problemas:
1. Revisar logs del backend en la terminal
2. Revisar consola del navegador (F12)
3. Verificar que todos los servicios estén ejecutándose
4. Consultar la documentación en `README-ACTIVIDAD-FORMATIVA-2.md`

---

**🎉 ¡Listo! Tu aplicación FullStack está funcionando**

