package com.clubing.application.app.rest.impl.converter;

import com.clubing.application.app.rest.api.dto.PlayerDTO;
import com.clubing.application.app.service.model.PlayerEntry;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Sergio Jiménez del Coso
 */

@Component
public class PlayerDTOConverter implements Converter<PlayerEntry, PlayerDTO> {

    public PlayerDTO toDTO(long playerId, String playerName, String playerFamilyName) {
        return new PlayerDTO() {{
            setPlayerId(playerId);
            setGivenName(playerName);
            setFamilyName(playerFamilyName);
        }};
    }


    @Override
    public PlayerDTO convert(PlayerEntry playerEntry) {
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
