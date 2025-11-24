package com.proyecto.AccesoUsuarios.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Table(name = "usuarios")
public class Usuario {

    // -------------------------------------------------------------------------
    // 1. IDENTIFICADOR (Primary Key)
    // -------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    // -------------------------------------------------------------------------
    // 2. DATOS CORE DEL NEGOCIO
    // -------------------------------------------------------------------------
    @Column(name = "doc_usuario", unique = true, nullable = false, length = 45)
    private String docUsuario;

    @Column(name = "tipo_documento", nullable = false, length = 20)
    private String tipoDocumento; 

    @Column(name = "primer_nombre", nullable = false, length = 80)
    private String primerNombre;

    @Column(name = "segundo_nombre", length = 80)
    private String segundoNombre;

    @Column(name = "primer_apellido", nullable = false, length = 80)
    private String primerApellido;

    @Column(name = "segundo_apellido", length = 80)
    private String segundoApellido;

    @Column(name = "telefono", length = 45)
    private String telefono;

    @Column(name = "email", nullable = false, unique = true, length = 120)
    private String email;

    // -------------------------------------------------------------------------
    // 3. SEGURIDAD
    // -------------------------------------------------------------------------
    @Column(name = "contrasena_usuario", nullable = false)
    private String password;
   @Column(name = "estado_usuario",nullable = false,
    columnDefinition = "TINYINT(1)")
    private Boolean estadoUsuario;


    @Column(name = "two_factor_secret")
    private String twoFactorSecret;

    // -------------------------------------------------------------------------
    // 4. RELACIONES
    // -------------------------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id_rol", nullable = false)
    private Rol rol;

    // -------------------------------------------------------------------------
    // 5. AUDITORÍA
    // -------------------------------------------------------------------------
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // -------------------------------------------------------------------------
    // 6. CICLO DE VIDA JPA
    // -------------------------------------------------------------------------
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
