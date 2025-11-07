package com.proyecto.AccesoUsuarios.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.proyecto.AccesoUsuarios.model.Usuario;
import com.proyecto.AccesoUsuarios.repository.UsuarioRepository;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(UsuarioRepository repo) {
        return args -> {
            if (repo.findByUserName("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUserName("admin");
                admin.setEmail("admin@correo.com");
                admin.setPassword(new BCryptPasswordEncoder().encode("123"));
                admin.setRol("ADMIN");
                repo.save(admin);
                System.out.println("✅ Usuario admin creado con éxito");
            } else {
                System.out.println("ℹ️ Usuario admin ya existe");
            }
        };
    }
}
