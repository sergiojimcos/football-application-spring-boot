package com.clubing.application.app.auth.impl.manager;

import com.clubing.application.app.api.UserService;
import com.clubing.application.app.auth.api.manager.TokenManager;
import com.clubing.application.app.rest.impl.exception.UnauthorizedException;
import com.clubing.application.app.service.model.UserEntry;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;

    @Override
    public String loginUser(String username, String password) throws Exception{

        String token = UUID.randomUUID().toString();

        if (!_isValidPassword(password)) {
            throw new UnauthorizedException("Invalid credentials");
        }

        UserEntry userEntry = userService.addUser(new UserEntry(username, password));

        userTokens.put(token, userEntry.getUsername());
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
