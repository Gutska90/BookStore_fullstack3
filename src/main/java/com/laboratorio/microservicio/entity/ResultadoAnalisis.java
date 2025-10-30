package com.laboratorio.microservicio.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entidad que representa un Resultado de Análisis Clínico
 */
@Entity
@Table(name = "resultados_analisis", indexes = {
    @Index(name = "idx_usuario", columnList = "USUARIO_ID"),
    @Index(name = "idx_laboratorio", columnList = "LABORATORIO_ID"),
    @Index(name = "idx_tipo_analisis", columnList = "TIPO_ANALISIS"),
    @Index(name = "idx_estado", columnList = "ESTADO")
})
public class ResultadoAnalisis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull(message = "El usuario es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario usuario;

    @NotNull(message = "El laboratorio es obligatorio")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LABORATORIO_ID", nullable = false)
    private Laboratorio laboratorio;

    @NotBlank(message = "El tipo de análisis es obligatorio")
    @Size(min = 3, max = 200, message = "El tipo de análisis debe tener entre 3 y 200 caracteres")
    @Column(name = "TIPO_ANALISIS", nullable = false, length = 200)
    private String tipoAnalisis;

    @NotNull(message = "La fecha de realización es obligatoria")
    @Column(name = "FECHA_REALIZACION", nullable = false)
    private LocalDate fechaRealizacion;

    @Column(name = "FECHA_RESULTADO")
    private LocalDate fechaResultado;

    @Column(name = "VALORES", columnDefinition = "CLOB")
    private String valores; // JSON o texto con los resultados

    @Size(max = 1000, message = "Las observaciones no pueden exceder 1000 caracteres")
    @Column(name = "OBSERVACIONES", length = 1000)
    private String observaciones;

    @NotNull(message = "El estado es obligatorio")
    @Column(name = "ESTADO", nullable = false, length = 20)
    private String estado; // PENDIENTE, COMPLETADO, CANCELADO

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
    public ResultadoAnalisis() {
        this.estado = "PENDIENTE";
    }

    // Constructor con parámetros
    public ResultadoAnalisis(Usuario usuario, Laboratorio laboratorio, String tipoAnalisis, 
                           LocalDate fechaRealizacion) {
        this.usuario = usuario;
        this.laboratorio = laboratorio;
        this.tipoAnalisis = tipoAnalisis;
        this.fechaRealizacion = fechaRealizacion;
        this.estado = "PENDIENTE";
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Laboratorio getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String getTipoAnalisis() {
        return tipoAnalisis;
    }

    public void setTipoAnalisis(String tipoAnalisis) {
        this.tipoAnalisis = tipoAnalisis;
    }

    public LocalDate getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(LocalDate fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public LocalDate getFechaResultado() {
        return fechaResultado;
    }

    public void setFechaResultado(LocalDate fechaResultado) {
        this.fechaResultado = fechaResultado;
    }

    public String getValores() {
        return valores;
    }

    public void setValores(String valores) {
        this.valores = valores;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

