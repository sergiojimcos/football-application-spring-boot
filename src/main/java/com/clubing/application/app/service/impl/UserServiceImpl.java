package com.clubing.application.app.service.impl;

import com.clubing.application.app.api.UserService;
import com.clubing.application.app.service.model.UserEntry;
import com.clubing.application.app.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sergio Jiménez del Coso
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntry addUser(String userName, String password) throws Exception {


        return userRepository.save(new UserEntry() {{
            setUsername(userName);
            setPassword(password);
        }});
    }

    @Override
    public UserEntry fetchUserByName(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
