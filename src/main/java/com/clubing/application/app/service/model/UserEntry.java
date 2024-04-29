package com.clubing.application.app.service.model;

import javax.persistence.*;

/**
 * @author Sergio Jiménez del Coso
 */

@Entity
public class UserEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;

    private String password;

    public UserEntry(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public UserEntry() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
