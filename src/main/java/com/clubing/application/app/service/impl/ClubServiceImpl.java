package com.clubing.application.app.service.impl;

import com.clubing.application.app.api.ClubService;
import com.clubing.application.app.service.model.ClubEntry;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

/**
 * @author Sergio Jiménez del Coso
 */

@Service
public class ClubServiceImpl implements ClubService {

    HashMap<Long, ClubEntry> _clubMap = new HashMap<>();

    @Override
    public ClubEntry addClubEntry(String email, String federation, String officialName, String password, String popularName, boolean isPublic) throws Exception {

        ClubEntry clubEntry = new ClubEntry(new Random().nextLong(), email, password, officialName, popularName, federation, isPublic);

        return _clubMap.put(clubEntry.getId(), clubEntry);

    }

    @Override
    public void deleteClubEntry(long clubId) throws Exception {

        _clubMap.remove(clubId);
    }

    @Override
    public ClubEntry fetchClubEntry(long clubId) {

        return _clubMap.get(clubId);
    }

    @Override
    public Collection<ClubEntry> getClubs() throws Exception {
        return _clubMap.values();
    }

    @Override
    public ClubEntry getClubEntry(long clubId) throws Exception {
        return _clubMap.get(clubId);
    }
}
