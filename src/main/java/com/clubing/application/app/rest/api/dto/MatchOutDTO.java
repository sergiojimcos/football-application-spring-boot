package com.clubing.application.app.rest.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Sergio Jiménez del Coso
 */


@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Setter
@Getter
@NoArgsConstructor
public class MatchOutDTO {

    private Date matchDate;

    private ClubDTO localClub;

    private ClubDTO visitantClub;
}
