package com.clubing.application.app;

import com.clubing.application.app.rest.api.dto.ClubDTO;
import com.clubing.application.app.rest.api.dto.PlayerDTO;
import com.clubing.application.app.rest.api.dto.TokenDTO;
import com.clubing.application.app.rest.api.dto.UserDTO;
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
		ClubDTO clubDTO = new ClubDTO( RandomStringUtils.random(4) + "@email.com","password", "official", "popularName", "federation", true);

		HttpHeaders headers = new HttpHeaders();

		headers.setBearerAuth(_token);

		HttpEntity<ClubDTO> httpEntity = new HttpEntity<>(clubDTO, headers);

		ResponseEntity<ClubDTO> clubDTOResponseEntity = this.testRestTemplate.exchange(_BASE_POST_CLUB, HttpMethod.POST, httpEntity, ClubDTO.class);

		_assertValidClub(clubDTOResponseEntity, clubDTO);
	}

	@Test
	public void testGetClubById() throws Exception {
		ClubDTO clubDTO = _postClub(new ClubDTO(RandomStringUtils.random(4) + "@email.com", "password",
				"official", "popularName", "federation", true));

		HttpHeaders headers = new HttpHeaders();

		headers.setBearerAuth(_token);

		HttpEntity<ClubDTO> httpEntity = new HttpEntity<>(clubDTO, headers);

		ResponseEntity<ClubDTO> clubDTOResponseEntity = this.testRestTemplate.exchange(_BASE_POST_CLUB + "/" +
				clubDTO.getClubId(), HttpMethod.GET, httpEntity, ClubDTO.class);

		_assertValidClub(clubDTOResponseEntity, clubDTO);
	}

	@Test
	public void testGetClubByIdWithSeveralPlayers() throws Exception {
		ClubDTO clubDTO = _postClub(new ClubDTO("email@email.com", "password",
				"official", "popularName", "federation", true));

		_postPlayer(new PlayerDTO("Aritz", "Aduriz", "Spanish", "arit@bilbao.com",
				new Date()), clubDTO.getClubId());

		clubDTO.setTotalPlayers(1);

		HttpHeaders headers = new HttpHeaders();

		headers.setBearerAuth(_token);

		HttpEntity<ClubDTO> httpEntity = new HttpEntity<>(clubDTO, headers);

		ResponseEntity<ClubDTO> clubDTOResponseEntity = this.testRestTemplate.exchange(_BASE_POST_CLUB + "/" +
				clubDTO.getClubId(), HttpMethod.GET, httpEntity, ClubDTO.class);

		_assertValidClub(clubDTOResponseEntity, clubDTO);
	}

	private void _assertValidClub(ResponseEntity<ClubDTO> actualClubDTO, ClubDTO expectedClubDTO) {
		then(actualClubDTO.getBody().getUserName()).isEqualTo(expectedClubDTO.getUserName());
		then(actualClubDTO.getBody().getPassword()).isEqualTo(expectedClubDTO.getPassword());
		then(actualClubDTO.getBody().getFederation()).isEqualTo(expectedClubDTO.getFederation());
		then(actualClubDTO.getBody().getOfficialName()).isEqualTo(expectedClubDTO.getOfficialName());
		then(actualClubDTO.getBody().getPopularName()).isEqualTo(expectedClubDTO.getPopularName());
		then(actualClubDTO.getBody().isPublic()).isEqualTo(expectedClubDTO.isPublic());
		then(actualClubDTO.getBody().getTotalPlayers()).isEqualTo(expectedClubDTO.getTotalPlayers());
	}

	private ClubDTO _postClub(ClubDTO clubDTO) {

		HttpHeaders headers = new HttpHeaders();

		headers.setBearerAuth(_token);

		HttpEntity<ClubDTO> httpEntity = new HttpEntity<>(clubDTO, headers);

		ResponseEntity<ClubDTO> clubDTOResponseEntity = this.testRestTemplate.exchange(_BASE_POST_CLUB, HttpMethod.POST, httpEntity, ClubDTO.class);

		return clubDTOResponseEntity.getBody();
	}

	private PlayerDTO _postPlayer(PlayerDTO playerDTO, long clubId){
		HttpHeaders headers = new HttpHeaders();

		headers.setBearerAuth(_token);

		HttpEntity<PlayerDTO> httpEntity = new HttpEntity<>(playerDTO, headers);

		ResponseEntity<PlayerDTO> playerDTOResponseEntity = this.testRestTemplate.exchange(String.format(_BASE_POST_PLAYER, clubId), HttpMethod.POST, httpEntity, PlayerDTO.class);

		return playerDTOResponseEntity.getBody();
	}
}
