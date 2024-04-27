package com.clubing.application.app.rest;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubController {

    private List clubs = new ArrayList();

    @PostMapping
    public ResponseEntity<String> postClub(@RequestBody String club) {
        clubs.add(club);
        return new ResponseEntity<>("Club creado exitosamente", HttpStatus.CREATED);
    }

    @PostMapping("/{clubId}/player")
    public ResponseEntity<String> postClubWithPlayer(@PathVariable Long clubId, @RequestBody String player) {

        return new ResponseEntity<>("Player created", HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<String>> getClubs() {
        return new ResponseEntity<>(clubs, HttpStatus.OK);
    }

    @GetMapping("/{clubId}")
    public ResponseEntity<String> getClub(@PathVariable Long clubId) {
        return null;
    }

    @GetMapping("/{clubId}/player")
    public ResponseEntity<List<String>> getAllPlayersByClubId(@PathVariable Long clubId) {

        List<String> players = new ArrayList<>();

        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/{clubId}/player/{playerId}")
    public ResponseEntity<String> getPlayerById(@PathVariable Long clubId, @PathVariable Long playerId) {


        return null;
    }






}
