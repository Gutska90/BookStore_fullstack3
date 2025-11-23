package com.laboratorios.laboratorios.service;

import com.laboratorios.laboratorios.dto.LaboratorioRequest;
import com.laboratorios.laboratorios.dto.LaboratorioResponse;
import com.laboratorios.laboratorios.entity.Laboratorio;
import com.laboratorios.laboratorios.exception.LaboratorioNotFoundException;
import com.laboratorios.laboratorios.repository.LaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LaboratorioService {
    
    private final LaboratorioRepository laboratorioRepository;
    
    @Autowired
    public LaboratorioService(LaboratorioRepository laboratorioRepository) {
        this.laboratorioRepository = laboratorioRepository;
    }
    
    public Page<LaboratorioResponse> obtenerTodosLosLaboratorios(Pageable pageable) {
        return laboratorioRepository.findAll(pageable)
                .map(this::toResponse);
    }
    
    public List<LaboratorioResponse> obtenerTodosLosLaboratorios() {
        return laboratorioRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public Page<LaboratorioResponse> obtenerLaboratoriosActivos(Pageable pageable) {
        return laboratorioRepository.findByActivoTrue(pageable)
                .map(this::toResponse);
    }
    
    public List<LaboratorioResponse> obtenerLaboratoriosActivos() {
        return laboratorioRepository.findByActivoTrue()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public LaboratorioResponse obtenerLaboratorioPorId(Long id) {
        Laboratorio laboratorio = laboratorioRepository.findById(id)
                .orElseThrow(() -> LaboratorioNotFoundException.withId(id));
        return toResponse(laboratorio);
    }
    
    public LaboratorioResponse crearLaboratorio(LaboratorioRequest request) {
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setNombre(request.getNombre());
        laboratorio.setDireccion(request.getDireccion());
        laboratorio.setTelefono(request.getTelefono());
        laboratorio.setTipo(request.getTipo());
        laboratorio.setActivo(true);
        
        Laboratorio savedLaboratorio = laboratorioRepository.save(laboratorio);
        return toResponse(savedLaboratorio);
    }
    
    public LaboratorioResponse actualizarLaboratorio(Long id, LaboratorioRequest request) {
        Laboratorio laboratorio = laboratorioRepository.findById(id)
                .orElseThrow(() -> LaboratorioNotFoundException.withId(id));
        
        laboratorio.setNombre(request.getNombre());
        laboratorio.setDireccion(request.getDireccion());
        laboratorio.setTelefono(request.getTelefono());
        laboratorio.setTipo(request.getTipo());
        
        Laboratorio savedLaboratorio = laboratorioRepository.save(laboratorio);
        return toResponse(savedLaboratorio);
    }
    
    public void eliminarLaboratorio(Long id) {
        if (!laboratorioRepository.existsById(id)) {
            throw LaboratorioNotFoundException.withId(id);
        }
        laboratorioRepository.deleteById(id);
    }
    
    private LaboratorioResponse toResponse(Laboratorio laboratorio) {
        return new LaboratorioResponse(
            laboratorio.getId(),
            laboratorio.getNombre(),
            laboratorio.getDireccion(),
            laboratorio.getTelefono(),
            laboratorio.getTipo(),
            laboratorio.getActivo(),
            laboratorio.getFechaCreacion(),
            laboratorio.getFechaActualizacion()
        );
    }
}

