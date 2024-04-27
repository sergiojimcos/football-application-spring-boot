package com.clubing.application.app.service.impl;

import com.clubing.application.app.api.ClubService;
import com.clubing.application.app.api.PlayerService;
import com.clubing.application.app.rest.api.dto.PlayerDTO;
import com.clubing.application.app.service.model.ClubEntry;
import com.clubing.application.app.service.model.PlayerEntry;
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

    HashMap<Long, PlayerEntry> _playerMap = new HashMap<>();

    @Override
    public PlayerEntry addPlayerEntry(long clubId, String name, String surname, String nationality, String email, Date birthDate) throws Exception {
        PlayerEntry playerEntry = new PlayerEntry(new Random().nextLong(), name, surname, nationality, email, birthDate, clubId);

        return _playerMap.put(playerEntry.getId(), playerEntry);
    }

    @Override
    public PlayerEntry getPlayerEntry(long playerId) throws Exception {
       return _playerMap.get(playerId);
    }

    @Override
    public Collection<PlayerEntry> getPlayerEntriesByClubId(long clubId) throws Exception {

        return _getPlayerEntriesByClubId(clubId);
    }

    @Override
    public PlayerEntry getPlayerEntryByClubIdAndPlayerId(long clubId, long playerId) throws Exception {

        return _getPlayerEntriesByClubId(clubId).stream()
                .filter(playerEntry -> playerId == playerEntry.getId()).
                findFirst().orElse(null);
    }

    @Override
    public int getPlayerEntriesByClubIdCount(long clubId) throws Exception {
        Collection<PlayerEntry> playerEntries = _getPlayerEntriesByClubId(clubId);

        return playerEntries.size();
    }

    private Collection<PlayerEntry> _getPlayerEntriesByClubId(long clubId) throws Exception {

        Collection<PlayerEntry> playerEntries = _playerMap.values();

        return playerEntries.stream()
                .filter((playerEntry -> playerEntry.getClubId() == clubId))
                .collect(Collectors.toList());
    }


}
