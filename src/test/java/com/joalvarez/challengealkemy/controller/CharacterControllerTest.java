package com.joalvarez.challengealkemy.controller;

import com.joalvarez.challengealkemy.data.dto.CharacterDTO;
import com.joalvarez.challengealkemy.service.interfaces.ICharacterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CharacterControllerTest {

	@InjectMocks
	private CharacterController controller;
	@Mock
	private ICharacterService service;

	@Test
	public void shouldFindCharacterByValidId() {
		final var validId = 1L;
		var character = new CharacterDTO();
		character.setCharacterId(1L);
		character.setName("Mickey Mouse");
		character.setImage("https://example.com/mickey-mouse.jpg");
		character.setAge(93);
		character.setWeight(23);
		character.setHistory("Mickey Mouse es un personaje animado creado por Walt Disney y Ub Iwerks en 1928. Es uno de los personajes más icónicos de Disney y es conocido por su personalidad alegre y amigable.");

		when(this.service.findById(validId)).thenReturn(character);

		final ResponseEntity<CharacterDTO> response = this.controller.findById(validId);

		assertAll(
			() -> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK),
			() -> assertNotNull(response.getBody()),
			() -> assertEquals(CharacterDTO.class, Objects.requireNonNull(response.getBody()).getClass()),
			() -> assertEquals(validId, Objects.requireNonNull(response.getBody()).getCharacterId())
		);
	}

	@Test
	public void shouldFindAllPaginatedCharacters() throws Exception {
		var pageable = Pageable.unpaged();
		var characterList = new ArrayList<CharacterDTO>();
		characterList.add(new CharacterDTO());

		when(this.service.findAll(any())).thenReturn(new PageImpl<>(characterList));

		final ResponseEntity<Page<CharacterDTO>> response = this.controller.findAll(pageable, 0, 10, "", "");

		assertAll(
			() -> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK),
			() -> assertNotNull(response.getBody()),
			() -> assertEquals(characterList.size(), Objects.requireNonNull(response.getBody()).getSize())
		);
	}

	@Test
	public void shouldCreateCharacterSuccess() {
		var character = new CharacterDTO();
		character.setCharacterId(1L);
		character.setName("Mickey Mouse");
		character.setImage("https://example.com/mickey-mouse.jpg");
		character.setAge(93);
		character.setWeight(23);
		character.setHistory("Mickey Mouse es un personaje animado creado por Walt Disney y Ub Iwerks en 1928. Es uno de los personajes más icónicos de Disney y es conocido por su personalidad alegre y amigable.");

		when(this.service.create(any())).thenReturn(character);

		final var response = this.controller.create(character);

		assertAll(
			() -> assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED),
			() -> assertNotNull(response.getBody()),
			() -> assertEquals(CharacterDTO.class, Objects.requireNonNull(response.getBody()).getClass()),
			() -> assertEquals(character.getName(), Objects.requireNonNull(response.getBody()).getName())
		);
	}

	@Test
	public void shouldUpdateCharacterSuccess() {

	}
}