package com.clubing.application.app.api;

import com.clubing.application.app.service.model.Match;

import java.util.Date;

/**
 * @author Sergio Jiménez del Coso
 */

public interface MatchService {

    public Match addMatch(Date matchDate, long localClubId, long visitantClubId);
}
