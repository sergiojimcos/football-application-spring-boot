package com.clubing.application.app.service.impl;

import com.clubing.application.app.api.ClubService;
import com.clubing.application.app.rest.impl.exception.NotFoundException;
import com.clubing.application.app.service.model.ClubEntry;
import com.clubing.application.app.service.repository.ClubRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;

/**
 * @author Sergio Jiménez del Coso
 */

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private ClubRepository clubRepository;

    @Override
    public ClubEntry addClubEntry(String email, String password, String officialName, String popularName,
                                  String federation, boolean isPublic) throws Exception {

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

        return clubRepository.findAllByPublic();
    }

    @Override
    public ClubEntry getClubEntry(long clubId) throws Exception {

        return clubRepository.findById(clubId).orElseThrow(() -> new NotFoundException("ClubEntry not found with id: " + clubId));
    }

    @Override
    public ClubEntry updateClubEntry(long clubId, String email, String password, String officialName, String popularName,
                                     String federation, Boolean isPublic) throws Exception {

        ClubEntry clubEntry = getClubEntry(clubId);

        if (!StringUtils.isEmpty(email)) {
            clubEntry.setEmail(email);
        }
        if (!StringUtils.isEmpty(password)) {
            clubEntry.setPassword(password);
        }
        if (!StringUtils.isEmpty(officialName)) {
            clubEntry.setFullName(officialName);
        }
        if (!StringUtils.isEmpty(popularName)) {
            clubEntry.setSortName(popularName);
        }
        if (!StringUtils.isEmpty(federation)) {
            clubEntry.setFederationName(federation);
        }
        if (!Objects.isNull(isPublic)) {
            clubEntry.setPublic(isPublic);
        }

        return clubRepository.save(clubEntry);

    }
}
