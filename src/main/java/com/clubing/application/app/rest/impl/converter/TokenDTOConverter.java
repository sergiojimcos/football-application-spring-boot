package com.clubing.application.app.rest.impl.converter;

import com.clubing.application.app.rest.api.dto.TokenDTO;

/**
 * @author Sergio Jiménez del Coso
 */

public class TokenDTOConverter {

    public static TokenDTO toDTO(String token) {
        return new TokenDTO(token);
    }
}
