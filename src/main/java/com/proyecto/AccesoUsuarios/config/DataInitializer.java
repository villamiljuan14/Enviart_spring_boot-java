package com.proyecto.AccesoUsuarios.config;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.proyecto.AccesoUsuarios.model.Rol;
import com.proyecto.AccesoUsuarios.model.Usuario;
import com.proyecto.AccesoUsuarios.repository.RolRepository;
import com.proyecto.AccesoUsuarios.repository.UsuarioRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final RolRepository rolRepo;
    private final UsuarioRepository userRepo;

    public DataInitializer(PasswordEncoder passwordEncoder, RolRepository rolRepo, UsuarioRepository userRepo) {
        this.passwordEncoder = passwordEncoder;
        this.rolRepo = rolRepo;
        this.userRepo = userRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // Crear roles si no existen
        initializeRoles("ADMIN");
        initializeRoles("USER");

        // Crear usuario administrador por defecto
        initializeAdminUser();
    }

    private void initializeRoles(String roleName) {
        Optional<Rol> rolOpt = rolRepo.findByTipoRol(roleName);
        if (rolOpt.isEmpty()) {
            Rol rol = new Rol();
            rol.setTipoRol(roleName);
            rolRepo.save(rol);
            System.out.println("✅ Rol " + roleName + " creado con éxito.");
        }
    }

    private void initializeAdminUser() {

        String adminEmail = "admin@correo.com";
        Optional<Usuario> userOpt = userRepo.findByEmail(adminEmail);

        if (userOpt.isEmpty()) {

            Optional<Rol> adminRolOpt = rolRepo.findByTipoRol("ADMIN");

            if (adminRolOpt.isPresent()) {

                Usuario admin = new Usuario();
                admin.setEmail(adminEmail);

                // ✔ CONTRASEÑA CORRECTA
                admin.setPassword(passwordEncoder.encode("123"));

                admin.setDocUsuario("1000521258");
                admin.setTipoDocumento("CC");
                admin.setPrimerNombre("Juan");
                admin.setSegundoNombre("Manuel");
                admin.setPrimerApellido("Villamil");
                admin.setSegundoApellido("Vargas");
                admin.setTelefono("3104942867");
                admin.setEstadoUsuario(true);
                admin.setCreatedAt(LocalDateTime.now());
                admin.setUpdatedAt(LocalDateTime.now());

                admin.setRol(adminRolOpt.get());

                userRepo.save(admin);

                System.out.println("✅ Usuario admin creado con éxito.");
                System.out.println("   Email: " + adminEmail);
                System.out.println("   Password: 123");

            } else {
                System.err.println("❌ ERROR: Rol ADMIN no existe.");
            }
        }
    }
}
