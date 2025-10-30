package com.laboratorio.microservicio.repository;

import com.laboratorio.microservicio.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones CRUD sobre la entidad Usuario
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Buscar usuario por email (para login)
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Verificar si existe un usuario con el email dado
     */
    boolean existsByEmail(String email);

    /**
     * Buscar usuarios por rol
     */
    List<Usuario> findByRol(Usuario.RolUsuario rol);

    /**
     * Buscar usuarios activos
     */
    List<Usuario> findByEstado(String estado);

    /**
     * Buscar usuarios por rol y estado
     */
    @Query("SELECT u FROM Usuario u WHERE u.rol = :rol AND u.estado = :estado")
    List<Usuario> findByRolAndEstado(@Param("rol") Usuario.RolUsuario rol, 
                                     @Param("estado") String estado);
}

