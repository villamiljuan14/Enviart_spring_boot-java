package com.proyecto.AccesoUsuarios.repository;

import com.proyecto.AccesoUsuarios.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Integer> {
    // Necesario para buscar el rol por su nombre (ej: "ADMIN")
    Optional<Rol> findByTipoRol(String tipoRol);
}