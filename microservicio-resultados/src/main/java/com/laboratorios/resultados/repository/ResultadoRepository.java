package com.laboratorios.resultados.repository;

import com.laboratorios.resultados.entity.ResultadoAnalisis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultadoRepository extends JpaRepository<ResultadoAnalisis, Long> {
    
    Page<ResultadoAnalisis> findByPacienteId(Long pacienteId, Pageable pageable);
    
    List<ResultadoAnalisis> findByPacienteId(Long pacienteId);
    
    Page<ResultadoAnalisis> findByLaboratorioId(Long laboratorioId, Pageable pageable);
    
    List<ResultadoAnalisis> findByLaboratorioId(Long laboratorioId);
    
    List<ResultadoAnalisis> findByPacienteIdAndLaboratorioId(Long pacienteId, Long laboratorioId);
}

