package com.joalvarez.challengealkemy.service;

import com.joalvarez.challengealkemy.data.dao.CharacterDAO;
import com.joalvarez.challengealkemy.data.dto.CharacterDTO;
import com.joalvarez.challengealkemy.data.mapper.CharacterMapper;
import com.joalvarez.challengealkemy.data.model.Character;
import com.joalvarez.challengealkemy.exception.generals.GenericException;
import com.joalvarez.challengealkemy.exception.generals.NotImplementedException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CharacterServiceTest {

	@InjectMocks
	private CharacterService service;
	@Mock
	private CharacterDAO dao;
	@Mock
	private CharacterMapper mapper;

	@Test
	public void updateCharacterSuccess() {
		var validId = 1L;
		var character = this.createCharacter(validId);
		var characterDTO = this.createCharacterDTO(validId);

		when(this.dao.existsById(validId)).thenReturn(true);
		when(this.dao.findById(validId)).thenReturn(character);
		when(this.dao.findByName(characterDTO.getName())).thenReturn(Optional.empty());
		when(this.mapper.toDTO(character)).thenReturn(characterDTO);

		var result = this.service.update(characterDTO, validId);

		assertAll(
			() -> assertNotNull(result),
			() -> assertEquals("name", result.getName()),
			() -> assertEquals(23, result.getAge()),
			() -> assertEquals("image", result.getImage()),
			() -> assertEquals("history", result.getHistory()),
			() -> assertEquals(87, result.getWeight()),
			() -> verify(this.dao, times(1)).existsById(validId),
			() -> verify(this.dao, times(1)).findById(validId),
			() -> verify(this.dao, times(1)).findByName(characterDTO.getName()),
			() -> verify(this.dao, times(1)).save(character),
			() -> verify(this.mapper, times(1)).toDTO(character)
		);

	}

	@Test
	public void throwExceptionWhenCharacterExistByName() {
		var validNameCharacter = "nameValid";
		var exists = true;

		var dto = new CharacterDTO();
		dto.setName(validNameCharacter);

		when(this.dao.existsByName(validNameCharacter)).thenReturn(exists);

		var exception = assertThrows(GenericException.class, () -> this.service.create(dto));

		assertAll(
			() -> assertEquals(HttpStatus.NOT_FOUND, exception.getCode()),
			() -> assertEquals("The character already exists.", exception.getMessage()),
			() -> assertEquals(8005, exception.getResponseCode())
		);
	}

	@Test
	public void throwExceptionWhenFindAllIsCalled() {

		var exception = assertThrows(NotImplementedException.class, () -> service.findAll());

		assertAll(
			() -> assertEquals(HttpStatus.NOT_IMPLEMENTED, exception.getCode()),
			() -> assertEquals("The resource you're trying to access or execute isn't implemented yet", exception.getMessage()),
			() -> assertEquals(8003, exception.getResponseCode())
		);
	}

	@Test
	public void throwExceptionWhenCharacterNotExist() {

		when(dao.findById(1L)).thenReturn(null);

		var exception = assertThrows(GenericException.class, () -> this.service.findById(1L));

		assertAll(
			() -> assertEquals(HttpStatus.NOT_FOUND, exception.getCode()),
			() -> assertEquals("The character doesn't exist.", exception.getMessage()),
			() -> assertEquals(8006, exception.getResponseCode())
		);
	}

	@Test
	public void throwExceptionWhenCharacterNotExistBoolean() {
		var character = new CharacterDTO();
		character.setName("name");
		character.setAge(23);
		character.setImage("image");
		character.setHistory("history");
		character.setWeight(87);

		when(this.dao.existsById(1L)).thenReturn(false);

		var exception = assertThrows(GenericException.class, () -> this.service.update(character, 1L));

		assertAll(
			() -> assertEquals(HttpStatus.NOT_FOUND, exception.getCode()),
			() -> assertEquals("The character doesn't exist.", exception.getMessage()),
			() -> assertEquals(8006, exception.getResponseCode())
		);
	}

	private Character createCharacter(long id) {
		var character = new Character();

		character.setCharacterId(id);
		character.setName("name");
		character.setAge(23);
		character.setImage("image");
		character.setHistory("history");
		character.setWeight(87);

		return character;
	}

	private CharacterDTO createCharacterDTO(long id) {
		var character = new CharacterDTO();

		character.setCharacterId(id);
		character.setName("name");
		character.setAge(23);
		character.setImage("image");
		character.setHistory("history");
		character.setWeight(87);

		return character;
	}
}