package com.clubing.application.app.auth.impl.manager;

import com.clubing.application.app.auth.api.manager.TokenManager;
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
        userTokens.put(token, username);
        return token;
    }

    @Override
    public boolean isUserLoggedIn(String token) {
        return userTokens.containsKey(token);
    }
}
