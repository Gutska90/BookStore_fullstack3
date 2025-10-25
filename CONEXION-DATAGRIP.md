# 🔌 Conectar a MySQL desde DataGrip

## 📋 Datos de Conexión

Usa estos datos para conectarte a tu base de datos desde DataGrip:

```
Host:       localhost
Port:       3306
Database:   biblioteca_db
User:       root
Password:   (vacío - sin contraseña)
```

---

## 🚀 Pasos para Conectar en DataGrip

### 1. Abrir DataGrip
Inicia DataGrip en tu computadora.

### 2. Crear Nueva Conexión
- Click en el botón **"+"** (New) en la esquina superior izquierda
- Selecciona **"Data Source"**
- Elige **"MySQL"**

### 3. Configurar la Conexión

En la ventana que se abre, completa los siguientes campos:

#### **General Tab:**

| Campo | Valor |
|-------|-------|
| Name | `Biblioteca - MySQL` |
| Host | `localhost` |
| Port | `3306` |
| User | `root` |
| Password | *dejar vacío* |
| Database | `biblioteca_db` |

#### Captura de pantalla de referencia:
```
┌─────────────────────────────────────────┐
│ Name:     Biblioteca - MySQL           │
│ Host:     localhost                     │
│ Port:     3306                          │
│ User:     root                          │
│ Password: (vacío)                       │
│ Database: biblioteca_db                 │
│                                         │
│ URL: jdbc:mysql://localhost:3306/      │
│      biblioteca_db                      │
└─────────────────────────────────────────┘
```

### 4. Descargar Driver (si es necesario)

Si es la primera vez que usas MySQL en DataGrip:
- DataGrip mostrará un mensaje: **"Download missing driver files"**
- Click en **"Download"**
- Espera a que descargue el driver MySQL

### 5. Test Connection

- Click en **"Test Connection"**
- Deberías ver: ✅ **"Successful"**
- Si hay error, verifica que MySQL esté corriendo

### 6. Aplicar y Conectar

- Click en **"Apply"**
- Click en **"OK"**
- ¡Listo! Ya estás conectado

---

## 🔍 Explorar la Base de Datos

Una vez conectado, verás:

```
biblioteca_db
└── tables
    └── books
        ├── Columns
        │   ├── id (BIGINT, PK, AUTO_INCREMENT)
        │   ├── titulo (VARCHAR(200))
        │   ├── autor (VARCHAR(100))
        │   ├── anio_publicacion (INT)
        │   └── genero (VARCHAR(50))
        └── Data (4 filas)
```

### Ver los Datos

1. **Doble click** en la tabla `books`
2. O click derecho → **"Jump to Query Console"**
3. Ejecuta:
   ```sql
   SELECT * FROM books;
   ```

Deberías ver los 4 libros que creamos:
```
id | titulo                     | autor                         | anio_publicacion | genero
---|----------------------------|-------------------------------|------------------|------------------
1  | Cien años de soledad       | Gabriel García Márquez        | 1967             | Realismo mágico
2  | Don Quijote de la Mancha   | Miguel de Cervantes          | 1605             | Novela
3  | El Principito              | Antoine de Saint-Exupéry      | 1943             | Fábula
4  | 1984                       | George Orwell                 | 1949             | Ciencia ficción
```

---

## 🛠️ Operaciones Útiles en DataGrip

### Ver Estructura de la Tabla
```sql
DESCRIBE books;
```

### Insertar Datos
```sql
INSERT INTO books (titulo, autor, anio_publicacion, genero)
VALUES ('Orgullo y Prejuicio', 'Jane Austen', 1813, 'Romance');
```

### Actualizar Datos
```sql
UPDATE books 
SET genero = 'Novela distópica'
WHERE id = 4;
```

### Eliminar Datos
```sql
DELETE FROM books WHERE id = 5;
```

### Ver Estadísticas
```sql
-- Contar libros por género
SELECT genero, COUNT(*) as cantidad
FROM books
GROUP BY genero;

-- Libro más antiguo
SELECT * FROM books
ORDER BY anio_publicacion ASC
LIMIT 1;

-- Libros por autor
SELECT autor, GROUP_CONCAT(titulo) as libros
FROM books
GROUP BY autor;
```

---

## 🎨 Configuración Adicional (Opcional)

