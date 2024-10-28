package com.clubing.application.app.rest.impl.controller;

import com.clubing.application.app.api.MatchService;
import com.clubing.application.app.rest.api.dto.MatchDTO;
import com.clubing.application.app.rest.api.dto.MatchOutDTO;
import com.clubing.application.app.rest.impl.converter.util.MatchDTOConverter;
import com.clubing.application.app.service.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Sergio Jiménez del Coso
 */

@RestController
@RequestMapping(value = "/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @Autowired
    private MatchDTOConverter matchDTOConverter;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MatchOutDTO> postMatch(@RequestBody @Valid MatchDTO matchDTO) throws Exception {

        Match match = matchService.addMatch(matchDTO.getMatchDate(), matchDTO.getLocalClubId(), matchDTO.getVisitantClubId());

        return ResponseEntity.ok(matchDTOConverter.convert(match));
    }


}
