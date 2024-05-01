package com.clubing.application.app.api;

import com.clubing.application.app.service.model.ClubEntry;

import java.util.Collection;

/**
 * @author Sergio Jiménez del Coso
 */
public interface ClubService {

    public ClubEntry addClubEntry(String email, String password, String officialName, String popularName,
                                  String federation, boolean isPublic) throws Exception;

    public void deleteClubEntry(long clubId) throws Exception;

    public ClubEntry fetchClubEntry(long clubId);

    public ClubEntry getClubEntry(long clubId) throws Exception;

    public Collection<ClubEntry> getClubs() throws Exception;

    public ClubEntry updateClubEntry(long clubId, String email, String password, String officialName, String popularName,
                                     String federation, boolean isPublic) throws Exception;
}
