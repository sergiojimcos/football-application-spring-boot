package com.clubing.application.app.rest.impl.converter;

import com.clubing.application.app.rest.api.dto.ClubDTO;
import com.clubing.application.app.service.model.ClubEntry;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author Sergio Jiménez del Coso
 */

@Component
public class ClubDTOConverter implements Converter<ClubEntry, ClubDTO> {

    public ClubDTO toDTO(long clubId, String officialName, String popularName, String federation,
                                boolean isPublic) {
        return new ClubDTO() {{
            setClubId(clubId);
            setOfficialName(officialName);
            setPopularName(popularName);
            setFederation(federation);
            setPublic(isPublic);
            setTotalPlayers(null);
        }};
    }

    @Override
    public ClubDTO convert(ClubEntry clubEntry) {
        return new ClubDTO() {{
            setClubId(clubEntry.getId());
            setUserName(clubEntry.getEmail());
            setPassword(clubEntry.getPassword());
            setOfficialName(clubEntry.getFullName());
            setPopularName(clubEntry.getSortName());
            setFederation(clubEntry.getFederationName());
            setPublic(clubEntry.isPublic());
        }};
    }
}
