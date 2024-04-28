package com.clubing.application.app.rest.converter.util;


import com.clubing.application.app.rest.api.dto.PlayerDTO;
import com.clubing.application.app.service.model.PlayerEntry;

/**
 * @author Sergio Jiménez del Coso
 */

public class PlayerDTOConverterUtil {

    public static PlayerDTO toDTO(long playerId, String playerName, String playerFamilyName) {
        return new PlayerDTO() {{
            setPlayerId(playerId);
            setGivenName(playerName);
            setFamilyName(playerFamilyName);
        }};
    }

    public static PlayerDTO toDTO(PlayerEntry playerEntry) {
        return new PlayerDTO() {{
            setPlayerId(playerEntry.getId());
            setEmail(playerEntry.getEmail());
            setGivenName(playerEntry.getName());
            setFamilyName(playerEntry.getSurname());
            setNationality(playerEntry.getNationality());
            setDateOfBirth(playerEntry.getDateOfBirth());
        }};
    }
}
