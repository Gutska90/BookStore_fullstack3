package com.laboratorio.microservicio.mapper;

import com.laboratorio.microservicio.dto.UsuarioResponse;
import com.laboratorio.microservicio.entity.Usuario;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre entidades Usuario y DTOs
 */
@Component
public class UsuarioMapper {

    /**
     * Convierte entidad Usuario a UsuarioResponse (sin información sensible)
     */
    public UsuarioResponse toResponse(Usuario usuario) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setNombreCompleto(usuario.getNombreCompleto());
        response.setEmail(usuario.getEmail());
        response.setRol(usuario.getRol());
        response.setEstado(usuario.getEstado());
        response.setFechaCreacion(usuario.getFechaCreacion());
        response.setFechaActualizacion(usuario.getFechaActualizacion());
        return response;
    }
}

