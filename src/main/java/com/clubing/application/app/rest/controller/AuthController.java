package com.clubing.application.app.rest.controller;

import com.clubing.application.app.auth.api.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sergio Jiménez del Coso
 */

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private TokenManager tokenManager;

    @PostMapping()
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) throws Exception {

        if (_isValidPassword(password)) {
            String token = tokenManager.loginUser(username, password);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid credential", HttpStatus.UNAUTHORIZED);
        }
    }

    private boolean _isValidPassword(String password) {
        return !(password.length() <= 8 || !password.contains("_"));
    }
}
