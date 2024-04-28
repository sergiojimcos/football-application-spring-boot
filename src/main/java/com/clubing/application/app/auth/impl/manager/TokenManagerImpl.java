package com.clubing.application.app.auth.impl.manager;

import com.clubing.application.app.auth.api.manager.TokenManager;
import com.clubing.application.app.rest.exception.UnauthorizedException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Sergio Jiménez del Coso
 */

@Service
public class TokenManagerImpl implements TokenManager {
    private Map<String, String> userTokens = new HashMap<>();

    @Override
    public String loginUser(String username, String password) {

        String token = UUID.randomUUID().toString();

        if (!_isValidPassword(password)) {
            throw new UnauthorizedException("Invalid credentials");
        }

        userTokens.put(token, username);
        return token;
    }

    @Override
    public boolean isUserLoggedIn(String token) {
        return userTokens.containsKey(token);
    }

    private boolean _isValidPassword(String password) {
        return !(password.length() <= 8 || !password.contains("_"));
    }
}
