package com.clubing.application.app.auth.impl.manager;

import com.clubing.application.app.api.UserService;
import com.clubing.application.app.auth.api.manager.TokenManager;
import com.clubing.application.app.rest.impl.exception.UnauthorizedException;
import com.clubing.application.app.service.model.UserEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Sergio Jiménez del Coso
 */

@Service
public class TokenManagerImpl implements TokenManager {

    @Autowired
    private UserService userService;

    @Override
    public String loginUser(String username, String password) throws Exception{

        String token = UUID.randomUUID().toString();

        if (!_isValidPassword(password)) {
            throw new UnauthorizedException("Invalid credentials");
        }

        UserEntry userEntry = userService.addUser(new UserEntry(token, username, password));

        return userEntry.getAccessToken();
    }

    @Override
    public boolean isUserLoggedIn(String token) {

        return userService.fetchUser(token) != null;
    }

    private boolean _isValidPassword(String password) {
        return !(password.length() <= 8 || !password.contains("_"));
    }
}
