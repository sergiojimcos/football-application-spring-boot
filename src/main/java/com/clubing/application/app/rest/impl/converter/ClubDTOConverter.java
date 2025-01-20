package com.clubing.application.app.rest.impl.converter;

import com.clubing.application.app.rest.api.dto.ClubDTO;
import com.clubing.application.app.rest.impl.infra.PageDTO;
import com.clubing.application.app.service.model.ClubEntry;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    public PageDTO<ClubDTO> toPageDTO(Page<ClubEntry> clubEntryPage) {
        Collection<ClubDTO> clubDTOCollection = clubEntryPage.getContent().stream()
                .map(clubEntry -> toDTO(
                        clubEntry.getId(),
                        clubEntry.getFullName(),
                        clubEntry.getSortName(),
                        clubEntry.getFederationName(),
                        clubEntry.isPublic()
                ))
                .collect(Collectors.toList());

        return new PageDTO<>() {{
            setContent(clubDTOCollection);
            setPageNumber(clubEntryPage.getNumber());
            setPageSize(clubEntryPage.getSize());
            setTotalElements(clubEntryPage.getTotalElements());
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
