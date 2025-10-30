package com.laboratorio.microservicio.service;

import com.laboratorio.microservicio.dto.CreateResultadoAnalisisRequest;
import com.laboratorio.microservicio.dto.ResultadoAnalisisResponse;
import com.laboratorio.microservicio.entity.Laboratorio;
import com.laboratorio.microservicio.entity.ResultadoAnalisis;
import com.laboratorio.microservicio.entity.Usuario;
import com.laboratorio.microservicio.exception.LaboratorioNotFoundException;
import com.laboratorio.microservicio.exception.ResultadoAnalisisNotFoundException;
import com.laboratorio.microservicio.mapper.ResultadoAnalisisMapper;
import com.laboratorio.microservicio.repository.LaboratorioRepository;
import com.laboratorio.microservicio.repository.ResultadoAnalisisRepository;
import com.laboratorio.microservicio.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para gestión de resultados de análisis
 */
@Service
@Transactional
public class ResultadoAnalisisService {

    private static final Logger log = LoggerFactory.getLogger(ResultadoAnalisisService.class);

    private final ResultadoAnalisisRepository resultadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final LaboratorioRepository laboratorioRepository;
    private final ResultadoAnalisisMapper resultadoMapper;

    @Autowired
    public ResultadoAnalisisService(ResultadoAnalisisRepository resultadoRepository,
                                   UsuarioRepository usuarioRepository,
                                   LaboratorioRepository laboratorioRepository,
                                   ResultadoAnalisisMapper resultadoMapper) {
        this.resultadoRepository = resultadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.laboratorioRepository = laboratorioRepository;
        this.resultadoMapper = resultadoMapper;
    }

