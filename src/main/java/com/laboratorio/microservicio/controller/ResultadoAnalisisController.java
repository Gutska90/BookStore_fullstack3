package com.laboratorio.microservicio.controller;

import com.laboratorio.microservicio.dto.CreateResultadoAnalisisRequest;
import com.laboratorio.microservicio.dto.ResultadoAnalisisResponse;
import com.laboratorio.microservicio.service.ResultadoAnalisisService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador REST para gestión de resultados de análisis
 */
@RestController
@RequestMapping("/api/resultados")
@CrossOrigin(origins = "*")
public class ResultadoAnalisisController {

    private static final Logger log = LoggerFactory.getLogger(ResultadoAnalisisController.class);

    private final ResultadoAnalisisService resultadoService;

    @Autowired
    public ResultadoAnalisisController(ResultadoAnalisisService resultadoService) {
        this.resultadoService = resultadoService;
    }

    /**
     * POST: Crear un nuevo resultado
     * Endpoint: POST /api/resultados
     */
    @PostMapping
    public ResponseEntity<ResultadoAnalisisResponse> crearResultado(
            @Valid @RequestBody CreateResultadoAnalisisRequest request) {
        log.debug("Solicitud POST para crear resultado: {}", request.getTipoAnalisis());
        ResultadoAnalisisResponse nuevoResultado = resultadoService.crearResultado(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoResultado);
    }

    /**
     * GET: Obtener todos los resultados con paginación
     * Endpoint: GET /api/resultados?page=0&size=10
     */
    @GetMapping
    public ResponseEntity<Page<ResultadoAnalisisResponse>> obtenerTodosLosResultados(
            @PageableDefault(size = 10, sort = "fechaRealizacion") Pageable pageable) {
        log.debug("Solicitud GET para obtener todos los resultados con paginación");
        Page<ResultadoAnalisisResponse> resultados = resultadoService.obtenerTodosLosResultados(pageable);
        return ResponseEntity.ok(resultados);
    }

    /**
     * GET: Obtener un resultado por su ID
     * Endpoint: GET /api/resultados/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ResultadoAnalisisResponse> obtenerResultadoPorId(@PathVariable Long id) {
        log.debug("Solicitud GET para obtener resultado con ID: {}", id);
        ResultadoAnalisisResponse resultado = resultadoService.obtenerResultadoPorId(id);
        return ResponseEntity.ok(resultado);
    }

    /**
     * PUT: Actualizar un resultado existente
     * Endpoint: PUT /api/resultados/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ResultadoAnalisisResponse> actualizarResultado(
            @PathVariable Long id,
            @Valid @RequestBody CreateResultadoAnalisisRequest request) {
        log.debug("Solicitud PUT para actualizar resultado con ID: {}", id);
        ResultadoAnalisisResponse resultadoActualizado = resultadoService.actualizarResultado(id, request);
        return ResponseEntity.ok(resultadoActualizado);
    }

    /**
     * DELETE: Eliminar un resultado por su ID
     * Endpoint: DELETE /api/resultados/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarResultado(@PathVariable Long id) {
        log.debug("Solicitud DELETE para eliminar resultado con ID: {}", id);
        resultadoService.eliminarResultado(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET: Buscar resultados por usuario
     * Endpoint: GET /api/resultados/usuario/{usuarioId}
     */
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<ResultadoAnalisisResponse>> buscarPorUsuario(@PathVariable Long usuarioId) {
        log.debug("Solicitud GET para buscar resultados por usuario ID: {}", usuarioId);
        List<ResultadoAnalisisResponse> resultados = resultadoService.buscarPorUsuario(usuarioId);
        return ResponseEntity.ok(resultados);
    }

    /**
     * GET: Buscar resultados por laboratorio
     * Endpoint: GET /api/resultados/laboratorio/{laboratorioId}
     */
    @GetMapping("/laboratorio/{laboratorioId}")
    public ResponseEntity<List<ResultadoAnalisisResponse>> buscarPorLaboratorio(@PathVariable Long laboratorioId) {
        log.debug("Solicitud GET para buscar resultados por laboratorio ID: {}", laboratorioId);
        List<ResultadoAnalisisResponse> resultados = resultadoService.buscarPorLaboratorio(laboratorioId);
        return ResponseEntity.ok(resultados);
    }

    /**
     * GET: Buscar resultados por estado
     * Endpoint: GET /api/resultados/estado/{estado}
     */
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ResultadoAnalisisResponse>> buscarPorEstado(@PathVariable String estado) {
        log.debug("Solicitud GET para buscar resultados por estado: {}", estado);
        List<ResultadoAnalisisResponse> resultados = resultadoService.buscarPorEstado(estado);
        return ResponseEntity.ok(resultados);
    }

    /**
     * GET: Buscar resultados por tipo de análisis
     * Endpoint: GET /api/resultados/tipo?tipo=Hemograma
     */
    @GetMapping("/tipo")
    public ResponseEntity<List<ResultadoAnalisisResponse>> buscarPorTipoAnalisis(
            @RequestParam String tipo) {
        log.debug("Solicitud GET para buscar resultados por tipo: {}", tipo);
        List<ResultadoAnalisisResponse> resultados = resultadoService.buscarPorTipoAnalisis(tipo);
        return ResponseEntity.ok(resultados);
    }

    /**
     * GET: Buscar resultados por rango de fechas
     * Endpoint: GET /api/resultados/fechas?inicio=2024-10-01&fin=2024-10-31
     */
    @GetMapping("/fechas")
    public ResponseEntity<List<ResultadoAnalisisResponse>> buscarPorRangoFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        log.debug("Solicitud GET para buscar resultados por rango de fechas: {} - {}", inicio, fin);
        List<ResultadoAnalisisResponse> resultados = resultadoService.buscarPorRangoFechas(inicio, fin);
        return ResponseEntity.ok(resultados);
    }
}

