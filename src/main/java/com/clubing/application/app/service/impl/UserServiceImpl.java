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
    public UserEntry addUser(UserEntry userEntry) throws Exception {
        return userRepository.save(userEntry);
    }

    @Override
    public UserEntry fetchUser(String accessToken){
        return userRepository.findByAccessToken(accessToken);
    }
}
