package com.clubing.application.app.rest.impl.converter.util;

import com.clubing.application.app.rest.api.dto.UserDTO;
import com.clubing.application.app.service.model.UserEntry;

public class UserDTOConverterUtil {

    public static UserDTO toDTO(UserEntry userEntry) {
        return new UserDTO(userEntry.getUsername(), userEntry.getPassword());
    }
}
