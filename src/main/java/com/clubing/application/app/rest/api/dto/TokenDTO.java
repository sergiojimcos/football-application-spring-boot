package com.clubing.application.app.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Sergio Jiménez del Coso
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TokenDTO implements Serializable {

    private String accessToken;
}
