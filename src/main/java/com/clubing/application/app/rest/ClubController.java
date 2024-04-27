package com.clubing.application.app.rest;


import com.clubing.application.app.api.ClubService;
import com.clubing.application.app.api.PlayerService;
import com.clubing.application.app.rest.api.dto.ClubDTO;
import com.clubing.application.app.rest.api.dto.PlayerDTO;
import com.clubing.application.app.service.model.ClubEntry;
import com.clubing.application.app.service.model.PlayerEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private PlayerService playerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClubDTO> postClub(@RequestBody @Valid ClubDTO clubDTO) throws Exception {

        return ResponseEntity.ok(_toClubDTO(clubService.addClubEntry(clubDTO.getUserName(), clubDTO.getFederation(),
                clubDTO.getOfficialName(), clubDTO.getPassword(), clubDTO.getPopularName(),
                clubDTO.isPublic())));
    }

    @PostMapping(value = "/{clubId}/player", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerDTO> postPlayerBydClubId(@PathVariable @NotNull Long clubId, @RequestBody PlayerDTO playerDTO) throws Exception {

        return ResponseEntity.ok(_toPlayerDTO(playerService.addPlayerEntry(clubId, playerDTO.getGivenName(), playerDTO.getFamilyName(),
                playerDTO.getNationality(), playerDTO.getEmail(), playerDTO.getDateOfBirth())));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ClubDTO>> getClubs() throws Exception {

        Collection<ClubEntry> clubEntryCollection = clubService.getClubs();

        Collection<ClubDTO> clubDTOCollection = clubEntryCollection.stream()
                .filter(ClubEntry::isPublic)
                .map(clubEntry -> _toClubDTO(
                        clubEntry.getId(),
                        clubEntry.getFullName(),
                        clubEntry.getSortName(),
                        clubEntry.getFederationName(),
                        clubEntry.isPublic()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(clubDTOCollection);
    }

    @GetMapping(value = "/{clubId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClubDTO> getClubById(@PathVariable Long clubId) throws Exception {

        ClubDTO clubDTO = _toClubDTO(clubService.getClubEntry(clubId));
        clubDTO.setTotalPlayers(playerService.getPlayerEntriesByClubIdCount(clubId));

        return ResponseEntity.ok(clubDTO);
    }

    @PutMapping(value = "/{clubId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClubDTO> putClubById(@PathVariable Long clubId, @RequestBody ClubDTO clubDTO) throws Exception {

        return ResponseEntity.ok(_toClubDTO(clubService.updateClubEntry(clubId, clubDTO.getUserName(),
                clubDTO.getFederation(), clubDTO.getOfficialName(), clubDTO.getPassword(), clubDTO.getPopularName(),
                clubDTO.isPublic())));
    }

    @GetMapping(value = "/{clubId}/player", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<PlayerDTO>> getAllPlayersByClubId(@PathVariable Long clubId) throws Exception {

        Collection<PlayerEntry> playerEntriesByClubId = playerService.getPlayerEntriesByClubId(clubId);

        Collection<PlayerDTO> playerDTOCollection = playerEntriesByClubId.stream()
                .map(playerEntry -> _toPlayerDTO(
                        playerEntry.getId(),
                        playerEntry.getName(),
                        playerEntry.getSurname()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(playerDTOCollection);

    }

    @GetMapping(value = "/{clubId}/player/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerDTO> getPlayerByClubIdAndPlayerId(@PathVariable Long clubId, @PathVariable Long playerId) throws Exception {

        clubService.getClubEntry(clubId);

        return ResponseEntity.ok(_toPlayerDTO(playerService.getPlayerEntryByClubIdAndPlayerId(clubId, playerId)));
    }

    @DeleteMapping(value = "/{clubId}/player/{playerId}")
    public ResponseEntity<?> deletePlayerByClubIdAndPlayerId(@PathVariable Long clubId, @PathVariable Long playerId) throws Exception {

        clubService.getClubEntry(clubId);

        playerService.deletePlayerEntry(clubId, playerId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{clubId}/player/{playerId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerDTO> putPlayerByClubIdAndPlayerId(@PathVariable Long clubId, @PathVariable
    Long playerId, @RequestBody PlayerDTO playerDTO) throws Exception {

        clubService.getClubEntry(clubId);

        return ResponseEntity.ok(_toPlayerDTO(playerService.updatePlayerEntry(playerId, playerDTO.getGivenName(),
                playerDTO.getFamilyName(), playerDTO.getNationality(), playerDTO.getEmail(),
                playerDTO.getDateOfBirth(), clubId)));

    }

    private ClubDTO _toClubDTO(long clubId, String officialName, String popularName, String federation, boolean isPublic) {
        return new ClubDTO() {{
            setClubId(clubId);
            setOfficialName(officialName);
            setPopularName(popularName);
            setFederation(federation);
            setPublic(isPublic);
        }};
    }

    private ClubDTO _toClubDTO(ClubEntry clubEntry) {
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

    private PlayerDTO _toPlayerDTO(PlayerEntry playerEntry) {
        return new PlayerDTO() {{
            setPlayerId(playerEntry.getId());
            setEmail(playerEntry.getEmail());
            setGivenName(playerEntry.getName());
            setFamilyName(playerEntry.getSurname());
            setNationality(playerEntry.getNationality());
            setDateOfBirth(playerEntry.getDateOfBirth());
        }};
    }

    private PlayerDTO _toPlayerDTO(long playerId, String playerName, String playerFamilyName) {
        return new PlayerDTO() {{
            setPlayerId(playerId);
            setGivenName(playerName);
            setFamilyName(playerFamilyName);
        }};
    }
}
