package com.proyecto.AccesoUsuarios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.proyecto.AccesoUsuarios.model.Usuario;
import com.proyecto.AccesoUsuarios.repository.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository repo;

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
        model.addAttribute("user", new Usuario());
        return "register";
    }

    @PostMapping("/register")
    public String registrarUsuario(@ModelAttribute("user") Usuario usuario, Model model) {

        // Validar si el usuario ya existe
        if (repo.findByUserName(usuario.getUserName()).isPresent()) {
            model.addAttribute("errorMessage", "El nombre de usuario ya está en uso.");
            model.addAttribute("user", usuario);
            return "register";
        }

        // Validar que las contraseñas coincidan
        if (!usuario.getPassword().equals(usuario.getPasswordConfirm())) {
            model.addAttribute("errorMessage", "Las contraseñas no coinciden.");
            model.addAttribute("user", usuario);
            return "register";
        }

        // Encriptar contraseña y guardar usuario
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        repo.save(usuario);

        // Redirigir al login con mensaje de éxito
        return "redirect:/login?registroExitoso";
    }

    // ======== RUTAS PRIVADAS (requieren login) ========

    @GetMapping("/home")
    public String home(Model model, Authentication auth) {
        model.addAttribute("rol", auth.getAuthorities().toString());
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
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("accion", "/usuarios/guardar"); // ✅ acción del form
        return "form";
    }

    // --- GUARDAR USUARIO (POST) ---
    @PostMapping("/usuarios/guardar")
    public String guardar(@ModelAttribute Usuario usuario) {
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        repo.save(usuario);
        return "redirect:/usuarios";
    }

    // --- EDITAR USUARIO (solo ADMIN) ---
    @GetMapping("/usuarios/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario usuario = repo.findById(id).orElseThrow();
        model.addAttribute("usuario", usuario);
        model.addAttribute("accion", "/usuarios/guardar"); // ✅ misma acción
        return "form";
    }

    // --- ELIMINAR USUARIO ---
    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/usuarios";
    }

    // --- PERFIL DEL USUARIO LOGUEADO ---
    @GetMapping("/perfil")
    public String perfil(Model model, Authentication auth) {
        String username = auth.getName();
        Usuario usuario = repo.findByUserName(username).orElseThrow();
        model.addAttribute("usuario", usuario);
        model.addAttribute("accion", "/perfil/guardar"); 
        return "form";
    }

    @PostMapping("/perfil/guardar")
    public String guardarPerfil(@ModelAttribute Usuario usuario, Authentication auth) {
        String username = auth.getName();
        Usuario actual = repo.findByUserName(username).orElseThrow();
        actual.setUserName(usuario.getUserName());
        actual.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        repo.save(actual);
        return "redirect:/home?actualizado";
    }
}
