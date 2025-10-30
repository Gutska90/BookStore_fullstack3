package com.laboratorio.microservicio.controller;

import com.laboratorio.microservicio.entity.Laboratorio;
import com.laboratorio.microservicio.repository.LaboratorioRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestión de laboratorios
 */
@RestController
@RequestMapping("/api/laboratorios")
@CrossOrigin(origins = "*")
public class LaboratorioController {

    private static final Logger log = LoggerFactory.getLogger(LaboratorioController.class);

    private final LaboratorioRepository laboratorioRepository;

    @Autowired
    public LaboratorioController(LaboratorioRepository laboratorioRepository) {
        this.laboratorioRepository = laboratorioRepository;
    }

    /**
     * POST: Crear un nuevo laboratorio
     */
    @PostMapping
    public ResponseEntity<Laboratorio> crearLaboratorio(@RequestBody Laboratorio laboratorio) {
        log.debug("Solicitud POST para crear laboratorio: {}", laboratorio.getNombre());
        if (laboratorio.getEstado() == null || laboratorio.getEstado().isEmpty()) {
            laboratorio.setEstado("ACTIVO");
        }
        Laboratorio nuevoLaboratorio = laboratorioRepository.save(laboratorio);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoLaboratorio);
    }

    /**
     * GET: Obtener todos los laboratorios
     */
    @GetMapping
    public ResponseEntity<List<Laboratorio>> obtenerTodosLosLaboratorios() {
        log.debug("Solicitud GET para obtener todos los laboratorios");
        List<Laboratorio> laboratorios = laboratorioRepository.findAll();
        return ResponseEntity.ok(laboratorios);
    }

    /**
     * GET: Obtener un laboratorio por su ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Laboratorio> obtenerLaboratorioPorId(@PathVariable Long id) {
        log.debug("Solicitud GET para obtener laboratorio con ID: {}", id);
        return laboratorioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

