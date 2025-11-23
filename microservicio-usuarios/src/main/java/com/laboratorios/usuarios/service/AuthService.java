package com.laboratorios.usuarios.service;

import com.laboratorios.usuarios.dto.LoginRequest;
import com.laboratorios.usuarios.dto.LoginResponse;
import com.laboratorios.usuarios.dto.UsuarioResponse;
import com.laboratorios.usuarios.entity.Usuario;
import com.laboratorios.usuarios.repository.UsuarioRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Value("${jwt.secret}")
    private String jwtSecret;
    
    @Value("${jwt.expiration}")
    private Long jwtExpiration;
    
    @Autowired
    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public LoginResponse login(LoginRequest request) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(request.getEmail());
        
        if (usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("Email o contraseña incorrectos");
        }
        
        Usuario usuario = usuarioOpt.get();
        
        if (!usuario.getActivo()) {
            throw new IllegalArgumentException("Usuario inactivo");
        }
        
        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new IllegalArgumentException("Email o contraseña incorrectos");
        }
        
        String token = generateToken(usuario);
        
        UsuarioResponse usuarioResponse = new UsuarioResponse(
            usuario.getId(),
            usuario.getEmail(),
            usuario.getNombre(),
            usuario.getApellido(),
            usuario.getRol(),
            usuario.getActivo(),
            usuario.getFechaCreacion(),
            usuario.getFechaActualizacion()
        );
        
        return new LoginResponse(token, usuarioResponse);
    }
    
    private String generateToken(Usuario usuario) {
        SecretKey key = Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
        
        return Jwts.builder()
                .subject(usuario.getEmail())
                .claim("rol", usuario.getRol().name())
                .claim("id", usuario.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(key)
                .compact();
    }
}

