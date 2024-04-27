package com.clubing.application.app.api;

/**
 * @author Sergio Jiménez del Coso
 */
public interface ClubService {

    public Object addClubEntry(String email, String federation, String officialName, String password,
                               String popularName, boolean isPublic) throws Exception;

    public void deleteClubEntry(long clubId) throws Exception;

    public Object fetchClubEntry(long clubId);

    public Object getClubEntry(long clubId) throws Exception;

}
