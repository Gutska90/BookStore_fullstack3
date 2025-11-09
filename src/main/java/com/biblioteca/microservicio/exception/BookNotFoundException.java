package com.biblioteca.microservicio.exception;

/**
 * Excepción personalizada para cuando un libro no es encontrado
 */
public class BookNotFoundException extends RuntimeException {
    
    public BookNotFoundException(String message) {
        super(message);
    }
    
    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public static BookNotFoundException withId(Long id) {
        return new BookNotFoundException("Libro no encontrado con ID: " + id);
    }
    
    public static BookNotFoundException withAutor(String autor) {
        return new BookNotFoundException("No se encontraron libros del autor: " + autor);
    }
    
    public static BookNotFoundException withGenero(String genero) {
        return new BookNotFoundException("No se encontraron libros del género: " + genero);
    }
    
    public static BookNotFoundException withAnio(Integer anio) {
        return new BookNotFoundException("No se encontraron libros del año: " + anio);
    }
}
