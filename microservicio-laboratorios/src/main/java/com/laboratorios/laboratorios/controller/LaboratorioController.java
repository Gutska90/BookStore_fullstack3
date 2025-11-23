package com.laboratorios.laboratorios.controller;

import com.laboratorios.laboratorios.dto.LaboratorioRequest;
import com.laboratorios.laboratorios.dto.LaboratorioResponse;
import com.laboratorios.laboratorios.service.LaboratorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laboratorios")
@CrossOrigin(origins = "*")
@Tag(name = "Laboratorios", description = "API para gestión de laboratorios")
public class LaboratorioController {
    
    private final LaboratorioService laboratorioService;
    
    @Autowired
    public LaboratorioController(LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }
    
    @Operation(summary = "Obtener todos los laboratorios", description = "Retorna una lista paginada de todos los laboratorios")
    @GetMapping
    public ResponseEntity<Page<LaboratorioResponse>> obtenerTodosLosLaboratorios(
            @PageableDefault(size = 10, sort = "nombre") Pageable pageable) {
        Page<LaboratorioResponse> laboratorios = laboratorioService.obtenerTodosLosLaboratorios(pageable);
        return ResponseEntity.ok(laboratorios);
    }
    
    @Operation(summary = "Obtener todos los laboratorios (sin paginación)", description = "Retorna una lista completa de todos los laboratorios")
    @GetMapping("/all")
    public ResponseEntity<List<LaboratorioResponse>> obtenerTodosLosLaboratoriosSinPaginacion() {
        List<LaboratorioResponse> laboratorios = laboratorioService.obtenerTodosLosLaboratorios();
        return ResponseEntity.ok(laboratorios);
    }
    
    @Operation(summary = "Obtener laboratorios activos", description = "Retorna una lista paginada de solo los laboratorios activos")
    @GetMapping("/activos")
    public ResponseEntity<Page<LaboratorioResponse>> obtenerLaboratoriosActivos(
            @PageableDefault(size = 10, sort = "nombre") Pageable pageable) {
        Page<LaboratorioResponse> laboratorios = laboratorioService.obtenerLaboratoriosActivos(pageable);
        return ResponseEntity.ok(laboratorios);
    }
    
    @Operation(summary = "Obtener laboratorio por ID", description = "Retorna un laboratorio específico por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<LaboratorioResponse> obtenerLaboratorioPorId(@PathVariable Long id) {
        LaboratorioResponse laboratorio = laboratorioService.obtenerLaboratorioPorId(id);
        return ResponseEntity.ok(laboratorio);
    }
    
    @Operation(summary = "Crear nuevo laboratorio", description = "Crea un nuevo laboratorio en el sistema")
    @PostMapping
    public ResponseEntity<LaboratorioResponse> crearLaboratorio(@Valid @RequestBody LaboratorioRequest request) {
        LaboratorioResponse nuevoLaboratorio = laboratorioService.crearLaboratorio(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLaboratorio);
    }
    
    @Operation(summary = "Actualizar laboratorio", description = "Actualiza la información de un laboratorio existente")
    @PutMapping("/{id}")
    public ResponseEntity<LaboratorioResponse> actualizarLaboratorio(
            @PathVariable Long id, 
            @Valid @RequestBody LaboratorioRequest request) {
        LaboratorioResponse laboratorioActualizado = laboratorioService.actualizarLaboratorio(id, request);
        return ResponseEntity.ok(laboratorioActualizado);
    }
    
    @Operation(summary = "Eliminar laboratorio", description = "Elimina un laboratorio del sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLaboratorio(@PathVariable Long id) {
        laboratorioService.eliminarLaboratorio(id);
        return ResponseEntity.noContent().build();
    }
}

