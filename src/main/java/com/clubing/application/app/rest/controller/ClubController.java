package com.clubing.application.app.rest.controller;

import com.clubing.application.app.api.ClubService;
import com.clubing.application.app.api.PlayerService;
import com.clubing.application.app.rest.api.dto.ClubDTO;
import com.clubing.application.app.rest.api.dto.PlayerDTO;
import com.clubing.application.app.rest.converter.util.ClubDTOConverterUtil;
import com.clubing.application.app.rest.converter.util.PlayerDTOConverterUtil;
import com.clubing.application.app.service.model.ClubEntry;
import com.clubing.application.app.service.model.PlayerEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.nio.file.AccessDeniedException;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private ClubService clubService;

    @Autowired
    private PlayerService playerService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClubDTO> postClub(@RequestBody @Valid ClubDTO clubDTO) throws Exception {

        return ResponseEntity.ok(ClubDTOConverterUtil.toDTO(clubService.addClubEntry(clubDTO.getUserName(), clubDTO.getFederation(),
                clubDTO.getOfficialName(), clubDTO.getPassword(), clubDTO.getPopularName(),
                clubDTO.isPublic())));
    }

    @PostMapping(value = "/{clubId}/player", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerDTO> postPlayerBydClubId(@PathVariable @NotNull Long clubId,
                                                         @RequestBody @Valid PlayerDTO playerDTO) throws Exception {

        return ResponseEntity.ok(PlayerDTOConverterUtil.toDTO(playerService.addPlayerEntry(clubId, playerDTO.getGivenName(),
                playerDTO.getFamilyName(), playerDTO.getNationality(), playerDTO.getEmail(),
                playerDTO.getDateOfBirth())));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ClubDTO>> getClubs() throws Exception {

        Collection<ClubEntry> clubEntryCollection = clubService.getClubs();

        Collection<ClubDTO> clubDTOCollection = clubEntryCollection.stream()
                .filter(ClubEntry::isPublic)
                .map(clubEntry -> ClubDTOConverterUtil.toDTO(
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
    public ResponseEntity<ClubDTO> getClubById(@PathVariable @NotNull Long clubId) throws Exception {

        ClubDTO clubDTO = ClubDTOConverterUtil.toDTO(clubService.getClubEntry(clubId));
        clubDTO.setTotalPlayers(playerService.getPlayerEntriesByClubIdCount(clubId));

        return ResponseEntity.ok(clubDTO);
    }

    @PutMapping(value = "/{clubId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClubDTO> putClubById(@PathVariable @NotNull Long clubId,
                                               @RequestBody @NotNull ClubDTO clubDTO) throws Exception {

        return ResponseEntity.ok(ClubDTOConverterUtil.toDTO(clubService.updateClubEntry(clubId, clubDTO.getUserName(),
                clubDTO.getFederation(), clubDTO.getOfficialName(), clubDTO.getPassword(), clubDTO.getPopularName(),
                clubDTO.isPublic())));
    }

    @GetMapping(value = "/{clubId}/player", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<PlayerDTO>> getAllPlayersByClubId(@PathVariable @NotNull Long clubId) throws Exception {

        Collection<PlayerEntry> playerEntriesByClubId = playerService.getPlayerEntriesByClubId(clubId);

        Collection<PlayerDTO> playerDTOCollection = playerEntriesByClubId.stream()
                .map(playerEntry -> PlayerDTOConverterUtil.toDTO(
                        playerEntry.getId(),
                        playerEntry.getName(),
                        playerEntry.getSurname()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(playerDTOCollection);

    }

    @GetMapping(value = "/{clubId}/player/{playerId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerDTO> getPlayerByClubIdAndPlayerId(@PathVariable @NotNull Long clubId,
                                                                  @PathVariable @NotNull Long playerId) throws Exception {

        clubService.getClubEntry(clubId);

        return ResponseEntity.ok(PlayerDTOConverterUtil.toDTO(playerService.getPlayerEntryByClubIdAndPlayerId(clubId, playerId)));
    }

    @DeleteMapping(value = "/{clubId}/player/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deletePlayerByClubIdAndPlayerId(@PathVariable @NotNull Long clubId,
                                                             @PathVariable @NotNull Long playerId) throws Exception {

        clubService.getClubEntry(clubId);

        playerService.deletePlayerEntry(clubId, playerId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{clubId}/player/{playerId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PlayerDTO> putPlayerByClubIdAndPlayerId(@PathVariable @NotNull Long clubId,
                                                                  @PathVariable @NotNull Long playerId,
                                                                  @RequestBody @Valid PlayerDTO playerDTO) throws Exception {

        clubService.getClubEntry(clubId);

        return ResponseEntity.ok(PlayerDTOConverterUtil.toDTO(playerService.updatePlayerEntry(playerId, playerDTO.getGivenName(),
                playerDTO.getFamilyName(), playerDTO.getNationality(), playerDTO.getEmail(),
                playerDTO.getDateOfBirth(), clubId)));

    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handlePermissionException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
}
