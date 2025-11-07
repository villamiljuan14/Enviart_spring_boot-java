package com.proyecto.AccesoUsuarios.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.AccesoUsuarios.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUserName(String userName);
}

