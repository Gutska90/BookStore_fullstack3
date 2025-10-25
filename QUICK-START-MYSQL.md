# ⚡ Inicio Ultra Rápido con MySQL

## Para Principiantes - 5 Minutos ⏱️

### Paso 1: Instalar MySQL (2 min)

#### Windows:
1. Descarga: https://dev.mysql.com/downloads/installer/
2. Instala MySQL Server
3. Contraseña root: `root`

#### macOS:
```bash
brew install mysql
brew services start mysql
mysqladmin -u root password 'root'
```

#### Linux:
```bash
sudo apt install mysql-server
sudo systemctl start mysql
sudo mysql -e "ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';"
```

### Paso 2: Configurar el Proyecto (1 min)

El archivo `application.properties` ya está configurado. Solo verifica la contraseña:

```properties
spring.datasource.password=root
```

Si usaste otra contraseña, cámbiala aquí.

### Paso 3: Ejecutar (2 min)

```bash
mvn clean install
mvn spring-boot:run
```

¡Listo! API en: `http://localhost:8080/api/libros` 🎉

---

## 🧪 Prueba Inmediata

```bash
# Ver todos los libros (estará vacío al inicio)
curl http://localhost:8080/api/libros

# Crear un libro
curl -X POST http://localhost:8080/api/libros \
  -H "Content-Type: application/json" \
  -d '{"titulo":"El Principito","autor":"Antoine de Saint-Exupéry","anioPublicacion":1943,"genero":"Fábula"}'

# Ver el libro creado
curl http://localhost:8080/api/libros
```

---

## 🐛 Problemas Comunes

### "Access denied for user 'root'"
```bash
mysql -u root
ALTER USER 'root'@'localhost' IDENTIFIED BY 'root';
FLUSH PRIVILEGES;
exit;
```

### "MySQL not running"
```bash
# Windows
net start MySQL80

# macOS
brew services start mysql

# Linux
sudo systemctl start mysql
```

### "Port 8080 already in use"
En `application.properties` cambia:
```properties
server.port=8081
```

---

## 📖 Más Información

- **Setup completo MySQL**: [`SETUP-MYSQL.md`](SETUP-MYSQL.md)
- **Documentación completa**: [`README.md`](README.md)
- **Guía rápida general**: [`GUIA-RAPIDA.md`](GUIA-RAPIDA.md)

---

**¡Eso es todo! Ya tienes tu microservicio corriendo con MySQL.** 🚀

