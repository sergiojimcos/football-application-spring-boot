package com.clubing.application.app.rest.impl.converter;

import com.clubing.application.app.rest.api.dto.MatchOutDTO;
import com.clubing.application.app.service.model.Match;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

public class MatchDTOConverter implements Converter<Match, MatchOutDTO> {

    @Override
    public MatchOutDTO convert(Match match) {

        return new MatchOutDTO(){{
            setMatchDate(new Date(match.getMatchDate().getTime()));
            setLocalClub(ClubDTOConverterUtil.toDTO(match.getLocalClubEntry()));
            setVisitantClub(ClubDTOConverterUtil.toDTO(match.getVisitantClubEntry()));
        }};
    }
}
