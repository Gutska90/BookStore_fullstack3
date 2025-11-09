package com.biblioteca.microservicio.service;

import com.biblioteca.microservicio.dto.BookResponse;
import com.biblioteca.microservicio.dto.CreateBookRequest;
import com.biblioteca.microservicio.dto.UpdateBookRequest;
import com.biblioteca.microservicio.entity.Book;
import com.biblioteca.microservicio.exception.BookNotFoundException;
import com.biblioteca.microservicio.mapper.BookMapper;
import com.biblioteca.microservicio.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio optimizado que contiene la lógica de negocio para gestionar libros
 * Incluye caché, paginación y mejor manejo de excepciones
 */
@Service
@Transactional
public class BookService {
    
    private static final Logger log = LoggerFactory.getLogger(BookService.class);
    
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    
    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }
    
    /**
     * Obtener todos los libros con paginación
     */
    @Transactional(readOnly = true)
    public Page<BookResponse> obtenerTodosLosLibros(Pageable pageable) {
        log.debug("Obteniendo todos los libros con paginación: {}", pageable);
        return bookRepository.findAll(pageable)
                .map(bookMapper::toResponse);
    }
    
    /**
     * Obtener todos los libros (sin paginación - mantener compatibilidad)
     */
    @Transactional(readOnly = true)
    @Cacheable(value = "books", key = "'all'")
    public List<BookResponse> obtenerTodosLosLibros() {
        log.debug("Obteniendo todos los libros");
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    /**
     * Obtener un libro por su ID
     */
    @Transactional(readOnly = true)
    @Cacheable(value = "books", key = "#id")
    public BookResponse obtenerLibroPorId(Long id) {
        log.debug("Obteniendo libro con ID: {}", id);
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> BookNotFoundException.withId(id));
        return bookMapper.toResponse(book);
    }
    
    /**
     * Crear un nuevo libro
     */
    @CacheEvict(value = "books", allEntries = true)
    public BookResponse crearLibro(CreateBookRequest request) {
        log.debug("Creando nuevo libro: {}", request.getTitulo());
        
        // Verificar si ya existe un libro con el mismo título y autor
        if (bookRepository.existsByTituloAndAutor(request.getTitulo(), request.getAutor())) {
            throw new IllegalArgumentException("Ya existe un libro con el mismo título y autor");
        }
        
        Book book = bookMapper.toEntity(request);
        Book savedBook = bookRepository.save(book);
        log.info("Libro creado exitosamente con ID: {}", savedBook.getId());
        return bookMapper.toResponse(savedBook);
    }
    
    /**
     * Actualizar un libro existente
     */
    @CacheEvict(value = "books", allEntries = true)
    public BookResponse actualizarLibro(Long id, UpdateBookRequest request) {
        log.debug("Actualizando libro con ID: {}", id);
        
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> BookNotFoundException.withId(id));
        
        // Verificar si el nuevo título y autor ya existen en otro libro
        if (!existingBook.getTitulo().equals(request.getTitulo()) || 
            !existingBook.getAutor().equals(request.getAutor())) {
            if (bookRepository.existsByTituloAndAutor(request.getTitulo(), request.getAutor())) {
                throw new IllegalArgumentException("Ya existe otro libro con el mismo título y autor");
            }
        }
        
        Book updatedBook = bookMapper.toEntity(request, existingBook);
        Book savedBook = bookRepository.save(updatedBook);
        log.info("Libro actualizado exitosamente con ID: {}", savedBook.getId());
        return bookMapper.toResponse(savedBook);
    }
    
    /**
     * Eliminar un libro por su ID
     */
    @CacheEvict(value = "books", allEntries = true)
    public void eliminarLibro(Long id) {
        log.debug("Eliminando libro con ID: {}", id);
        
        if (!bookRepository.existsById(id)) {
            throw BookNotFoundException.withId(id);
        }
        
        bookRepository.deleteById(id);
        log.info("Libro eliminado exitosamente con ID: {}", id);
    }
    
    /**
     * Buscar libros por autor con paginación
     */
    @Transactional(readOnly = true)
    public Page<BookResponse> buscarPorAutor(String autor, Pageable pageable) {
        log.debug("Buscando libros por autor: {} con paginación", autor);
        return bookRepository.findByAutorContainingIgnoreCase(autor, pageable)
                .map(bookMapper::toResponse);
    }
    
    /**
     * Buscar libros por autor (sin paginación - mantener compatibilidad)
     */
    @Transactional(readOnly = true)
    public List<BookResponse> buscarPorAutor(String autor) {
        log.debug("Buscando libros por autor: {}", autor);
        List<Book> books = bookRepository.findByAutor(autor);
        if (books.isEmpty()) {
            throw BookNotFoundException.withAutor(autor);
        }
        return books.stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    /**
     * Buscar libros por género con paginación
     */
    @Transactional(readOnly = true)
    public Page<BookResponse> buscarPorGenero(String genero, Pageable pageable) {
        log.debug("Buscando libros por género: {} con paginación", genero);
        return bookRepository.findByGeneroContainingIgnoreCase(genero, pageable)
                .map(bookMapper::toResponse);
    }
    
    /**
     * Buscar libros por género (sin paginación - mantener compatibilidad)
     */
    @Transactional(readOnly = true)
    public List<BookResponse> buscarPorGenero(String genero) {
        log.debug("Buscando libros por género: {}", genero);
        List<Book> books = bookRepository.findByGenero(genero);
        if (books.isEmpty()) {
            throw BookNotFoundException.withGenero(genero);
        }
        return books.stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    /**
     * Buscar libros por año con paginación
     */
    @Transactional(readOnly = true)
    public Page<BookResponse> buscarPorAnio(Integer anio, Pageable pageable) {
        log.debug("Buscando libros por año: {} con paginación", anio);
        return bookRepository.findByAnioPublicacion(anio, pageable)
                .map(bookMapper::toResponse);
    }
    
    /**
     * Buscar libros por año (sin paginación - mantener compatibilidad)
     */
    @Transactional(readOnly = true)
    public List<BookResponse> buscarPorAnio(Integer anio) {
        log.debug("Buscando libros por año: {}", anio);
        List<Book> books = bookRepository.findByAnioPublicacion(anio);
        if (books.isEmpty()) {
            throw BookNotFoundException.withAnio(anio);
        }
        return books.stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    /**
     * Búsqueda avanzada por término
     */
    @Transactional(readOnly = true)
    public Page<BookResponse> buscarPorTermino(String termino, Pageable pageable) {
        log.debug("Buscando libros por término: {} con paginación", termino);
        return bookRepository.buscarPorTermino(termino, pageable)
                .map(bookMapper::toResponse);
    }
    
    /**
     * Buscar libros por rango de años
     */
    @Transactional(readOnly = true)
    public Page<BookResponse> buscarPorRangoAnios(Integer anioInicio, Integer anioFin, Pageable pageable) {
        log.debug("Buscando libros por rango de años: {} - {} con paginación", anioInicio, anioFin);
        return bookRepository.buscarPorRangoAnios(anioInicio, anioFin, pageable)
                .map(bookMapper::toResponse);
    }
    
    /**
     * Obtener estadísticas de la biblioteca
     */
    @Transactional(readOnly = true)
    public List<String> obtenerTodosLosAutores() {
        log.debug("Obteniendo lista de todos los autores");
        return bookRepository.obtenerTodosLosAutores();
    }
    
    @Transactional(readOnly = true)
    public List<String> obtenerTodosLosGeneros() {
        log.debug("Obteniendo lista de todos los géneros");
        return bookRepository.obtenerTodosLosGeneros();
    }
}

