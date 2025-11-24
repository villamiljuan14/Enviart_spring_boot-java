package com.proyecto.AccesoUsuarios.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder; // Usar la interfaz
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.AccesoUsuarios.model.Rol;
import com.proyecto.AccesoUsuarios.model.Usuario;
import com.proyecto.AccesoUsuarios.repository.RolRepository;
import com.proyecto.AccesoUsuarios.repository.UsuarioRepository;

@Controller
public class UsuarioController {

    // INYECCIÓN CORREGIDA: Usamos el bean global de PasswordEncoder
    @Autowired
    private PasswordEncoder passwordEncoder; 
    
    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private RolRepository rolRepo;    

    // ======== RUTAS PÚBLICAS ========

    @GetMapping("/")
    public String redireccionRaiz() {
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // --- Registro de nuevo usuario ---
    @GetMapping("/register")
    public String register(Model model) {
        // Aseguramos que el objeto Usuario tenga todos los campos requeridos inicializados para el form
        Usuario usuarioForm = new Usuario();
        usuarioForm.setEstadoUsuario(true); // Default
        // Inicializar con datos básicos para evitar errores en el form.
        usuarioForm.setTipoDocumento("CC");
        usuarioForm.setDocUsuario("");
        usuarioForm.setPrimerNombre("");
        usuarioForm.setPrimerApellido("");
        
        model.addAttribute("user", usuarioForm);
        return "register";
    }

    @PostMapping("/register")
    public String registrarUsuario(@ModelAttribute("user") Usuario usuario, Model model, RedirectAttributes ra) {
        final String defaultRolName = "USER"; // Rol por defecto (hemos cambiado a USER, no CLIENTE)

        if (repo.findByEmail(usuario.getEmail()).isPresent()) {
            model.addAttribute("errorMessage", "El email ya está asociado a una cuenta.");
            return "register";
        }
        
        // 1. Asignar rol por defecto (USER)
        Optional<Rol> rolClienteOpt = rolRepo.findByTipoRol(defaultRolName);

        if (rolClienteOpt.isEmpty()) {
            throw new RuntimeException("El rol por defecto '" + defaultRolName + "' no está inicializado en la DB. ¡Añade 'USER' a DataInitializer!");
        }
        
        usuario.setRol(rolClienteOpt.get());
        
        // 2. Asignar campos obligatorios que el formulario simple no incluye
        // Se asume que el usuario debe completar los campos personales en el formulario
        // Si el formulario es simple (solo email/pass), estos campos deben ser asignados aquí:
        if (usuario.getDocUsuario() == null || usuario.getDocUsuario().isEmpty()) {
             usuario.setDocUsuario(usuario.getEmail()); // Usar email como doc temporal si no hay otro
        }
        if (usuario.getPrimerNombre() == null || usuario.getPrimerNombre().isEmpty()) {
            usuario.setPrimerNombre("Nuevo");
        }
        if (usuario.getPrimerApellido() == null || usuario.getPrimerApellido().isEmpty()) {
            usuario.setPrimerApellido("Usuario");
        }
        
        usuario.setEstadoUsuario(true); // Siempre activo
        usuario.setCreatedAt(LocalDateTime.now());
        usuario.setUpdatedAt(LocalDateTime.now());
        
        // 3. Encriptar contraseña (USANDO EL BEAN INYECTADO) y guardar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        repo.save(usuario);

        ra.addFlashAttribute("registroExitoso", true);
        return "redirect:/login";
    }

    // ======== RUTAS PRIVADAS (requieren login) ========

    // Lógica para /home que soporta home.html
    @GetMapping("/home")
    public String home(Model model, Authentication authentication) {
        if (authentication != null) {
            String email = authentication.getName();
            String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(a -> a.getAuthority().replace("ROLE_", ""))
                .orElse("N/A");
                
            model.addAttribute("email", email);
            model.addAttribute("role", role);
        }
        return "home";
    }

    // --- LISTADO DE USUARIOS (solo ADMIN) ---
    @GetMapping("/usuarios")
    public String listar(Model model) {
        model.addAttribute("usuarios", repo.findAll());
        return "usuarios";
    }

    // --- NUEVO USUARIO (solo ADMIN) ---
    @GetMapping("/usuarios/nuevo")
    public String nuevo(Model model) {
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEstadoUsuario(true); // Por defecto activo
        model.addAttribute("usuario", nuevoUsuario);
        model.addAttribute("roles", rolRepo.findAll()); // Necesitas los roles para el formulario
        model.addAttribute("accion", "/usuarios/guardar");
        return "form";
    }

    // --- GUARDAR USUARIO (POST) ---
    @PostMapping("/usuarios/guardar")
    public String guardar(@ModelAttribute Usuario usuario) {
        
        // Cifrar la contraseña si es un usuario nuevo o si se actualiza la clave
        if (usuario.getIdUsuario() == null || (usuario.getPassword() != null && !usuario.getPassword().isEmpty() && !usuario.getPassword().startsWith("$2a$"))) {
             usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        } else if (usuario.getIdUsuario() != null) {
             // Si es edición y la contraseña está vacía, se mantiene la actual
             Optional<Usuario> existing = repo.findById(usuario.getIdUsuario());
             if (existing.isPresent()) {
                 usuario.setPassword(existing.get().getPassword());
                 // Aseguramos que los campos obligatorios que no están en el form sean persistidos
                 usuario.setCreatedAt(existing.get().getCreatedAt());
             }
        }
        
        usuario.setUpdatedAt(LocalDateTime.now());
        repo.save(usuario);
        return "redirect:/usuarios";
    }

    // --- EDITAR USUARIO (solo ADMIN) ---
    @GetMapping("/usuarios/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {    
        Usuario usuario = repo.findById(id).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        model.addAttribute("usuario", usuario);
        model.addAttribute("roles", rolRepo.findAll()); // Necesitas roles
        model.addAttribute("accion", "/usuarios/guardar");
        return "form";
    }

    // --- ELIMINAR USUARIO ---
    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        repo.deleteById(id);
        return "redirect:/usuarios";
    }

