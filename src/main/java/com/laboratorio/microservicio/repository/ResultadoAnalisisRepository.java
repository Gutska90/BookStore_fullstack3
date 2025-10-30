package com.laboratorio.microservicio.repository;

import com.laboratorio.microservicio.entity.ResultadoAnalisis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio para operaciones CRUD sobre la entidad ResultadoAnalisis
 */
@Repository
public interface ResultadoAnalisisRepository extends JpaRepository<ResultadoAnalisis, Long> {

    /**
     * Buscar resultados por usuario
     */
    List<ResultadoAnalisis> findByUsuarioId(Long usuarioId);

    /**
     * Buscar resultados por usuario con paginación
     */
    Page<ResultadoAnalisis> findByUsuarioId(Long usuarioId, Pageable pageable);

    /**
     * Buscar resultados por laboratorio
     */
    List<ResultadoAnalisis> findByLaboratorioId(Long laboratorioId);

    /**
     * Buscar resultados por laboratorio con paginación
     */
    Page<ResultadoAnalisis> findByLaboratorioId(Long laboratorioId, Pageable pageable);

    /**
     * Buscar resultados por estado
     */
    List<ResultadoAnalisis> findByEstado(String estado);

    /**
     * Buscar resultados por tipo de análisis
     */
    List<ResultadoAnalisis> findByTipoAnalisisContainingIgnoreCase(String tipoAnalisis);

    /**
     * Buscar resultados por rango de fechas
     */
    @Query("SELECT r FROM ResultadoAnalisis r WHERE r.fechaRealizacion BETWEEN :fechaInicio AND :fechaFin")
    List<ResultadoAnalisis> findByFechaRealizacionBetween(@Param("fechaInicio") LocalDate fechaInicio,
                                                         @Param("fechaFin") LocalDate fechaFin);

    /**
     * Buscar resultados por usuario y laboratorio
     */
    @Query("SELECT r FROM ResultadoAnalisis r WHERE r.usuario.id = :usuarioId AND r.laboratorio.id = :laboratorioId")
    List<ResultadoAnalisis> findByUsuarioAndLaboratorio(@Param("usuarioId") Long usuarioId,
                                                         @Param("laboratorioId") Long laboratorioId);

    /**
     * Contar resultados por estado
     */
    @Query("SELECT COUNT(r) FROM ResultadoAnalisis r WHERE r.estado = :estado")
    Long countByEstado(@Param("estado") String estado);
}

