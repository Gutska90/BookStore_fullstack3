package com.laboratorios.laboratorios.repository;

import com.laboratorios.laboratorios.entity.Laboratorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {
    
    Page<Laboratorio> findByActivoTrue(Pageable pageable);
    
    List<Laboratorio> findByActivoTrue();
    
    List<Laboratorio> findByTipo(String tipo);
}

