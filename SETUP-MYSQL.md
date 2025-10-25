# 🐬 Guía de Configuración con MySQL

## ¿Por qué MySQL?

Si no tienes acceso a Oracle Cloud o prefieres usar MySQL (más simple y gratuito), esta guía es para ti.

---

## 📋 Opción 1: MySQL Local (Recomendado para desarrollo)

### Paso 1: Instalar MySQL

#### En Windows:
1. Descarga MySQL desde: https://dev.mysql.com/downloads/installer/
2. Instala MySQL Server
3. Durante la instalación, configura:
   - Usuario: `root`
   - Contraseña: `root` (o la que prefieras)
   - Puerto: `3306` (por defecto)

#### En macOS:
```bash
# Usando Homebrew
brew install mysql

# Iniciar MySQL
brew services start mysql

# Configurar contraseña
mysql_secure_installation
```

#### En Linux (Ubuntu/Debian):
```bash
# Instalar MySQL
sudo apt update
sudo apt install mysql-server

# Iniciar servicio
sudo systemctl start mysql

# Configurar seguridad
sudo mysql_secure_installation
```

### Paso 2: Crear Base de Datos (Opcional)

```bash
# Conectarse a MySQL
mysql -u root -p

# Crear base de datos
CREATE DATABASE biblioteca_db;

# Salir
exit;
```

> **Nota**: Spring Boot puede crear la base de datos automáticamente con `createDatabaseIfNotExist=true`

### Paso 3: Configurar application.properties

El archivo `application.properties` ya está configurado para MySQL por defecto. Solo ajusta tu contraseña:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=TU_PASSWORD_AQUI
```

### Paso 4: Ejecutar el Proyecto

```bash
mvn clean install
mvn spring-boot:run
```

¡Listo! Tu API estará corriendo en `http://localhost:8080/api/libros`

---

## 📋 Opción 2: MySQL en la Nube (Gratis)

### Railway.app (Recomendado)

1. **Crear cuenta en Railway**: https://railway.app
2. **Crear nuevo proyecto**
3. **Añadir MySQL**:
   - Click en "+ New"
   - Selecciona "Database"
   - Elige "MySQL"
4. **Obtener credenciales**:
   - En la pestaña "Connect", copia:
     - `MYSQL_HOST`
     - `MYSQL_PORT`
     - `MYSQL_USER`
     - `MYSQL_PASSWORD`
     - `MYSQL_DATABASE`

5. **Configurar application.properties**:
```properties
spring.datasource.url=jdbc:mysql://[MYSQL_HOST]:[MYSQL_PORT]/[MYSQL_DATABASE]?useSSL=true&serverTimezone=UTC
spring.datasource.username=[MYSQL_USER]
spring.datasource.password=[MYSQL_PASSWORD]
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

### Otras alternativas gratuitas:

#### PlanetScale
- URL: https://planetscale.com
- Plan gratuito: 5GB
- Configuración similar a Railway

#### FreeSQLDatabase
- URL: https://www.freesqldatabase.com
- MySQL gratuito 5MB (suficiente para pruebas)

#### Clever Cloud
- URL: https://www.clever-cloud.com
- Plan gratuito disponible

---

## 🔧 Configuración del Proyecto

### Ya está hecho por ti:

✅ **pom.xml** - Driver MySQL añadido
✅ **application.properties** - Configurado para MySQL
✅ **Book.java** - Estrategia IDENTITY (compatible con MySQL)
✅ **database-setup-mysql.sql** - Script SQL para MySQL

### Cambios realizados:

1. **Añadido driver MySQL en pom.xml**:
```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
</dependency>
```

2. **Book.java usa IDENTITY** (mejor para MySQL):
```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
```

---

## 🚀 Comandos Rápidos

### Compilar con dependencias MySQL
```bash
mvn clean install
```

### Ejecutar
```bash
mvn spring-boot:run
```

### Verificar conexión MySQL
```bash
mysql -u root -p -e "SHOW DATABASES;"
```

---

## 🧪 Probar la API

### Crear un libro
```bash
curl -X POST http://localhost:8080/api/libros \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Cien años de soledad",
    "autor": "Gabriel García Márquez",
    "anioPublicacion": 1967,
    "genero": "Realismo mágico"
  }'
