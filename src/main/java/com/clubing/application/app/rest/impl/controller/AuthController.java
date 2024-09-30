package com.clubing.application.app.rest.impl.controller;

import com.clubing.application.app.api.UserService;
import com.clubing.application.app.auth.api.manager.TokenManager;
import com.clubing.application.app.rest.api.dto.TokenDTO;
import com.clubing.application.app.rest.api.dto.UserDTO;
import com.clubing.application.app.rest.impl.converter.util.TokenDTOConverterUtil;
import com.clubing.application.app.rest.impl.converter.util.UserDTOConverterUtil;
import com.clubing.application.app.service.model.UserEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid UserDTO userDTO) throws Exception {

        UserDetails userDetails;

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassword())
        );

        try {
            userDetails = userDetailsService.loadUserByUsername(userDTO.getUserName());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        String token;
        try {
            token = tokenManager.generateToken(userDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        return ResponseEntity.ok(TokenDTOConverterUtil.toDTO(token));
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO userDTO) throws Exception {

        UserEntry userEntry = userService.fetchUserByName(userDTO.getUserName());

        if (userEntry == null) {
            userEntry = userService.addUser(userDTO.getUserName(), userDTO.getPassword());
            return ResponseEntity.ok(UserDTOConverterUtil.toDTO(userEntry));
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }
}
