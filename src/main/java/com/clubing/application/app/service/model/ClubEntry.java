package com.clubing.application.app.service.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Sergio Jiménez del Coso
 */

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ClubEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(unique = true)
    @Email(message = "Invalid email format")
    private String email;

    @Column
    @Size(min = 8, message = "Password should have more that 8 characters")
    private String password;
    private String fullName;
    private String sortName;

    @Column()
    @Size(max = 8, message = "Federation name cant have more that 8 characters")
    private String federationName;
    private boolean isPublic;

    @OneToMany(mappedBy = "clubEntry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayerEntry> playerEntryList;

    public ClubEntry(String email, String password, String fullName, String sortName, String federationName, boolean isPublic) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.sortName = sortName;
        this.federationName = federationName;
        this.isPublic = isPublic;
    }

    public ClubEntry(long clubId, String email, String password, String fullName, String sortName, String federationName, boolean isPublic) {
        this.id = clubId;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.sortName = sortName;
        this.federationName = federationName;
        this.isPublic = isPublic;
    }
}
