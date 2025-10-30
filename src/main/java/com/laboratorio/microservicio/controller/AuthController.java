package com.laboratorio.microservicio.controller;

import com.laboratorio.microservicio.dto.LoginRequest;
import com.laboratorio.microservicio.dto.LoginResponse;
import com.laboratorio.microservicio.service.UsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para autenticación
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UsuarioService usuarioService;

    @Autowired
    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * POST: Iniciar sesión
     * Endpoint: POST /api/auth/login
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        log.debug("Solicitud de login para email: {}", request.getEmail());
        LoginResponse response = usuarioService.login(request);
        return ResponseEntity.ok(response);
    }

    /**
     * POST: Cerrar sesión (simplemente informativo, JWT es stateless)
     * Endpoint: POST /api/auth/logout
     */
    @PostMapping("/logout")
    public ResponseEntity<Object> logout() {
        log.debug("Solicitud de logout");
        // JWT es stateless, no hay sesión en el servidor que cerrar
        // En un sistema real, podrías invalidar tokens en una blacklist
        return ResponseEntity.ok().build();
    }
}

