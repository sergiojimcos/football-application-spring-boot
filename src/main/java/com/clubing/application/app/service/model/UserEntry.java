package com.clubing.application.app.service.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Sergio Jiménez del Coso
 */

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserEntry {

    @Id
    private String accessToken;

    @Column(unique = true)
    private String username;

    private String password;
}
