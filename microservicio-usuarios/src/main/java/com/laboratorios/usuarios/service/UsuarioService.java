package com.laboratorios.usuarios.service;

import com.laboratorios.usuarios.dto.UsuarioRequest;
import com.laboratorios.usuarios.dto.UsuarioResponse;
import com.laboratorios.usuarios.entity.Usuario;
import com.laboratorios.usuarios.exception.UsuarioNotFoundException;
import com.laboratorios.usuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordValidatorService passwordValidator;
    
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, 
                         PasswordEncoder passwordEncoder,
                         PasswordValidatorService passwordValidator) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordValidator = passwordValidator;
    }
    
    public List<UsuarioResponse> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
    
    public UsuarioResponse obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> UsuarioNotFoundException.withId(id));
        return toResponse(usuario);
    }
    
    public UsuarioResponse crearUsuario(UsuarioRequest request) {
        // Validar que el email no exista
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con el email: " + request.getEmail());
        }
        
        // Validar contraseña
        passwordValidator.validatePassword(request.getPassword());
        
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setRol(request.getRol());
        usuario.setActivo(true);
        
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return toResponse(savedUsuario);
    }
    
    public UsuarioResponse actualizarUsuario(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> UsuarioNotFoundException.withId(id));
        
        // Validar email único si cambió
        if (!usuario.getEmail().equals(request.getEmail()) && 
            usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario con el email: " + request.getEmail());
        }
        
        // Validar contraseña si se proporciona
        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            passwordValidator.validatePassword(request.getPassword());
            usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        }
        
        usuario.setEmail(request.getEmail());
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setRol(request.getRol());
        
        Usuario savedUsuario = usuarioRepository.save(usuario);
        return toResponse(savedUsuario);
    }
    
    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw UsuarioNotFoundException.withId(id);
        }
        usuarioRepository.deleteById(id);
    }
    
    private UsuarioResponse toResponse(Usuario usuario) {
        return new UsuarioResponse(
            usuario.getId(),
            usuario.getEmail(),
            usuario.getNombre(),
            usuario.getApellido(),
            usuario.getRol(),
            usuario.getActivo(),
            usuario.getFechaCreacion(),
            usuario.getFechaActualizacion()
        );
    }
}

