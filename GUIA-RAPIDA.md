# 🚀 Guía Rápida - Microservicio Biblioteca

## 📋 Checklist Antes de Empezar

- [ ] ✅ Java 17 instalado (`java -version`)
- [ ] ✅ Maven instalado (`mvn -version`)
- [ ] ✅ MySQL instalado O Acceso a Oracle Cloud
- [ ] ✅ Credenciales de base de datos listas

## 🎯 3 Pasos Para Ejecutar

### Paso 1: Configurar Base de Datos

**Por defecto usa MySQL**. Solo ajusta tu contraseña en `src/main/resources/application.properties`:

```properties
spring.datasource.password=TU_PASSWORD_MYSQL
```

> 💡 **¿No tienes MySQL?** Lee [`SETUP-MYSQL.md`](SETUP-MYSQL.md) para instalarlo en 5 minutos.

> 🔷 **¿Prefieres Oracle?** Lee las instrucciones en [`README.md`](README.md#configuración)

### Paso 2: Compilar
```bash
mvn clean install
```

### Paso 3: Ejecutar
```bash
mvn spring-boot:run
```

¡Listo! Tu API está en: `http://localhost:8080/api/libros`

## 🧪 Prueba Rápida

### Crear un libro (cURL)
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

### Obtener todos los libros
```bash
curl http://localhost:8080/api/libros
```

## 📁 Archivos Importantes

| Archivo | Descripción |
|---------|-------------|
| `pom.xml` | Dependencias Maven |
| `application.properties` | Configuración de DB |
| `Book.java` | Entidad del libro |
| `BookRepository.java` | Acceso a datos |
| `BookService.java` | Lógica de negocio |
| `BookController.java` | API REST |

## 🔗 Endpoints Principales

```
GET    /api/libros          → Obtener todos
GET    /api/libros/{id}     → Obtener uno
POST   /api/libros          → Crear
PUT    /api/libros/{id}     → Actualizar
DELETE /api/libros/{id}     → Eliminar
```

## 📦 Usar Postman

1. Importar: `Biblioteca-API.postman_collection.json`
2. Ejecutar requests predefinidos
3. ¡Listo para probar!

## 🐛 Solución Rápida de Problemas

**Error de conexión a Oracle:**
- ✅ Verifica credenciales en `application.properties`
- ✅ Revisa firewall/VPN
- ✅ Confirma que el servicio Oracle esté activo

**Puerto 8080 ocupado:**
- Cambia en `application.properties`: `server.port=8081`

**Error de compilación:**
- Ejecuta: `mvn clean`
- Luego: `mvn install`

## 📚 Documentación Completa

- `README.md` - Documentación detallada
- `INSTRUCCIONES.md` - Guía paso a paso
- `ARQUITECTURA.md` - Explicación técnica

## 🎓 Para Entregar la Actividad

1. ✅ Verifica que todos los endpoints funcionen
2. ✅ Sube a GitHub
3. ✅ Comparte el repositorio con el docente

```bash
git init
git add .
git commit -m "Microservicio Biblioteca - Actividad 1"
git remote add origin https://github.com/TU_USUARIO/biblioteca-microservicio.git
git push -u origin main
```

---

**¿Necesitas más ayuda?** Revisa los otros archivos de documentación:
- 📖 `README.md` - Guía completa
- 🏗️ `ARQUITECTURA.md` - Diseño del sistema
- 📋 `INSTRUCCIONES.md` - Pasos detallados

