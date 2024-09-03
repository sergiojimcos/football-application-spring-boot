package com.clubing.application.app.service.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

/**
 * @author Sergio Jiménez del Coso
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
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
}
