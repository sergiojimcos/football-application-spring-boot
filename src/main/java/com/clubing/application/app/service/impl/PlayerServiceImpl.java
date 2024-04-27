package com.clubing.application.app.service.impl;

import com.clubing.application.app.api.PlayerService;
import com.clubing.application.app.service.model.PlayerEntry;

import java.util.*;

/**
 * @author Sergio Jiménez del Coso
 */
public class PlayerServiceImpl implements PlayerService {

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
        return _playerMap.values();
    }
}
