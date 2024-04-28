package com.clubing.application.app.rest.api.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Sergio Jiménez del Coso
 */

public class UserDTO implements Serializable {
    public UserDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    private String userName;

    @NotNull
    private String password;
}
