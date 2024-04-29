package com.clubing.application.app.api;

import com.clubing.application.app.service.model.UserEntry;

/**
 * @author Sergio Jiménez del Coso
 */

public interface UserService {

    public UserEntry addUser(UserEntry userEntry) throws Exception;
}
