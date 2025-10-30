package com.laboratorio.microservicio.repository;

import com.laboratorio.microservicio.entity.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para operaciones CRUD sobre la entidad Laboratorio
 */
@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {

    /**
     * Buscar laboratorios por estado
     */
    List<Laboratorio> findByEstado(String estado);

    /**
     * Buscar laboratorios por nombre (case insensitive)
     */
    List<Laboratorio> findByNombreContainingIgnoreCase(String nombre);

    /**
     * Contar laboratorios activos
     */
    @Query("SELECT COUNT(l) FROM Laboratorio l WHERE l.estado = 'ACTIVO'")
    Long countLaboratoriosActivos();
}

