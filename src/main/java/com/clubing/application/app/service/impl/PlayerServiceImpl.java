package com.clubing.application.app.service.impl;

import com.clubing.application.app.api.ClubService;
import com.clubing.application.app.api.PlayerService;
import com.clubing.application.app.rest.impl.exception.NotFoundException;
import com.clubing.application.app.service.model.ClubEntry;
import com.clubing.application.app.service.model.PlayerEntry;
import com.clubing.application.app.service.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Sergio Jiménez del Coso
 */

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private ClubService clubService;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public PlayerEntry addPlayerEntry(long clubId, String name, String surname, String nationality, String email, Date birthDate) throws Exception {

        ClubEntry clubEntry = clubService.getClubEntry(clubId);

        return playerRepository.save(new PlayerEntry(name, surname, nationality, email, birthDate, clubEntry));
    }

    @Override
    public void deletePlayerEntry(long clubId, long playerId) throws Exception {

        clubService.getClubEntry(clubId);

        PlayerEntry playerEntry = playerRepository.findByClubEntry_IdAndId(clubId, playerId).
                orElseThrow(() -> new NotFoundException("Player not found"));

        playerRepository.deleteById(playerEntry.getId());
    }

    @Override
    public PlayerEntry getPlayerEntry(long playerId) throws Exception {
        return playerRepository.findById(playerId).orElseThrow(() -> new
                NotFoundException("PlayerEntry not found with id: " + playerId));
    }

    @Override
    public Collection<PlayerEntry> getPlayerEntriesByClubId(long clubId) throws Exception {
        clubService.getClubEntry(clubId);

        return playerRepository.findAllByClubEntry_Id(clubId);
    }

    @Override
    public PlayerEntry getPlayerEntryByClubIdAndPlayerId(long clubId, long playerId) throws Exception {

        clubService.getClubEntry(clubId);

        return playerRepository.findByClubEntry_IdAndId(clubId, playerId).
                orElseThrow(() -> new NotFoundException("Player not found"));
    }

    @Override
    public int getPlayerEntriesByClubIdCount(long clubId) throws Exception {

        clubService.getClubEntry(clubId);

        Collection<PlayerEntry> playerEntries = playerRepository.findAllByClubEntry_Id(clubId);

        return playerEntries.size();
    }

    @Override
    public PlayerEntry updatePlayerEntry(long playerId, String name, String surname, String nationality, String email, Date birthDate, long clubId) throws Exception {

        getPlayerEntry(playerId);

        return playerRepository.save(new PlayerEntry(playerId, name, surname, nationality, email, birthDate, clubService.getClubEntry(clubId)));
    }

}
