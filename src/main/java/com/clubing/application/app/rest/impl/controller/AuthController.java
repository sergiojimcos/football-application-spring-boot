package com.clubing.application.app.rest.impl.controller;

import com.clubing.application.app.api.UserService;
import com.clubing.application.app.auth.api.manager.TokenManager;
import com.clubing.application.app.rest.api.dto.TokenDTO;
import com.clubing.application.app.rest.api.dto.UserDTO;
import com.clubing.application.app.rest.impl.converter.util.TokenDTOConverterUtil;
import com.clubing.application.app.service.model.UserEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Sergio Jiménez del Coso
 */

@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private TokenManager tokenManager;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid UserDTO userDTO) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassword())
        );

        UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUserName());

        return ResponseEntity.ok(TokenDTOConverterUtil.toDTO(tokenManager.generateToken(userDetails)));
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) {

        UserEntry userEntry = userService.fetchUserByName(userDTO.getUserName());

        if (userEntry == null) {
            return ResponseEntity.ok(userDTO);
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
}
