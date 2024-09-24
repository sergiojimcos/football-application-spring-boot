package com.clubing.application.app.auth.api.manager;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Sergio Jiménez del Coso
 */

public interface TokenManager {

    String extractUsername(String token);

    String generateToken(UserDetails userDetails);

    Boolean validateToken(String token, UserDetails userDetails);
}
