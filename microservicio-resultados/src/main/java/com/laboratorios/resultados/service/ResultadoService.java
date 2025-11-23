package com.laboratorios.resultados.service;

import com.laboratorios.resultados.dto.ResultadoRequest;
import com.laboratorios.resultados.dto.ResultadoResponse;
import com.laboratorios.resultados.entity.ResultadoAnalisis;
import com.laboratorios.resultados.exception.ResultadoNotFoundException;
import com.laboratorios.resultados.repository.ResultadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResultadoService {
    
    private final ResultadoRepository resultadoRepository;
    
    @Autowired
    public ResultadoService(ResultadoRepository resultadoRepository) {
        this.resultadoRepository = resultadoRepository;
    }
    
    public Page<ResultadoResponse> obtenerTodosLosResultados(Pageable pageable) {
        return resultadoRepository.findAll(pageable)
                .map(this::toResponse);
    }
    
    public List<ResultadoResponse> obtenerTodosLosResultados() {
        return resultadoRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public ResultadoResponse obtenerResultadoPorId(Long id) {
        ResultadoAnalisis resultado = resultadoRepository.findById(id)
                .orElseThrow(() -> ResultadoNotFoundException.withId(id));
        return toResponse(resultado);
    }
    
    public Page<ResultadoResponse> obtenerResultadosPorPaciente(Long pacienteId, Pageable pageable) {
        return resultadoRepository.findByPacienteId(pacienteId, pageable)
                .map(this::toResponse);
    }
    
    public List<ResultadoResponse> obtenerResultadosPorPaciente(Long pacienteId) {
        return resultadoRepository.findByPacienteId(pacienteId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public Page<ResultadoResponse> obtenerResultadosPorLaboratorio(Long laboratorioId, Pageable pageable) {
        return resultadoRepository.findByLaboratorioId(laboratorioId, pageable)
                .map(this::toResponse);
    }
    
    public List<ResultadoResponse> obtenerResultadosPorLaboratorio(Long laboratorioId) {
        return resultadoRepository.findByLaboratorioId(laboratorioId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public ResultadoResponse crearResultado(ResultadoRequest request) {
        ResultadoAnalisis resultado = new ResultadoAnalisis();
        resultado.setPacienteId(request.getPacienteId());
        resultado.setLaboratorioId(request.getLaboratorioId());
        resultado.setTipoAnalisis(request.getTipoAnalisis());
        resultado.setResultado(request.getResultado());
        resultado.setObservaciones(request.getObservaciones());
        resultado.setFechaAnalisis(request.getFechaAnalisis() != null ? 
                                   request.getFechaAnalisis() : LocalDateTime.now());
        
        ResultadoAnalisis savedResultado = resultadoRepository.save(resultado);
        return toResponse(savedResultado);
    }
    
    public ResultadoResponse actualizarResultado(Long id, ResultadoRequest request) {
        ResultadoAnalisis resultado = resultadoRepository.findById(id)
                .orElseThrow(() -> ResultadoNotFoundException.withId(id));
        
        resultado.setPacienteId(request.getPacienteId());
        resultado.setLaboratorioId(request.getLaboratorioId());
        resultado.setTipoAnalisis(request.getTipoAnalisis());
        resultado.setResultado(request.getResultado());
        resultado.setObservaciones(request.getObservaciones());
        if (request.getFechaAnalisis() != null) {
            resultado.setFechaAnalisis(request.getFechaAnalisis());
        }
        
        ResultadoAnalisis savedResultado = resultadoRepository.save(resultado);
        return toResponse(savedResultado);
    }
    
    public void eliminarResultado(Long id) {
        if (!resultadoRepository.existsById(id)) {
            throw ResultadoNotFoundException.withId(id);
        }
        resultadoRepository.deleteById(id);
    }
    
    private ResultadoResponse toResponse(ResultadoAnalisis resultado) {
        return new ResultadoResponse(
            resultado.getId(),
            resultado.getPacienteId(),
            resultado.getLaboratorioId(),
            resultado.getTipoAnalisis(),
            resultado.getResultado(),
            resultado.getObservaciones(),
            resultado.getFechaAnalisis(),
            resultado.getFechaCreacion(),
            resultado.getFechaActualizacion()
        );
    }
}

