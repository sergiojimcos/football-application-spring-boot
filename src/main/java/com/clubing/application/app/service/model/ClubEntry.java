package com.clubing.application.app.service.model;


/**
 * @author Sergio Jiménez del Coso
 */
public class ClubEntry {

    private long id;
    private String email;
    private String password;
    private String fullName;
    private String sortName;
    private boolean isPublic;

    public ClubEntry(long id, String email, String password, String fullName, String sortName, boolean isPublic) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.sortName = sortName;
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
}
