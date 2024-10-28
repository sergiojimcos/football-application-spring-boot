package com.clubing.application.app.rest.impl.converter;

import com.clubing.application.app.rest.api.dto.TokenDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Sergio Jiménez del Coso
 */

@Component
public class TokenDTOConverter implements Converter<String, TokenDTO> {


    @Override
    public TokenDTO convert(String token) {
        return new TokenDTO(token);
    }
}
