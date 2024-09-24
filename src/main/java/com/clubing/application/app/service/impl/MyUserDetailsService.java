package com.clubing.application.app.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Aquí deberías obtener el usuario desde la base de datos.
        // Por ahora, devuelvo un usuario "dummy" por simplicidad.

        if ("user".equals(username)) {
            return new org.springframework.security.core.userdetails.User(
                    "user",
                    "{noop}password", // Contraseña codificada con {noop} (sin codificación)
                    new ArrayList<>() // Aquí se pueden agregar roles/permisos
            );
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado con el nombre de usuario: " + username);
        }
    }
}
