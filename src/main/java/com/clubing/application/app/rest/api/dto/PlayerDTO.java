package com.clubing.application.app.rest.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sergio Jiménez del Coso
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Setter
@Getter
@NoArgsConstructor
public class PlayerDTO implements Serializable {

    private long playerId;
    private String givenName;
    private String familyName;
    private String nationality;
    private String email;
    private Date dateOfBirth;

    public PlayerDTO(String givenName, String familyName, String nationality, String email, Date dateOfBirth) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.nationality = nationality;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }
}
