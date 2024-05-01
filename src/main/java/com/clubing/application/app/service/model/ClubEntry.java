package com.clubing.application.app.service.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * @author Sergio Jiménez del Coso
 */

@Entity
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
    @Size(max = 8)
    private String federationName;
    private boolean isPublic;

    public ClubEntry(String email, String password, String fullName, String sortName, String federationName, boolean isPublic) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.sortName = sortName;
        this.federationName = federationName;
        this.isPublic = isPublic;
    }

    public ClubEntry() {
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

    public void setId(long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getSortName() {
        return sortName;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public String getFederationName() {
        return federationName;
    }

    public void setFederationName(String federationName) {
        this.federationName = federationName;
    }
}