    /**
     * Crear nuevo resultado de análisis
     */
    @CacheEvict(value = "resultados", allEntries = true)
    public ResultadoAnalisisResponse crearResultado(CreateResultadoAnalisisRequest request) {
        log.debug("Creando nuevo resultado de análisis: {}", request.getTipoAnalisis());

        // Validar que el usuario existe
        Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + request.getUsuarioId()));

        // Validar que el laboratorio existe
        Laboratorio laboratorio = laboratorioRepository.findById(request.getLaboratorioId())
                .orElseThrow(() -> LaboratorioNotFoundException.withId(request.getLaboratorioId()));

        // Crear resultado
        ResultadoAnalisis resultado = new ResultadoAnalisis(
                usuario,
                laboratorio,
                request.getTipoAnalisis(),
                request.getFechaRealizacion()
        );

        if (request.getFechaResultado() != null) {
            resultado.setFechaResultado(request.getFechaResultado());
        }
        if (request.getValores() != null) {
            resultado.setValores(request.getValores());
        }
        if (request.getObservaciones() != null) {
            resultado.setObservaciones(request.getObservaciones());
        }
        if (request.getEstado() != null) {
            resultado.setEstado(request.getEstado());
        } else {
            resultado.setEstado("PENDIENTE");
        }

        ResultadoAnalisis resultadoGuardado = resultadoRepository.save(resultado);
        log.info("Resultado creado exitosamente con ID: {}", resultadoGuardado.getId());

        return resultadoMapper.toResponse(resultadoGuardado);
    }

    /**
     * Obtener todos los resultados con paginación
     */
    @Transactional(readOnly = true)
    public Page<ResultadoAnalisisResponse> obtenerTodosLosResultados(Pageable pageable) {
        log.debug("Obteniendo todos los resultados con paginación: {}", pageable);
        return resultadoRepository.findAll(pageable)
                .map(resultadoMapper::toResponse);
    }

    /**
     * Obtener resultado por ID
     */
    @Transactional(readOnly = true)
    @Cacheable(value = "resultados", key = "#id")
    public ResultadoAnalisisResponse obtenerResultadoPorId(Long id) {
        log.debug("Obteniendo resultado con ID: {}", id);
        ResultadoAnalisis resultado = resultadoRepository.findById(id)
                .orElseThrow(() -> ResultadoAnalisisNotFoundException.withId(id));
        return resultadoMapper.toResponse(resultado);
    }

    /**
     * Actualizar resultado
     */
    @CacheEvict(value = "resultados", allEntries = true)
    public ResultadoAnalisisResponse actualizarResultado(Long id, CreateResultadoAnalisisRequest request) {
        log.debug("Actualizando resultado con ID: {}", id);

        ResultadoAnalisis resultadoExistente = resultadoRepository.findById(id)
                .orElseThrow(() -> ResultadoAnalisisNotFoundException.withId(id));

        // Validar usuario si se proporciona
        if (request.getUsuarioId() != null) {
            Usuario usuario = usuarioRepository.findById(request.getUsuarioId())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
            resultadoExistente.setUsuario(usuario);
        }

        // Validar laboratorio si se proporciona
        if (request.getLaboratorioId() != null) {
            Laboratorio laboratorio = laboratorioRepository.findById(request.getLaboratorioId())
                    .orElseThrow(() -> LaboratorioNotFoundException.withId(request.getLaboratorioId()));
            resultadoExistente.setLaboratorio(laboratorio);
        }

        // Actualizar campos
        if (request.getTipoAnalisis() != null) {
            resultadoExistente.setTipoAnalisis(request.getTipoAnalisis());
        }
        if (request.getFechaRealizacion() != null) {
            resultadoExistente.setFechaRealizacion(request.getFechaRealizacion());
        }
        if (request.getFechaResultado() != null) {
            resultadoExistente.setFechaResultado(request.getFechaResultado());
        }
        if (request.getValores() != null) {
            resultadoExistente.setValores(request.getValores());
        }
        if (request.getObservaciones() != null) {
            resultadoExistente.setObservaciones(request.getObservaciones());
        }
        if (request.getEstado() != null) {
            resultadoExistente.setEstado(request.getEstado());
        }

        ResultadoAnalisis resultadoActualizado = resultadoRepository.save(resultadoExistente);
        log.info("Resultado actualizado exitosamente con ID: {}", resultadoActualizado.getId());

        return resultadoMapper.toResponse(resultadoActualizado);
    }

    /**
     * Eliminar resultado
     */
    @CacheEvict(value = "resultados", allEntries = true)
    public void eliminarResultado(Long id) {
        log.debug("Eliminando resultado con ID: {}", id);

        if (!resultadoRepository.existsById(id)) {
            throw ResultadoAnalisisNotFoundException.withId(id);
        }

        resultadoRepository.deleteById(id);
        log.info("Resultado eliminado exitosamente con ID: {}", id);
    }

    /**
     * Buscar resultados por usuario
     */
    @Transactional(readOnly = true)
    public List<ResultadoAnalisisResponse> buscarPorUsuario(Long usuarioId) {
        log.debug("Buscando resultados por usuario ID: {}", usuarioId);
        return resultadoRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(resultadoMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Buscar resultados por laboratorio
     */
    @Transactional(readOnly = true)
    public List<ResultadoAnalisisResponse> buscarPorLaboratorio(Long laboratorioId) {
        log.debug("Buscando resultados por laboratorio ID: {}", laboratorioId);
        return resultadoRepository.findByLaboratorioId(laboratorioId)
                .stream()
                .map(resultadoMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Buscar resultados por estado
     */
    @Transactional(readOnly = true)
    public List<ResultadoAnalisisResponse> buscarPorEstado(String estado) {
        log.debug("Buscando resultados por estado: {}", estado);
        return resultadoRepository.findByEstado(estado)
                .stream()
                .map(resultadoMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Buscar resultados por tipo de análisis
     */
    @Transactional(readOnly = true)
    public List<ResultadoAnalisisResponse> buscarPorTipoAnalisis(String tipoAnalisis) {
        log.debug("Buscando resultados por tipo de análisis: {}", tipoAnalisis);
        return resultadoRepository.findByTipoAnalisisContainingIgnoreCase(tipoAnalisis)
                .stream()
                .map(resultadoMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Buscar resultados por rango de fechas
     */
    @Transactional(readOnly = true)
    public List<ResultadoAnalisisResponse> buscarPorRangoFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        log.debug("Buscando resultados por rango de fechas: {} - {}", fechaInicio, fechaFin);
        return resultadoRepository.findByFechaRealizacionBetween(fechaInicio, fechaFin)
                .stream()
                .map(resultadoMapper::toResponse)
                .collect(Collectors.toList());
    }
}

