-- Script SQL para crear manualmente la tabla BOOKS en Oracle
-- (Opcional - Hibernate lo hace automáticamente con ddl-auto=update)

-- Crear secuencia para el ID
CREATE SEQUENCE BOOK_SEQ
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

-- Crear tabla BOOKS
CREATE TABLE BOOKS (
    ID NUMBER(19) PRIMARY KEY,
    TITULO VARCHAR2(200) NOT NULL,
    AUTOR VARCHAR2(100) NOT NULL,
    ANIO_PUBLICACION NUMBER(10) NOT NULL,
    GENERO VARCHAR2(50) NOT NULL
);

-- Insertar datos de ejemplo
INSERT INTO BOOKS (ID, TITULO, AUTOR, ANIO_PUBLICACION, GENERO)
VALUES (BOOK_SEQ.NEXTVAL, 'Cien años de soledad', 'Gabriel García Márquez', 1967, 'Realismo mágico');

INSERT INTO BOOKS (ID, TITULO, AUTOR, ANIO_PUBLICACION, GENERO)
VALUES (BOOK_SEQ.NEXTVAL, 'Don Quijote de la Mancha', 'Miguel de Cervantes', 1605, 'Novela');

INSERT INTO BOOKS (ID, TITULO, AUTOR, ANIO_PUBLICACION, GENERO)
VALUES (BOOK_SEQ.NEXTVAL, 'El Principito', 'Antoine de Saint-Exupéry', 1943, 'Fábula');

INSERT INTO BOOKS (ID, TITULO, AUTOR, ANIO_PUBLICACION, GENERO)
VALUES (BOOK_SEQ.NEXTVAL, '1984', 'George Orwell', 1949, 'Ciencia ficción');

INSERT INTO BOOKS (ID, TITULO, AUTOR, ANIO_PUBLICACION, GENERO)
VALUES (BOOK_SEQ.NEXTVAL, 'Orgullo y prejuicio', 'Jane Austen', 1813, 'Romance');

COMMIT;

-- Verificar datos insertados
SELECT * FROM BOOKS;

