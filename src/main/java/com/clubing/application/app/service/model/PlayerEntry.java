package com.clubing.application.app.service.model;


import java.util.Date;

/**
 * @author Sergio Jiménez del Coso
 */
public class PlayerEntry {
    private long id;
    private String name;
    private String surname;
    private String nationality;
    private String email;
    private Date dateOfBirth;
    private long clubId;

    public PlayerEntry(long id, String name, String surname, String nationality, String email, Date dateOfBirth, long clubId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.clubId = clubId;
    }

    public void setClubId(long clubId) {
        this.clubId = clubId;
    }

    public long getClubId() {
        return clubId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNationality() {
        return nationality;
    }

    public String getEmail() {
        return email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
}
