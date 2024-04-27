package com.clubing.application.app.rest;


import com.clubing.application.app.rest.api.dto.ClubDTO;
import com.clubing.application.app.rest.api.dto.PlayerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubController {

    @PostMapping
    public ResponseEntity<ClubDTO> postClub(@RequestBody ClubDTO clubDTO) {

        return null;
    }

    @PostMapping("/{clubId}/player")
    public ResponseEntity<String> postClubWithPlayer(@PathVariable Long clubId, @RequestBody PlayerDTO playerDTO) {

        return null;
    }

    @GetMapping
    public ResponseEntity<List<ClubDTO>> getClubs() {
        return null;
    }

    @GetMapping("/{clubId}")
    public ResponseEntity<String> getClubById(@PathVariable Long clubId) {
        return null;
    }

    @GetMapping("/{clubId}/player")
    public ResponseEntity<List<PlayerDTO>> getAllPlayersByClubId(@PathVariable Long clubId) {

        return null;
    }

    @GetMapping("/{clubId}/player/{playerId}")
    public ResponseEntity<String> getPlayerById(@PathVariable Long clubId, @PathVariable Long playerId) {


        return null;
    }






}