    // --- PERFIL DEL USUARIO LOGUEADO ---
    @GetMapping("/perfil")
    public String perfil(Model model, Authentication auth) {
        String userIdentifier = auth.getName();
        
        Usuario usuario = repo.findByEmail(userIdentifier)
                                .orElseThrow(() -> new UsernameNotFoundException("Perfil no encontrado para: " + userIdentifier));
                                
        model.addAttribute("usuario", usuario);
        // NOTA: El form de perfil debe ser diferente a 'form' para evitar pedir datos de seguridad al usuario
        // Aquí se asume que se usa el mismo 'form.html' que para la gestión de administradores.
        model.addAttribute("accion", "/perfil/guardar");
        return "form"; 
    }

    @PostMapping("/perfil/guardar")
    public String guardarPerfil(@ModelAttribute Usuario usuarioConCambios, Authentication auth) {
        String userIdentifier = auth.getName();
        
        Usuario actual = repo.findByEmail(userIdentifier)
                             .orElseThrow(() -> new UsernameNotFoundException("Perfil no encontrado para: " + userIdentifier));
        
        // 1. Actualizar campos de datos (se debe evitar que el usuario cambie el rol)
        actual.setEmail(usuarioConCambios.getEmail());
        actual.setTelefono(usuarioConCambios.getTelefono());
        actual.setPrimerNombre(usuarioConCambios.getPrimerNombre());
        actual.setSegundoNombre(usuarioConCambios.getSegundoNombre());
        actual.setPrimerApellido(usuarioConCambios.getPrimerApellido());
        actual.setSegundoApellido(usuarioConCambios.getSegundoApellido());
        
        // 2. Solo actualizar contraseña si se proporciona una nueva (no vacía)
        if (usuarioConCambios.getPassword() != null && !usuarioConCambios.getPassword().isEmpty()) {
            // USANDO EL BEAN INYECTADO
            actual.setPassword(passwordEncoder.encode(usuarioConCambios.getPassword()));
        }
        
        actual.setUpdatedAt(LocalDateTime.now());
        repo.save(actual);
        return "redirect:/perfil?actualizado"; // Redireccionar a perfil con mensaje
    }
}