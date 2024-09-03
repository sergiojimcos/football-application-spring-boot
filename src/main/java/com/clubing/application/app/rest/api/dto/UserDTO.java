package com.clubing.application.app.rest.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Sergio Jiménez del Coso
 */

@Getter
@Setter
@AllArgsConstructor
public class UserDTO implements Serializable {

    @NotNull
    private String userName;

    @NotNull
    private String password;
}
