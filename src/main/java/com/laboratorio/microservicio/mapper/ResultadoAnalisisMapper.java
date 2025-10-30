package com.laboratorio.microservicio.mapper;

import com.laboratorio.microservicio.dto.ResultadoAnalisisResponse;
import com.laboratorio.microservicio.entity.ResultadoAnalisis;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre entidades ResultadoAnalisis y DTOs
 */
@Component
public class ResultadoAnalisisMapper {

    /**
     * Convierte entidad ResultadoAnalisis a ResultadoAnalisisResponse
     */
    public ResultadoAnalisisResponse toResponse(ResultadoAnalisis resultado) {
        ResultadoAnalisisResponse response = new ResultadoAnalisisResponse();
        response.setId(resultado.getId());
        response.setUsuarioId(resultado.getUsuario() != null ? resultado.getUsuario().getId() : null);
        response.setUsuarioNombre(resultado.getUsuario() != null ? resultado.getUsuario().getNombreCompleto() : null);
        response.setLaboratorioId(resultado.getLaboratorio() != null ? resultado.getLaboratorio().getId() : null);
        response.setLaboratorioNombre(resultado.getLaboratorio() != null ? resultado.getLaboratorio().getNombre() : null);
        response.setTipoAnalisis(resultado.getTipoAnalisis());
        response.setFechaRealizacion(resultado.getFechaRealizacion());
        response.setFechaResultado(resultado.getFechaResultado());
        response.setValores(resultado.getValores());
        response.setObservaciones(resultado.getObservaciones());
        response.setEstado(resultado.getEstado());
        response.setFechaCreacion(resultado.getFechaCreacion());
        response.setFechaActualizacion(resultado.getFechaActualizacion());
        return response;
    }
}

