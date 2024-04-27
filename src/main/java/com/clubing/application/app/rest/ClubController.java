package com.clubing.application.app.rest;


import com.clubing.application.app.rest.api.dto.ClubDTO;
import com.clubing.application.app.rest.api.dto.PlayerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClubDTO> postClub(@RequestBody ClubDTO clubDTO) {

        return null;
    }

    @PostMapping(value = "/{clubId}/player", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postClubWithPlayer(@PathVariable Long clubId, @RequestBody PlayerDTO playerDTO) {

        return null;
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClubDTO>> getClubs() {
        return null;
    }

    @GetMapping(value ="/{clubId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getClubById(@PathVariable Long clubId) {
        return null;
    }

    @GetMapping(value = "/{clubId}/player", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayerDTO>> getAllPlayersByClubId(@PathVariable Long clubId) {

        return null;
    }

    @GetMapping(value = "/{clubId}/player/{playerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPlayerById(@PathVariable Long clubId, @PathVariable Long playerId) {


        return null;
    }






}
