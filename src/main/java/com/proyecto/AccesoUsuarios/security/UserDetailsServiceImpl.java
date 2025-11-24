
package com.proyecto.AccesoUsuarios.security;   

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.proyecto.AccesoUsuarios.model.Usuario;
import com.proyecto.AccesoUsuarios.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 1. Buscar el usuario por email
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.warn("FALLO DE LOGIN: Usuario no encontrado con email: {}", email);
                    return new UsernameNotFoundException("Usuario no encontrado con email: " + email);
                });

        // 2. Comprobar que el usuario esté activo (buena práctica)
        if (!usuario.getEstadoUsuario()) {
            logger.warn("FALLO DE LOGIN: Usuario con email {} inactivo.", email);
            throw new UsernameNotFoundException("El usuario con email: " + email + " está inactivo.");
        }
        
        // 3. Obtener el rol y construir la autoridad
        // Spring Security requiere que el rol empiece con "ROLE_"
        String rolName = "ROLE_" + usuario.getRol().getTipoRol();
        
        GrantedAuthority authority = new SimpleGrantedAuthority(rolName);
        
        // 4. Crear y retornar el objeto UserDetails
        return new User(
            usuario.getEmail(),             // Username: El email del usuario
            usuario.getPassword(),          // << CORRECCIÓN CLAVE: Usar el método getPassword() de la entidad
            Collections.singletonList(authority) // Roles/Autoridades
        );
    }
}