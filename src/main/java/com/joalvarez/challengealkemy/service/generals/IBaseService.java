package com.joalvarez.challengealkemy.service.generals;

import java.util.List;

public interface IBaseService<DTO, PK> {

	List<DTO> findAll();
	DTO findById(PK id);
	DTO update(DTO dto, PK id);
	DTO create(DTO dto);
}
