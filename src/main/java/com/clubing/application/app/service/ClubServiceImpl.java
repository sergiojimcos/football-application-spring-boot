package com.clubing.application.app.service;

import com.clubing.application.app.api.ClubService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Sergio Jiménez del Coso
 */

@Service
public class ClubServiceImpl implements ClubService {
    @Override
    public Object addClubEntry(String email, String federation, String officialName, String password, String popularName, boolean isPublic) throws Exception {
        return null;
    }

    @Override
    public void deleteClubEntry(long clubId) throws Exception {

    }

    @Override
    public Object fetchClubEntry(long clubId) {
        return null;
    }

    @Override
    public List<Object> getClubs() throws Exception {
        return List.of();
    }

    @Override
    public Object getClubEntry(long clubId) throws Exception {
        return null;
    }
}
