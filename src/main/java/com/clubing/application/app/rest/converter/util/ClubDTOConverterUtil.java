package com.clubing.application.app.rest.converter.util;

import com.clubing.application.app.rest.api.dto.ClubDTO;
import com.clubing.application.app.service.model.ClubEntry;

/**
 * @author Sergio Jiménez del Coso
 */

public class ClubDTOConverterUtil {

    public static ClubDTO toDTO(long clubId, String officialName, String popularName, String federation,
                                boolean isPublic) {
        return new ClubDTO() {{
            setClubId(clubId);
            setOfficialName(officialName);
            setPopularName(popularName);
            setFederation(federation);
            setPublic(isPublic);
        }};
    }

    public static ClubDTO toDTO(ClubEntry clubEntry) {
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
