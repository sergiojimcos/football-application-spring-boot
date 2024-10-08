package com.clubing.application.app.rest.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Sergio Jiménez del Coso
 */


public class MatchDTO implements Serializable {

    private Long id;

    private Date matchDate;

    private Long localClubId;

    private Long visitantClubId;
}
