package com.laboratorio.microservicio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Entidad que representa un Laboratorio Clínico
 */
@Entity
@Table(name = "laboratorios", indexes = {
    @Index(name = "idx_nombre", columnList = "NOMBRE"),
    @Index(name = "idx_estado", columnList = "ESTADO")
})
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "El nombre del laboratorio es obligatorio")
    @Size(min = 3, max = 200, message = "El nombre debe tener entre 3 y 200 caracteres")
    @Column(name = "NOMBRE", nullable = false, length = 200)
    private String nombre;

    @Size(max = 500, message = "La dirección no puede exceder 500 caracteres")
    @Column(name = "DIRECCION", length = 500)
    private String direccion;

    @Pattern(regexp = "^[+]?[0-9]{8,15}$", message = "El teléfono debe tener un formato válido")
    @Column(name = "TELEFONO", length = 20)
    private String telefono;

    @Email(message = "El email debe tener un formato válido")
    @Column(name = "EMAIL_CONTACTO", length = 100)
    private String emailContacto;

    @NotNull(message = "El estado es obligatorio")
    @Column(name = "ESTADO", nullable = false, length = 20)
    private String estado; // ACTIVO, INACTIVO

    @CreationTimestamp
    @Column(name = "FECHA_CREACION", updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "FECHA_ACTUALIZACION")
    private LocalDateTime fechaActualizacion;

    @Version
    @Column(name = "VERSION")
    private Long version;

    // Constructor por defecto
    public Laboratorio() {
        this.estado = "ACTIVO";
    }

    // Constructor con parámetros
    public Laboratorio(String nombre, String direccion, String telefono, String emailContacto) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.emailContacto = emailContacto;
        this.estado = "ACTIVO";
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}

