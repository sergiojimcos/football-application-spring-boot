package com.clubing.application.app.rest.api.dto;

import java.io.Serializable;

/**
 * @author Sergio Jiménez del Coso
 */
public class TokenDTO implements Serializable {

    private String accessToken;

    public TokenDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public TokenDTO() {

    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