### Cambiar Color de la Conexión
Para identificar fácilmente tu conexión:
1. Click derecho en la conexión
2. **"Properties"**
3. Tab **"Options"**
4. Selecciona un **Color** (ej: azul para desarrollo)

### Configurar Auto-Commit
1. En las opciones de conexión
2. Tab **"Options"**
3. Desactiva **"Auto-commit"** si quieres control manual de transacciones

---

## 🔧 Solución de Problemas

### ❌ Error: "Can't connect to MySQL server"

**Causa**: MySQL no está corriendo

**Solución**:
```bash
# Verificar si MySQL está corriendo
brew services list | grep mysql

# Iniciar MySQL
brew services start mysql

# O manualmente
mysql.server start
```

### ❌ Error: "Access denied for user 'root'"

**Causa**: Contraseña incorrecta

**Solución**: Si configuraste contraseña en MySQL:
```bash
mysql -u root -p
# Ingresa tu contraseña
```

Luego en DataGrip, usa esa misma contraseña.

### ❌ Error: "Unknown database 'biblioteca_db'"

**Causa**: La base de datos no existe

**Solución**:
```bash
mysql -u root -e "CREATE DATABASE biblioteca_db;"
```

O desde DataGrip:
1. Conéctate sin especificar database
2. Ejecuta: `CREATE DATABASE biblioteca_db;`
3. Refresca y selecciona la base de datos

---

## 📱 Atajos de Teclado Útiles

| Atajo | Acción |
|-------|--------|
| `Cmd + Enter` (Mac) | Ejecutar query |
| `Ctrl + Enter` (Win) | Ejecutar query |
| `Cmd + N` | Nueva query console |
| `Cmd + D` | Duplicar línea |
| `F5` | Refrescar base de datos |
| `Cmd + /` | Comentar línea |

---

## 🎯 Comparar con la API

Puedes verificar que la API y DataGrip muestran los mismos datos:

### Desde la API:
```bash
curl http://localhost:8080/api/libros
```

### Desde DataGrip:
```sql
SELECT * FROM books;
```

**¡Los datos deben ser idénticos!** ✅

---

## 📊 Queries Útiles para el Proyecto

### Ver todos los libros ordenados por año
```sql
SELECT * FROM books
ORDER BY anio_publicacion DESC;
```

### Buscar libros por autor (como la API)
```sql
SELECT * FROM books
WHERE autor LIKE '%García Márquez%';
```

### Buscar por género (como la API)
```sql
SELECT * FROM books
WHERE genero = 'Novela';
```

### Libros publicados después de 1900
```sql
SELECT * FROM books
WHERE anio_publicacion > 1900;
```

### Contar libros por siglo
```sql
SELECT 
    CONCAT(FLOOR(anio_publicacion/100) + 1, '° siglo') as siglo,
    COUNT(*) as cantidad
FROM books
GROUP BY FLOOR(anio_publicacion/100);
```

---

## 🔄 Sincronización con la API

Recuerda que cualquier cambio que hagas en DataGrip se reflejará inmediatamente en la API:

1. **Insertas en DataGrip** → La API lo verá
2. **Actualizas en DataGrip** → La API lo verá
3. **Eliminas en DataGrip** → La API no lo encontrará

Y viceversa:

1. **POST en API** → Verás el nuevo registro en DataGrip
2. **PUT en API** → Verás los cambios en DataGrip
3. **DELETE en API** → El registro desaparecerá en DataGrip

**¡Están totalmente sincronizados!** 🔄

---

## ✅ Checklist de Conexión

- [ ] DataGrip instalado
- [ ] MySQL corriendo en localhost:3306
- [ ] Base de datos `biblioteca_db` creada
- [ ] Conexión configurada en DataGrip
- [ ] Driver MySQL descargado
- [ ] Test Connection: Exitoso
- [ ] Tabla `books` visible
- [ ] Datos visibles (4 libros)

---

## 📚 Recursos Adicionales

### Documentación DataGrip
- https://www.jetbrains.com/help/datagrip/

### MySQL con DataGrip
- https://www.jetbrains.com/help/datagrip/mysql.html

### SQL Queries
- https://www.mysqltutorial.org/

---

**¡Ahora puedes administrar tu base de datos visualmente con DataGrip!** 🎉

Si tienes algún problema, revisa la sección de **Solución de Problemas** arriba.

