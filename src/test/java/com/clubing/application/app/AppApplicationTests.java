package com.clubing.application.app;

import com.clubing.application.app.rest.api.dto.ClubDTO;
import com.clubing.application.app.rest.api.dto.PlayerDTO;
import com.clubing.application.app.rest.api.dto.TokenDTO;
import com.clubing.application.app.rest.api.dto.UserDTO;
import com.clubing.application.app.rest.impl.exception.BadRequestException;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.BDDAssertions.then;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppApplicationTests {

    private static String _BASE_POST_CLUB;

    private static String _BASE_POST_PLAYER;

    private static String _BASE_LOGGING_USER;

    private String _token;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Before
    public void setUp() {
        _BASE_LOGGING_USER = "http://localhost:" + this.port + "/login";
        _BASE_POST_CLUB = "http://localhost:" + this.port + "/club";
        _BASE_POST_PLAYER = "http://localhost:" + this.port + "/club/%s/player";

        ResponseEntity<TokenDTO> tokenEntity = this.testRestTemplate.postForEntity(_BASE_LOGGING_USER,
                new UserDTO(RandomStringUtils.random(5), "password123_"), TokenDTO.class);

        _token = tokenEntity.getBody().getAccessToken();

    }

    @Test
    public void testCreateValidClub() throws Exception {
        ClubDTO clubDTO = new ClubDTO("validClub@email.com", "password", "official", "popularName", RandomStringUtils.random(4), true);

        ResponseEntity<ClubDTO> clubDTOResponseEntity = this.testRestTemplate.exchange(_BASE_POST_CLUB, HttpMethod.POST, _buildHttpEntity(clubDTO), ClubDTO.class);

        _assertValidClub(clubDTOResponseEntity, clubDTO);
    }

    @Test
    public void testCreateInvalidClubsWithDuplicatedEmails() throws Exception {
        ClubDTO clubDTO = new ClubDTO("duplicate@email.com", "password", "official", "popularName", RandomStringUtils.random(4), true);

        _postClub(clubDTO);

        ResponseEntity<BadRequestException> responseEntity = this.testRestTemplate.exchange(
                _BASE_POST_CLUB, HttpMethod.POST, _buildHttpEntity(clubDTO), BadRequestException.class);

        then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testCreateNotValidClub() throws Exception {

        // Invalid email

        ClubDTO clubDTO = new ClubDTO("notValidEmail1email.com", "password", "official", "popularName", RandomStringUtils.random(4), true);

        ResponseEntity<BadRequestException> responseEntity = this.testRestTemplate.exchange(
                _BASE_POST_CLUB, HttpMethod.POST, _buildHttpEntity(clubDTO), BadRequestException.class);

        then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        // Invalid password

        clubDTO.setPassword(RandomStringUtils.random(4));

        responseEntity = this.testRestTemplate.exchange(_BASE_POST_CLUB, HttpMethod.POST, _buildHttpEntity(clubDTO),
                BadRequestException.class);

        then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        // Invalid federation

        clubDTO.setPassword(RandomStringUtils.random(10));
        clubDTO.setFederation(RandomStringUtils.random(10));

        responseEntity = this.testRestTemplate.exchange(_BASE_POST_CLUB, HttpMethod.POST, _buildHttpEntity(clubDTO),
                BadRequestException.class);

        then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testGetClubById() throws Exception {
        ClubDTO clubDTO = _postClub(new ClubDTO("email1@email.com", "password",
                "official", "popularName", RandomStringUtils.random(4), true));

        ResponseEntity<ClubDTO> clubDTOResponseEntity = this.testRestTemplate.exchange(_BASE_POST_CLUB + "/" +
                clubDTO.getClubId(), HttpMethod.GET, _buildHttpEntity(clubDTO), ClubDTO.class);

        _assertValidClub(clubDTOResponseEntity, clubDTO);
    }

    @Test
    public void testGetClubByIdWithSeveralPlayers() throws Exception {
        ClubDTO clubDTO = _postClub(new ClubDTO("email2@email.com", "password",
                "official", "popularName", "federation", true));

        _postPlayer(new PlayerDTO("Aritz", "Aduriz", "Spanish", "arit@bilbao.com",
                new Date()), clubDTO.getClubId());

        clubDTO.setTotalPlayers(1);

        ResponseEntity<ClubDTO> clubDTOResponseEntity = this.testRestTemplate.exchange(_BASE_POST_CLUB + "/" +
                clubDTO.getClubId(), HttpMethod.GET, _buildHttpEntity(clubDTO), ClubDTO.class);

        _assertValidClub(clubDTOResponseEntity, clubDTO);
    }

    private void _assertValidClub(ResponseEntity<ClubDTO> actualClubDTO, ClubDTO expectedClubDTO) {
        then(actualClubDTO.getBody().getUserName()).isEqualTo(expectedClubDTO.getUserName());
        then(actualClubDTO.getBody().getPassword()).isEqualTo(expectedClubDTO.getPassword());
        then(actualClubDTO.getBody().getFederation()).isEqualTo(expectedClubDTO.getFederation());
        then(actualClubDTO.getBody().getOfficialName()).isEqualTo(expectedClubDTO.getOfficialName());
        then(actualClubDTO.getBody().getPopularName()).isEqualTo(expectedClubDTO.getPopularName());
        then(actualClubDTO.getBody().isPublic()).isEqualTo(expectedClubDTO.isPublic());
    }

    private <T> HttpEntity<T> _buildHttpEntity(T dtoEntity) {
        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(_token);

        HttpEntity<T> httpEntity = new HttpEntity<>(dtoEntity, headers);

        return httpEntity;
    }

    private ClubDTO _postClub(ClubDTO clubDTO) {

        ResponseEntity<ClubDTO> clubDTOResponseEntity = this.testRestTemplate.exchange(
                _BASE_POST_CLUB, HttpMethod.POST, _buildHttpEntity(clubDTO), ClubDTO.class);

        return clubDTOResponseEntity.getBody();
    }

    private PlayerDTO _postPlayer(PlayerDTO playerDTO, long clubId) {

        ResponseEntity<PlayerDTO> playerDTOResponseEntity = this.testRestTemplate.exchange(
                String.format(_BASE_POST_PLAYER, clubId), HttpMethod.POST, _buildHttpEntity(playerDTO), PlayerDTO.class);

        return playerDTOResponseEntity.getBody();
    }
}