```

### Obtener todos los libros
```bash
curl http://localhost:8080/api/libros
```

---

## 🐛 Solución de Problemas MySQL

### Error: "Access denied for user 'root'"

**Solución 1** - Restablecer contraseña:
```bash
# Linux/macOS
sudo mysql
ALTER USER 'root'@'localhost' IDENTIFIED BY 'nueva_password';
FLUSH PRIVILEGES;
exit;
```

**Solución 2** - Crear nuevo usuario:
```sql
CREATE USER 'biblioteca'@'localhost' IDENTIFIED BY 'biblioteca123';
GRANT ALL PRIVILEGES ON biblioteca_db.* TO 'biblioteca'@'localhost';
FLUSH PRIVILEGES;
```

Luego actualiza `application.properties`:
```properties
spring.datasource.username=biblioteca
spring.datasource.password=biblioteca123
```

### Error: "Unknown database 'biblioteca_db'"

**Solución**: Asegúrate que la URL incluya `createDatabaseIfNotExist=true`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
```

O créala manualmente:
```bash
mysql -u root -p -e "CREATE DATABASE biblioteca_db;"
```

### Error: "Communications link failure"

**Causa**: MySQL no está corriendo

**Solución**:
```bash
# Windows
net start MySQL80

# macOS
brew services start mysql

# Linux
sudo systemctl start mysql
```

### Error: "Public Key Retrieval is not allowed"

**Solución**: Añade a la URL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca_db?allowPublicKeyRetrieval=true&useSSL=false
```

---

## 📊 Comparación Oracle vs MySQL

| Característica | Oracle | MySQL |
|----------------|--------|-------|
| **Instalación** | Compleja | Simple |
| **Precio** | Requiere Oracle Cloud | Gratuito |
| **Para aprender** | ⭐⭐⭐ | ⭐⭐⭐⭐⭐ |
| **Para producción** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐⭐ |
| **Configuración** | Más pasos | Menos pasos |

**Recomendación**: Usa MySQL para desarrollo y aprendizaje, Oracle para producción empresarial.

---

## 🎯 Verificar que Funciona

### 1. Iniciar MySQL
```bash
mysql -u root -p
```

### 2. Verificar base de datos
```sql
SHOW DATABASES;
USE biblioteca_db;
SHOW TABLES;
```

### 3. Iniciar Spring Boot
```bash
mvn spring-boot:run
```

### 4. Probar API
```bash
curl http://localhost:8080/api/libros
```

Si ves `[]` (array vacío), ¡está funcionando! 🎉

---

## 📱 Herramientas Visuales para MySQL

### MySQL Workbench (Oficial)
- **URL**: https://dev.mysql.com/downloads/workbench/
- **Descripción**: Cliente oficial de MySQL
- **Gratis**: Sí

### DBeaver (Recomendado)
- **URL**: https://dbeaver.io/
- **Descripción**: Cliente universal, muy completo
- **Gratis**: Sí

### HeidiSQL (Windows)
- **URL**: https://www.heidisql.com/
- **Descripción**: Ligero y rápido
- **Gratis**: Sí

### phpMyAdmin (Web)
- **URL**: https://www.phpmyadmin.net/
- **Descripción**: Interfaz web
- **Gratis**: Sí

---

## 📝 Notas Importantes

1. ✅ **La aplicación funciona igual** con Oracle o MySQL
2. ✅ **Todos los endpoints son idénticos**
3. ✅ **El código no cambia**, solo la configuración
4. ✅ **Puedes cambiar entre Oracle y MySQL** editando `application.properties`

---

## 🔄 Cambiar de MySQL a Oracle (si quieres)

En `application.properties`, comenta MySQL y descomenta Oracle:

```properties
# OPCIÓN 1: ORACLE (Descomenta para usar Oracle)
spring.datasource.url=jdbc:oracle:thin:@//[HOST]:[PORT]/[SERVICE_NAME]
spring.datasource.username=[TU_USUARIO]
spring.datasource.password=[TU_PASSWORD]
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect

# OPCIÓN 2: MySQL (Comenta si usas Oracle)
# spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca_db?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
# spring.datasource.username=root
# spring.datasource.password=root
# spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
# spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

---

## ✅ Checklist MySQL

- [ ] MySQL instalado y corriendo
- [ ] Contraseña de root configurada
- [ ] Base de datos `biblioteca_db` creada (o auto-create habilitado)
- [ ] `application.properties` configurado
- [ ] `mvn clean install` ejecutado exitosamente
- [ ] `mvn spring-boot:run` iniciado sin errores
- [ ] API responde en `http://localhost:8080/api/libros`
- [ ] Probado crear, leer, actualizar y eliminar libros

---

## 🎉 ¡Listo!

Ahora tu microservicio funciona con MySQL. Es más simple y rápido para desarrollo y aprendizaje.

**¿Necesitas ayuda?**
- Para detalles de la API: lee `README.md`
- Para arquitectura: lee `ARQUITECTURA.md`
- Para problemas generales: lee `INSTRUCCIONES.md`

