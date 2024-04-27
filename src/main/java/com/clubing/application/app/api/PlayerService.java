package com.clubing.application.app.api;

import com.clubing.application.app.service.model.PlayerEntry;

import java.util.Collection;
import java.util.Date;

/**
 * @author Sergio Jiménez del Coso
 */
public interface PlayerService {
    public PlayerEntry addPlayerEntry(long clubId, String name, String surname, String nationality, String email,
                                 Date birthDate) throws Exception;

    public PlayerEntry getPlayerEntry(long playerId) throws Exception;

    public Collection<PlayerEntry> getPlayerEntriesByClubId(long clubId) throws Exception;

    public PlayerEntry getPlayerEntryByClubIdAndPlayerId(long clubId, long playerId) throws Exception;

    public int getPlayerEntriesByClubIdCount(long clubId) throws Exception;
}
