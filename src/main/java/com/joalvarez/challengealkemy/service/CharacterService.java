package com.joalvarez.challengealkemy.service;

import com.joalvarez.challengealkemy.constants.ErrorCode;
import com.joalvarez.challengealkemy.data.dao.CharacterDAO;
import com.joalvarez.challengealkemy.data.dto.CharacterDTO;
import com.joalvarez.challengealkemy.data.mapper.CharacterMapper;
import com.joalvarez.challengealkemy.exception.generals.GenericException;
import com.joalvarez.challengealkemy.exception.generals.NotImplementedException;
import com.joalvarez.challengealkemy.service.generals.GenericService;
import com.joalvarez.challengealkemy.service.interfaces.ICharacterService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CharacterService extends GenericService<CharacterDAO, CharacterMapper> implements ICharacterService {

	public CharacterService(CharacterDAO characterDAO, CharacterMapper mapper) {
		super(characterDAO, mapper);
	}

	@Override
	public List<CharacterDTO> findAll() {
		throw new NotImplementedException();
	}

	@Override
	public CharacterDTO findById(Long id) {
		throw new NotImplementedException();
	}

	@Override
	public CharacterDTO update(CharacterDTO dto, Long id) {
		var isExists = this.dao.existsById(id);

		if (!isExists) {
			throw new GenericException(
				HttpStatus.NOT_FOUND,
				ErrorCode.CHARACTER_NOT_FOUND
			);
		}

		var entity = this.dao.findById(id);
		var comparedEntity = this.dao.findByName(dto.getName());

		if (comparedEntity.isPresent() && !Objects.equals(comparedEntity.get().getId(), id)) {
			throw new GenericException(
				HttpStatus.NOT_FOUND,
				ErrorCode.CHARACTER_NAME_ALREADY_EXISTS
			);
		}

		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		entity.setHistory(dto.getHistory());
		entity.setImage(dto.getImage());
		entity.setWeight(dto.getWeight());

		this.dao.save(entity);

		return this.mapper.toDTO(entity);
	}

	@Override
	public CharacterDTO create(CharacterDTO dto) {

		if (this.dao.existsByName(dto.getName())) {
			throw new GenericException(
				HttpStatus.NOT_FOUND,
				ErrorCode.CHARACTER_ALREADY_EXISTS
			);
		}

		return this.mapper.toDTO(this.dao.save(this.mapper.fromDTO(dto)));
	}

	@Override
	public Page<CharacterDTO> findAll(Pageable pageable) {
		return this.dao.findAll(pageable)
			.map(this.mapper::toDTO);
	}
}
