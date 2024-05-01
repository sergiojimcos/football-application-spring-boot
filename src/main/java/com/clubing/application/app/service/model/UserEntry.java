package com.clubing.application.app.service.model;

import javax.persistence.*;

/**
 * @author Sergio Jiménez del Coso
 */

@Entity
public class UserEntry {

    @Id
    private String accessToken;

    @Column(unique = true)
    private String username;

    private String password;

    public UserEntry(String username, String password, String accessToken) {
        this.username = username;
        this.password = password;
        this.accessToken = accessToken;
    }


    public UserEntry() {

    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
