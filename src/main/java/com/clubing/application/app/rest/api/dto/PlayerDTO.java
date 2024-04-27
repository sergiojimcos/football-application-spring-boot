package com.clubing.application.app.rest.api.dto;

import java.io.Serializable;
import java.util.Date;

public class PlayerDTO implements Serializable {
    private long playerId;
    private String givenName;
    private String familyName;
    private String nationality;
    private String email;
    private Date dateOfBirth;

    public PlayerDTO() {
    }

    public PlayerDTO(long playerId, String givenName, String familyName, String nationality, String email, Date dateOfBirth) {
        this.playerId = playerId;
        this.givenName = givenName;
        this.familyName = familyName;
        this.nationality = nationality;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    // Método toString para imprimir el objeto PlayerDTO
    @Override
    public String toString() {
        return "PlayerDTO{" +
                "playerId=" + playerId +
                ", givenName='" + givenName + '\'' +
                ", familyName='" + familyName + '\'' +
                ", nationality='" + nationality + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
