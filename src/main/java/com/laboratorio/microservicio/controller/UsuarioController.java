package com.laboratorio.microservicio.controller;

import com.laboratorio.microservicio.dto.CreateUsuarioRequest;
import com.laboratorio.microservicio.dto.UsuarioResponse;
import com.laboratorio.microservicio.entity.Usuario;
import com.laboratorio.microservicio.service.UsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para gestión de usuarios
 */
@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * POST: Crear un nuevo usuario
     * Endpoint: POST /api/usuarios
     */
    @PostMapping
    public ResponseEntity<UsuarioResponse> crearUsuario(@Valid @RequestBody CreateUsuarioRequest request) {
        log.debug("Solicitud POST para crear usuario: {}", request.getEmail());
        UsuarioResponse nuevoUsuario = usuarioService.crearUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }

    /**
     * GET: Obtener todos los usuarios
     * Endpoint: GET /api/usuarios
     */
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> obtenerTodosLosUsuarios() {
        log.debug("Solicitud GET para obtener todos los usuarios");
        List<UsuarioResponse> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    /**
     * GET: Obtener un usuario por su ID
     * Endpoint: GET /api/usuarios/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerUsuarioPorId(@PathVariable Long id) {
        log.debug("Solicitud GET para obtener usuario con ID: {}", id);
        UsuarioResponse usuario = usuarioService.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }

    /**
     * PUT: Actualizar un usuario existente
     * Endpoint: PUT /api/usuarios/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizarUsuario(
            @PathVariable Long id,
            @Valid @RequestBody CreateUsuarioRequest request) {
        log.debug("Solicitud PUT para actualizar usuario con ID: {}", id);
        UsuarioResponse usuarioActualizado = usuarioService.actualizarUsuario(id, request);
        return ResponseEntity.ok(usuarioActualizado);
    }

    /**
     * DELETE: Eliminar un usuario por su ID
     * Endpoint: DELETE /api/usuarios/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        log.debug("Solicitud DELETE para eliminar usuario con ID: {}", id);
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET: Buscar usuarios por rol
     * Endpoint: GET /api/usuarios/rol/{rol}
     */
    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<UsuarioResponse>> buscarPorRol(@PathVariable Usuario.RolUsuario rol) {
        log.debug("Solicitud GET para buscar usuarios por rol: {}", rol);
        List<UsuarioResponse> usuarios = usuarioService.buscarPorRol(rol);
        return ResponseEntity.ok(usuarios);
    }
}

