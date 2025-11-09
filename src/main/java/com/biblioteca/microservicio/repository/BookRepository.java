package com.biblioteca.microservicio.repository;

import com.biblioteca.microservicio.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio optimizado para realizar operaciones CRUD sobre la entidad Book
 * Incluye métodos personalizados y paginación
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    // Métodos de búsqueda básicos con paginación
    Page<Book> findByAutorContainingIgnoreCase(String autor, Pageable pageable);
    Page<Book> findByGeneroContainingIgnoreCase(String genero, Pageable pageable);
    Page<Book> findByAnioPublicacion(Integer anio, Pageable pageable);
    
    // Métodos de búsqueda exacta (mantener compatibilidad)
    List<Book> findByAutor(String autor);
    List<Book> findByGenero(String genero);
    List<Book> findByAnioPublicacion(Integer anio);
    
    // Búsquedas avanzadas
    @Query("SELECT b FROM Book b WHERE " +
           "LOWER(b.titulo) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(b.autor) LIKE LOWER(CONCAT('%', :termino, '%')) OR " +
           "LOWER(b.genero) LIKE LOWER(CONCAT('%', :termino, '%'))")
    Page<Book> buscarPorTermino(@Param("termino") String termino, Pageable pageable);
    
    @Query("SELECT b FROM Book b WHERE b.anioPublicacion BETWEEN :anioInicio AND :anioFin")
    Page<Book> buscarPorRangoAnios(@Param("anioInicio") Integer anioInicio, 
                                  @Param("anioFin") Integer anioFin, 
                                  Pageable pageable);
    
    // Métodos de estadísticas
    @Query("SELECT COUNT(b) FROM Book b WHERE b.autor = :autor")
    Long contarPorAutor(@Param("autor") String autor);
    
    @Query("SELECT COUNT(b) FROM Book b WHERE b.genero = :genero")
    Long contarPorGenero(@Param("genero") String genero);
    
    @Query("SELECT DISTINCT b.autor FROM Book b ORDER BY b.autor")
    List<String> obtenerTodosLosAutores();
    
    @Query("SELECT DISTINCT b.genero FROM Book b ORDER BY b.genero")
    List<String> obtenerTodosLosGeneros();
    
    // Verificación de existencia optimizada
    boolean existsByTituloAndAutor(String titulo, String autor);
    
    // Búsqueda por título (case insensitive)
    Optional<Book> findByTituloIgnoreCase(String titulo);
}

