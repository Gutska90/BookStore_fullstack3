package com.biblioteca.microservicio.controller;

import com.biblioteca.microservicio.dto.BookResponse;
import com.biblioteca.microservicio.dto.CreateBookRequest;
import com.biblioteca.microservicio.dto.UpdateBookRequest;
import com.biblioteca.microservicio.service.BookService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST optimizado para manejar las solicitudes HTTP relacionadas con los libros
 * Incluye paginación, DTOs y mejor manejo de errores
 */
@RestController
@RequestMapping("/api/libros")
@CrossOrigin(origins = "*")
public class BookController {
    
    private static final Logger log = LoggerFactory.getLogger(BookController.class);
    
    private final BookService bookService;
    
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    /**
     * GET: Obtener todos los libros con paginación
     * Endpoint: GET /api/libros?page=0&size=10&sort=titulo,asc
     */
    @GetMapping
    public ResponseEntity<Page<BookResponse>> obtenerTodosLosLibros(
            @PageableDefault(size = 10, sort = "titulo") Pageable pageable) {
        log.debug("Solicitud GET para obtener todos los libros con paginación: {}", pageable);
        Page<BookResponse> libros = bookService.obtenerTodosLosLibros(pageable);
        return ResponseEntity.ok(libros);
    }
    
    /**
     * GET: Obtener todos los libros (sin paginación - mantener compatibilidad)
     * Endpoint: GET /api/libros/all
     */
    @GetMapping("/all")
    public ResponseEntity<List<BookResponse>> obtenerTodosLosLibrosSinPaginacion() {
        log.debug("Solicitud GET para obtener todos los libros sin paginación");
        List<BookResponse> libros = bookService.obtenerTodosLosLibros();
        return ResponseEntity.ok(libros);
    }
    
    /**
     * GET: Obtener un libro por su ID
     * Endpoint: GET /api/libros/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> obtenerLibroPorId(@PathVariable Long id) {
        log.debug("Solicitud GET para obtener libro con ID: {}", id);
        BookResponse libro = bookService.obtenerLibroPorId(id);
        return ResponseEntity.ok(libro);
    }
    
    /**
     * POST: Crear un nuevo libro
     * Endpoint: POST /api/libros
     */
    @PostMapping
    public ResponseEntity<BookResponse> crearLibro(@Valid @RequestBody CreateBookRequest request) {
        log.debug("Solicitud POST para crear libro: {}", request.getTitulo());
        BookResponse nuevoLibro = bookService.crearLibro(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLibro);
    }
    
    /**
     * PUT: Actualizar un libro existente
     * Endpoint: PUT /api/libros/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> actualizarLibro(
            @PathVariable Long id, 
            @Valid @RequestBody UpdateBookRequest request) {
        log.debug("Solicitud PUT para actualizar libro con ID: {}", id);
        BookResponse libroActualizado = bookService.actualizarLibro(id, request);
        return ResponseEntity.ok(libroActualizado);
    }
    
    /**
     * DELETE: Eliminar un libro por su ID
     * Endpoint: DELETE /api/libros/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable Long id) {
        log.debug("Solicitud DELETE para eliminar libro con ID: {}", id);
        bookService.eliminarLibro(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * GET: Buscar libros por autor con paginación
     * Endpoint: GET /api/libros/autor/{autor}?page=0&size=10
     */
    @GetMapping("/autor/{autor}")
    public ResponseEntity<Page<BookResponse>> buscarPorAutor(
            @PathVariable String autor,
            @PageableDefault(size = 10, sort = "titulo") Pageable pageable) {
        log.debug("Solicitud GET para buscar libros por autor: {} con paginación", autor);
        Page<BookResponse> libros = bookService.buscarPorAutor(autor, pageable);
        return ResponseEntity.ok(libros);
    }
    
    /**
     * GET: Buscar libros por autor (sin paginación - mantener compatibilidad)
     * Endpoint: GET /api/libros/autor/{autor}/all
     */
    @GetMapping("/autor/{autor}/all")
    public ResponseEntity<List<BookResponse>> buscarPorAutorSinPaginacion(@PathVariable String autor) {
        log.debug("Solicitud GET para buscar libros por autor: {} sin paginación", autor);
        List<BookResponse> libros = bookService.buscarPorAutor(autor);
        return ResponseEntity.ok(libros);
    }
    
