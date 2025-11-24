package com.proyecto.AccesoUsuarios.service;

import com.proyecto.AccesoUsuarios.model.Rol;
import com.proyecto.AccesoUsuarios.repository.RolRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RolService {

    // 1. Inyectar el Repositorio (para acceder a la base de datos)
    private final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    /**
     * Obtiene y devuelve la lista completa de todos los roles de la base de datos.
     * @return Lista de objetos Rol.
     */
    public List<Rol> findAll() {
        // 2. Usar el método findAll() proporcionado por JpaRepository
        return rolRepository.findAll();
    }
}