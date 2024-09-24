package com.clubing.application.app.service.impl;

import com.clubing.application.app.api.UserService;
import com.clubing.application.app.service.model.UserEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntry userEntry = userService.fetchUserByName(username);

        if (userEntry != null) {
            return new User(userEntry.getUsername(), userEntry.getPassword(), Collections.emptyList());
        } else {
            throw new UsernameNotFoundException("User with username: " + username + " is not created");
        }
    }
}
