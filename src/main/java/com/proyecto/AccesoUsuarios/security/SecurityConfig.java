package com.proyecto.AccesoUsuarios.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 1. BEAN DE PASSWORD ENCODER
    // Este Bean se inyecta en DataInitializer y UsuarioController
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Rutas públicas: accesibles por cualquiera
                .requestMatchers("/", "/index", "/login", "/register", "/css/**", "/js/**", "/images/**").permitAll()
                
                // Rutas con acceso restringido por rol
                .requestMatchers("/usuarios", "/usuarios/**").hasRole("ADMIN")
                
                // Otras rutas (como /home, /perfil) requieren cualquier autenticación
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")        // Especifica la URL de la página de login personalizada
                .permitAll()                // Permite a todos acceder a la página de login
                .defaultSuccessUrl("/home", true) // Redirige a /home después de un login exitoso
                .failureUrl("/login?error") // Redirige con parámetro si falla
                // >>>>>> CORRECCIÓN CLAVE: Usar 'email' como parámetro de usuario <<<<<<
                .usernameParameter("email") 
            )
            .logout(logout -> logout
                .logoutUrl("/logout")       // URL para hacer logout (por defecto POST, pero aquí forzamos la ruta)
                .logoutSuccessUrl("/index") // Redirige después de un logout exitoso
                .permitAll()
            );

        return http.build();
    }
}