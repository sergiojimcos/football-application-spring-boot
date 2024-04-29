package com.clubing.application.app.service.impl;

import com.clubing.application.app.api.ClubService;
import com.clubing.application.app.rest.exception.NotFoundException;
import com.clubing.application.app.service.model.ClubEntry;
import com.clubing.application.app.service.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Sergio Jiménez del Coso
 */

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @Override
    public ClubEntry addClubEntry(String email, String federation, String officialName, String password, String popularName, boolean isPublic) throws Exception {

        return clubRepository.save(new ClubEntry(email, password, officialName, popularName, federation, isPublic));

    }

    @Override
    public void deleteClubEntry(long clubId) throws Exception {

        clubRepository.deleteById(clubId);
    }

    @Override
    public ClubEntry fetchClubEntry(long clubId) {

        return clubRepository.findById(clubId).orElse(null);
    }

    @Override
    public Collection<ClubEntry> getClubs() throws Exception {
        return clubRepository.findAll();
    }

    @Override
    public ClubEntry getClubEntry(long clubId) throws Exception {
        return clubRepository.findById(clubId).orElseThrow(() -> new NotFoundException("ClubEntry not found with id: " + clubId));
    }

    @Override
    public ClubEntry updateClubEntry(long clubId, String email, String federation, String officialName, String password,
                                     String popularName, boolean isPublic) throws Exception {

        ClubEntry updatedClubEntry = clubRepository.updateClubEntryById(clubId, new ClubEntry(email, password,
                officialName, popularName, federation, isPublic));

        if (updatedClubEntry == null) {
            throw new NotFoundException("ClubEntry not found with id: " + clubId);
        }
        return updatedClubEntry;

    }
}
