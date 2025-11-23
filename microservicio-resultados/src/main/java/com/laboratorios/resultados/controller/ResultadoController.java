package com.laboratorios.resultados.controller;

import com.laboratorios.resultados.dto.ResultadoRequest;
import com.laboratorios.resultados.dto.ResultadoResponse;
import com.laboratorios.resultados.service.ResultadoService;
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
@RequestMapping("/api/resultados")
@CrossOrigin(origins = "*")
@Tag(name = "Resultados", description = "API para gestión de resultados de análisis")
public class ResultadoController {
    
    private final ResultadoService resultadoService;
    
    @Autowired
    public ResultadoController(ResultadoService resultadoService) {
        this.resultadoService = resultadoService;
    }
    
    @Operation(summary = "Obtener todos los resultados", description = "Retorna una lista paginada de todos los resultados de análisis")
    @GetMapping
    public ResponseEntity<Page<ResultadoResponse>> obtenerTodosLosResultados(
            @PageableDefault(size = 10, sort = "fechaAnalisis") Pageable pageable) {
        Page<ResultadoResponse> resultados = resultadoService.obtenerTodosLosResultados(pageable);
        return ResponseEntity.ok(resultados);
    }
    
    @Operation(summary = "Obtener todos los resultados (sin paginación)", description = "Retorna una lista completa de todos los resultados")
    @GetMapping("/all")
    public ResponseEntity<List<ResultadoResponse>> obtenerTodosLosResultadosSinPaginacion() {
        List<ResultadoResponse> resultados = resultadoService.obtenerTodosLosResultados();
        return ResponseEntity.ok(resultados);
    }
    
    @Operation(summary = "Obtener resultado por ID", description = "Retorna un resultado específico por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<ResultadoResponse> obtenerResultadoPorId(@PathVariable Long id) {
        ResultadoResponse resultado = resultadoService.obtenerResultadoPorId(id);
        return ResponseEntity.ok(resultado);
    }
    
    @Operation(summary = "Obtener resultados por paciente", description = "Retorna una lista paginada de resultados de un paciente específico")
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<Page<ResultadoResponse>> obtenerResultadosPorPaciente(
            @PathVariable Long pacienteId,
            @PageableDefault(size = 10, sort = "fechaAnalisis") Pageable pageable) {
        Page<ResultadoResponse> resultados = resultadoService.obtenerResultadosPorPaciente(pacienteId, pageable);
        return ResponseEntity.ok(resultados);
    }
    
    @Operation(summary = "Obtener resultados por laboratorio", description = "Retorna una lista paginada de resultados de un laboratorio específico")
    @GetMapping("/laboratorio/{laboratorioId}")
    public ResponseEntity<Page<ResultadoResponse>> obtenerResultadosPorLaboratorio(
            @PathVariable Long laboratorioId,
            @PageableDefault(size = 10, sort = "fechaAnalisis") Pageable pageable) {
        Page<ResultadoResponse> resultados = resultadoService.obtenerResultadosPorLaboratorio(laboratorioId, pageable);
        return ResponseEntity.ok(resultados);
    }
    
    @Operation(summary = "Crear nuevo resultado", description = "Crea un nuevo resultado de análisis en el sistema")
    @PostMapping
    public ResponseEntity<ResultadoResponse> crearResultado(@Valid @RequestBody ResultadoRequest request) {
        ResultadoResponse nuevoResultado = resultadoService.crearResultado(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoResultado);
    }
    
    @Operation(summary = "Actualizar resultado", description = "Actualiza la información de un resultado existente")
    @PutMapping("/{id}")
    public ResponseEntity<ResultadoResponse> actualizarResultado(
            @PathVariable Long id, 
            @Valid @RequestBody ResultadoRequest request) {
        ResultadoResponse resultadoActualizado = resultadoService.actualizarResultado(id, request);
        return ResponseEntity.ok(resultadoActualizado);
    }
    
    @Operation(summary = "Eliminar resultado", description = "Elimina un resultado del sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResultado(@PathVariable Long id) {
        resultadoService.eliminarResultado(id);
        return ResponseEntity.noContent().build();
    }
}

