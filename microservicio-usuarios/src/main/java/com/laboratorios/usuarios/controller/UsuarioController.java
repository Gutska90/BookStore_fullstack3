package com.laboratorios.usuarios.controller;

import com.laboratorios.usuarios.dto.UsuarioRequest;
import com.laboratorios.usuarios.dto.UsuarioResponse;
import com.laboratorios.usuarios.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
@Tag(name = "Usuarios", description = "API para gestión de usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    
    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @Operation(summary = "Obtener todos los usuarios", description = "Retorna una lista de todos los usuarios registrados")
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> obtenerTodosLosUsuarios() {
        List<UsuarioResponse> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(usuarios);
    }
    
    @Operation(summary = "Obtener usuario por ID", description = "Retorna un usuario específico por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> obtenerUsuarioPorId(@PathVariable Long id) {
        UsuarioResponse usuario = usuarioService.obtenerUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }
    
    @Operation(summary = "Crear nuevo usuario", description = "Crea un nuevo usuario en el sistema")
    @PostMapping
    public ResponseEntity<UsuarioResponse> crearUsuario(@Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse nuevoUsuario = usuarioService.crearUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);
    }
    
    @Operation(summary = "Actualizar usuario", description = "Actualiza la información de un usuario existente")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizarUsuario(
            @PathVariable Long id, 
            @Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse usuarioActualizado = usuarioService.actualizarUsuario(id, request);
        return ResponseEntity.ok(usuarioActualizado);
    }
    
    @Operation(summary = "Eliminar usuario", description = "Elimina un usuario del sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}

