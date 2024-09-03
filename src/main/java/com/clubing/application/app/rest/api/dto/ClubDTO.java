package com.clubing.application.app.rest.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Sergio Jiménez del Coso
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@NoArgsConstructor
@Getter
@Setter
public class ClubDTO implements Serializable{

    private long clubId;
    private String userName;
    private String password;
    private String officialName;
    private String popularName;
    private String federation;
    private boolean isPublic;
    private Integer totalPlayers;

    public ClubDTO(String userName, String password, String officialName, String popularName, String federation, boolean isPublic) {
        this.userName = userName;
        this.password = password;
        this.officialName = officialName;
        this.popularName = popularName;
        this.federation = federation;
        this.isPublic = isPublic;
    }
}
