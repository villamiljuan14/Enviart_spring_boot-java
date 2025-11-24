package com.proyecto.AccesoUsuarios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.proyecto.AccesoUsuarios.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    // Método esencial para Spring Security: carga el rol junto con el usuario
    @Query("SELECT u FROM Usuario u JOIN FETCH u.rol WHERE u.email = :email")
    Optional<Usuario> findByEmail(String email);

    // Opcional: Para asegurar que no haya duplicados
    boolean existsByEmail(String email);
}
