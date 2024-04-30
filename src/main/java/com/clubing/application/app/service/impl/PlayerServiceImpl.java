package com.clubing.application.app.service.impl;

import com.clubing.application.app.api.ClubService;
import com.clubing.application.app.api.PlayerService;
import com.clubing.application.app.rest.exception.NotFoundException;
import com.clubing.application.app.service.model.ClubEntry;
import com.clubing.application.app.service.model.PlayerEntry;
import com.clubing.application.app.service.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

        _validateClub(clubId);

        return playerRepository.save(new PlayerEntry(name, surname, nationality, email, birthDate, clubId));
    }

    @Override
    public void deletePlayerEntry(long clubId, long playerId) throws Exception {

        _validateClub(clubId);

        PlayerEntry playerEntry = _getPlayerEntriesByClubId(clubId).stream()
                .filter(player -> playerId == player.getId()).
                findFirst().orElse(null);

        playerRepository.deleteById(playerEntry.getId());
    }

    @Override
    public PlayerEntry getPlayerEntry(long playerId) throws Exception {
        return playerRepository.findById(playerId).orElseThrow(() -> new
                NotFoundException("PlayerEntry not found with id: " + playerId));
    }

    @Override
    public Collection<PlayerEntry> getPlayerEntriesByClubId(long clubId) throws Exception {
        _validateClub(clubId);

        return _getPlayerEntriesByClubId(clubId);
    }

    @Override
    public PlayerEntry getPlayerEntryByClubIdAndPlayerId(long clubId, long playerId) throws Exception {

        _validateClub(clubId);

        return _getPlayerEntriesByClubId(clubId).stream()
                .filter(playerEntry -> playerId == playerEntry.getId()).
                findFirst().orElse(null);
    }

    @Override
    public int getPlayerEntriesByClubIdCount(long clubId) throws Exception {

        _validateClub(clubId);

        Collection<PlayerEntry> playerEntries = _getPlayerEntriesByClubId(clubId);

        return playerEntries.size();
    }

    @Override
    public PlayerEntry updatePlayerEntry(long playerId, String name, String surname, String nationality, String email, Date birthDate, long clubId) throws Exception {

        _validateClub(clubId);

        getPlayerEntry(playerId);

        return playerRepository.save(new PlayerEntry(playerId, name, surname, nationality, email, birthDate, clubId));
    }

    private Collection<PlayerEntry> _getPlayerEntriesByClubId(long clubId) throws Exception {

        return playerRepository.findAll().stream()
                .filter((playerEntry -> playerEntry.getClubId() == clubId))
                .collect(Collectors.toList());
    }

    private void _validateClub(long clubId) {
        ClubEntry clubEntry = clubService.fetchClubEntry(clubId);

        if (clubEntry == null) {
            throw new NotFoundException("Club not found");
        }
    }
}
