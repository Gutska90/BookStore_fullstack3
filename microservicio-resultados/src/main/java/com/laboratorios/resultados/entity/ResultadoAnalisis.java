package com.laboratorios.resultados.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "RESULTADOS")
public class ResultadoAnalisis {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @NotNull(message = "El ID del paciente es obligatorio")
    @Column(name = "PACIENTE_ID", nullable = false)
    private Long pacienteId;
    
    @NotNull(message = "El ID del laboratorio es obligatorio")
    @Column(name = "LABORATORIO_ID", nullable = false)
    private Long laboratorioId;
    
    @NotBlank(message = "El tipo de análisis es obligatorio")
    @Size(min = 3, max = 100, message = "El tipo de análisis debe tener entre 3 y 100 caracteres")
    @Column(name = "TIPO_ANALISIS", nullable = false, length = 100)
    private String tipoAnalisis;
    
    @NotBlank(message = "El resultado es obligatorio")
    @Column(name = "RESULTADO", nullable = false, columnDefinition = "CLOB")
    private String resultado;
    
    @Column(name = "OBSERVACIONES", columnDefinition = "CLOB")
    private String observaciones;
    
    @NotNull(message = "La fecha del análisis es obligatoria")
    @Column(name = "FECHA_ANALISIS", nullable = false)
    private LocalDateTime fechaAnalisis;
    
    @Column(name = "FECHA_CREACION", updatable = false)
    private LocalDateTime fechaCreacion;
    
    @Column(name = "FECHA_ACTUALIZACION")
    private LocalDateTime fechaActualizacion;
    
    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
        if (fechaAnalisis == null) {
            fechaAnalisis = LocalDateTime.now();
        }
    }
    
    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }
    
    // Constructores
    public ResultadoAnalisis() {
    }
    
    public ResultadoAnalisis(Long pacienteId, Long laboratorioId, String tipoAnalisis, String resultado) {
        this.pacienteId = pacienteId;
        this.laboratorioId = laboratorioId;
        this.tipoAnalisis = tipoAnalisis;
        this.resultado = resultado;
        this.fechaAnalisis = LocalDateTime.now();
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getPacienteId() {
        return pacienteId;
    }
    
    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }
    
    public Long getLaboratorioId() {
        return laboratorioId;
    }
    
    public void setLaboratorioId(Long laboratorioId) {
        this.laboratorioId = laboratorioId;
    }
    
    public String getTipoAnalisis() {
        return tipoAnalisis;
    }
    
    public void setTipoAnalisis(String tipoAnalisis) {
        this.tipoAnalisis = tipoAnalisis;
    }
    
    public String getResultado() {
        return resultado;
    }
    
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public LocalDateTime getFechaAnalisis() {
        return fechaAnalisis;
    }
    
    public void setFechaAnalisis(LocalDateTime fechaAnalisis) {
        this.fechaAnalisis = fechaAnalisis;
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
}