    /**
     * GET: Buscar libros por género con paginación
     * Endpoint: GET /api/libros/genero/{genero}?page=0&size=10
     */
    @GetMapping("/genero/{genero}")
    public ResponseEntity<Page<BookResponse>> buscarPorGenero(
            @PathVariable String genero,
            @PageableDefault(size = 10, sort = "titulo") Pageable pageable) {
        log.debug("Solicitud GET para buscar libros por género: {} con paginación", genero);
        Page<BookResponse> libros = bookService.buscarPorGenero(genero, pageable);
        return ResponseEntity.ok(libros);
    }
    
    /**
     * GET: Buscar libros por género (sin paginación - mantener compatibilidad)
     * Endpoint: GET /api/libros/genero/{genero}/all
     */
    @GetMapping("/genero/{genero}/all")
    public ResponseEntity<List<BookResponse>> buscarPorGeneroSinPaginacion(@PathVariable String genero) {
        log.debug("Solicitud GET para buscar libros por género: {} sin paginación", genero);
        List<BookResponse> libros = bookService.buscarPorGenero(genero);
        return ResponseEntity.ok(libros);
    }
    
    /**
     * GET: Buscar libros por año de publicación con paginación
     * Endpoint: GET /api/libros/anio/{anio}?page=0&size=10
     */
    @GetMapping("/anio/{anio}")
    public ResponseEntity<Page<BookResponse>> buscarPorAnio(
            @PathVariable Integer anio,
            @PageableDefault(size = 10, sort = "titulo") Pageable pageable) {
        log.debug("Solicitud GET para buscar libros por año: {} con paginación", anio);
        Page<BookResponse> libros = bookService.buscarPorAnio(anio, pageable);
        return ResponseEntity.ok(libros);
    }
    
    /**
     * GET: Buscar libros por año (sin paginación - mantener compatibilidad)
     * Endpoint: GET /api/libros/anio/{anio}/all
     */
    @GetMapping("/anio/{anio}/all")
    public ResponseEntity<List<BookResponse>> buscarPorAnioSinPaginacion(@PathVariable Integer anio) {
        log.debug("Solicitud GET para buscar libros por año: {} sin paginación", anio);
        List<BookResponse> libros = bookService.buscarPorAnio(anio);
        return ResponseEntity.ok(libros);
    }
    
    /**
     * GET: Búsqueda avanzada por término
     * Endpoint: GET /api/libros/buscar?termino=harry&page=0&size=10
     */
    @GetMapping("/buscar")
    public ResponseEntity<Page<BookResponse>> buscarPorTermino(
            @RequestParam String termino,
            @PageableDefault(size = 10, sort = "titulo") Pageable pageable) {
        log.debug("Solicitud GET para búsqueda por término: {} con paginación", termino);
        Page<BookResponse> libros = bookService.buscarPorTermino(termino, pageable);
        return ResponseEntity.ok(libros);
    }
    
    /**
     * GET: Buscar libros por rango de años
     * Endpoint: GET /api/libros/rango-anios?inicio=1900&fin=2000&page=0&size=10
     */
    @GetMapping("/rango-anios")
    public ResponseEntity<Page<BookResponse>> buscarPorRangoAnios(
            @RequestParam Integer inicio,
            @RequestParam Integer fin,
            @PageableDefault(size = 10, sort = "anioPublicacion") Pageable pageable) {
        log.debug("Solicitud GET para buscar libros por rango de años: {} - {} con paginación", inicio, fin);
        Page<BookResponse> libros = bookService.buscarPorRangoAnios(inicio, fin, pageable);
        return ResponseEntity.ok(libros);
    }
    
    /**
     * GET: Obtener lista de todos los autores
     * Endpoint: GET /api/libros/autores
     */
    @GetMapping("/autores")
    public ResponseEntity<List<String>> obtenerTodosLosAutores() {
        log.debug("Solicitud GET para obtener lista de autores");
        List<String> autores = bookService.obtenerTodosLosAutores();
        return ResponseEntity.ok(autores);
    }
    
    /**
     * GET: Obtener lista de todos los géneros
     * Endpoint: GET /api/libros/generos
     */
    @GetMapping("/generos")
    public ResponseEntity<List<String>> obtenerTodosLosGeneros() {
        log.debug("Solicitud GET para obtener lista de géneros");
        List<String> generos = bookService.obtenerTodosLosGeneros();
        return ResponseEntity.ok(generos);
    }
}

