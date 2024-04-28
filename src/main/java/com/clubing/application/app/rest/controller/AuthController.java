package com.clubing.application.app.rest.controller;

import com.clubing.application.app.auth.api.manager.TokenManager;
import com.clubing.application.app.rest.api.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Sergio Jiménez del Coso
 */

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private TokenManager tokenManager;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(@RequestBody @Valid UserDTO userDTO) throws Exception {
        String token = tokenManager.loginUser(userDTO.getUserName(), userDTO.getPassword());

        return new ResponseEntity<>(token, HttpStatus.OK);

    }
}
