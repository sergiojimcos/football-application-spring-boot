package com.clubing.application.app.service.model;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

/**
 * @author Sergio Jiménez del Coso
 */

@Entity
public class PlayerEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    private String nationality;

    @Email(message = "Invalid email format")
    private String email;

    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "clubEntry_id")
    private ClubEntry clubEntry;

    public PlayerEntry(long id, String name, String surname, String nationality, String email, Date dateOfBirth,
                       ClubEntry clubEntry) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.clubEntry = clubEntry;
    }

    public PlayerEntry(String name, String surname, String nationality, String email, Date dateOfBirth, ClubEntry clubEntry) {
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.clubEntry = clubEntry;
    }

    public PlayerEntry() {

    }

    public void setClubId(ClubEntry clubEntry) {
        this.clubEntry = clubEntry;
    }

    public ClubEntry getClubEntry() {
        return clubEntry;
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
