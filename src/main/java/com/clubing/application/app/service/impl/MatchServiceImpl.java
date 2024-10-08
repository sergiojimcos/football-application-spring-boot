package com.clubing.application.app.service.impl;

import com.clubing.application.app.api.ClubService;
import com.clubing.application.app.api.MatchService;
import com.clubing.application.app.rest.impl.exception.NotFoundException;
import com.clubing.application.app.service.model.ClubEntry;
import com.clubing.application.app.service.model.Match;
import com.clubing.application.app.service.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;

/**
 * @author Sergio Jiménez del Coso
 */

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private ClubService clubService;

    @Autowired
    private MatchRepository matchRepository;


    @Override
    public Match addMatch(Date matchDate, long localClubId, long visitantClubId) {

        ClubEntry localClubEntry = clubService.fetchClubEntry(localClubId);
        ClubEntry visitantClubEntry = clubService.fetchClubEntry(visitantClubId);

        if (localClubEntry == null) {
            throw new NotFoundException("No local club with id: " + localClubId + " is found");
        }

        if (visitantClubEntry == null) {
            throw new NotFoundException("No visitant club with id: " + localClubId + " is found");
        }

        Match match = new Match(new Time(matchDate.getTime()), localClubEntry, visitantClubEntry);

        return matchRepository.saveAndFlush(match);
    }
}
