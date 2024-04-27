package com.clubing.application.app.rest.api.dto;

import java.io.Serializable;

public class ClubDTO implements Serializable{

    private String userName;
    private String password;
    private String officialName;
    private String popularName;
    private String federation;
    private boolean isPublic;

    public ClubDTO() {
    }

    public ClubDTO(String userName, String password, String officialName, String popularName, String federation, boolean isPublic) {
        this.userName = userName;
        this.password = password;
        this.officialName = officialName;
        this.popularName = popularName;
        this.federation = federation;
        this.isPublic = isPublic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getPopularName() {
        return popularName;
    }

    public void setPopularName(String popularName) {
        this.popularName = popularName;
    }

    public String getFederation() {
        return federation;
    }

    public void setFederation(String federation) {
        this.federation = federation;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    @Override
    public String toString() {
        return "ClubDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", officialName='" + officialName + '\'' +
                ", popularName='" + popularName + '\'' +
                ", federation='" + federation + '\'' +
                ", isPublic=" + isPublic +
                '}';
    }
}
