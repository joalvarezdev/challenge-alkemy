package com.joalvarez.challengealkemy.service.interfaces;

import com.joalvarez.challengealkemy.data.dto.CharacterDTO;
import com.joalvarez.challengealkemy.service.generals.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ICharacterService extends IBaseService<CharacterDTO, Long> {

	Page<CharacterDTO> findAll(Pageable pageable);
}
