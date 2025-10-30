package com.laboratorio.microservicio.service;

import com.laboratorio.microservicio.dto.*;
import com.laboratorio.microservicio.entity.Usuario;
import com.laboratorio.microservicio.exception.UsuarioNotFoundException;
import com.laboratorio.microservicio.repository.UsuarioRepository;
import com.laboratorio.microservicio.mapper.UsuarioMapper;
import com.laboratorio.microservicio.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio para gestión de usuarios y autenticación
 */
@Service
@Transactional
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder,
                         JwtUtil jwtUtil, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.usuarioMapper = usuarioMapper;
    }

    /**
     * Crear nuevo usuario
     */
    @CacheEvict(value = "usuarios", allEntries = true)
    public UsuarioResponse crearUsuario(CreateUsuarioRequest request) {
        log.debug("Creando nuevo usuario: {}", request.getEmail());

        // Verificar si ya existe un usuario con el mismo email
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con el email: " + request.getEmail());
        }

        // Encriptar contraseña
        String contrasenaEncriptada = passwordEncoder.encode(request.getContrasena());

        // Crear usuario
        Usuario usuario = new Usuario(
                request.getNombreCompleto(),
                request.getEmail(),
                contrasenaEncriptada,
                request.getRol()
        );

        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        log.info("Usuario creado exitosamente con ID: {}", usuarioGuardado.getId());

        return usuarioMapper.toResponse(usuarioGuardado);
    }

    /**
     * Obtener todos los usuarios
     */
    @Transactional(readOnly = true)
    public List<UsuarioResponse> obtenerTodosLosUsuarios() {
        log.debug("Obteniendo todos los usuarios");
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Obtener usuario por ID
     */
    @Transactional(readOnly = true)
    public UsuarioResponse obtenerUsuarioPorId(Long id) {
        log.debug("Obteniendo usuario con ID: {}", id);
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> UsuarioNotFoundException.withId(id));
        return usuarioMapper.toResponse(usuario);
    }

    /**
     * Actualizar usuario
     */
    @CacheEvict(value = "usuarios", allEntries = true)
    public UsuarioResponse actualizarUsuario(Long id, CreateUsuarioRequest request) {
        log.debug("Actualizando usuario con ID: {}", id);

        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> UsuarioNotFoundException.withId(id));

        // Verificar si el nuevo email ya existe en otro usuario
        if (!usuarioExistente.getEmail().equals(request.getEmail())) {
            if (usuarioRepository.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("Ya existe otro usuario con el email: " + request.getEmail());
            }
        }

        // Actualizar datos
        usuarioExistente.setNombreCompleto(request.getNombreCompleto());
        usuarioExistente.setEmail(request.getEmail());
        usuarioExistente.setRol(request.getRol());

        // Si se proporciona nueva contraseña, encriptarla
        if (request.getContrasena() != null && !request.getContrasena().isEmpty()) {
            usuarioExistente.setContrasena(passwordEncoder.encode(request.getContrasena()));
        }

        Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);
        log.info("Usuario actualizado exitosamente con ID: {}", usuarioActualizado.getId());

        return usuarioMapper.toResponse(usuarioActualizado);
    }

    /**
     * Eliminar usuario
     */
    @CacheEvict(value = "usuarios", allEntries = true)
    public void eliminarUsuario(Long id) {
        log.debug("Eliminando usuario con ID: {}", id);

        if (!usuarioRepository.existsById(id)) {
            throw UsuarioNotFoundException.withId(id);
        }

        usuarioRepository.deleteById(id);
        log.info("Usuario eliminado exitosamente con ID: {}", id);
    }

    /**
     * Autenticar usuario (login)
     */
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {
        log.debug("Intentando login para email: {}", request.getEmail());

        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Email o contraseña incorrectos"));

        // Verificar estado del usuario
        if (!"ACTIVO".equals(usuario.getEstado())) {
            throw new IllegalStateException("El usuario está inactivo");
        }

        // Verificar contraseña
        if (!passwordEncoder.matches(request.getContrasena(), usuario.getContrasena())) {
            throw new IllegalArgumentException("Email o contraseña incorrectos");
        }

        // Generar token JWT
        String token = jwtUtil.generateToken(usuario.getEmail(), usuario.getRol().name(), usuario.getId());

        UsuarioResponse usuarioResponse = usuarioMapper.toResponse(usuario);

        log.info("Login exitoso para usuario: {}", usuario.getEmail());

        return new LoginResponse(token, usuarioResponse, "Login exitoso");
    }

    /**
     * Buscar usuarios por rol
     */
    @Transactional(readOnly = true)
    public List<UsuarioResponse> buscarPorRol(Usuario.RolUsuario rol) {
        log.debug("Buscando usuarios por rol: {}", rol);
        return usuarioRepository.findByRol(rol)
                .stream()
                .map(usuarioMapper::toResponse)
                .collect(Collectors.toList());
    }
}

