package com.joalvarez.challengealkemy.service.interfaces;

import com.joalvarez.challengealkemy.service.generals.IBaseService;

import java.util.Optional;

public interface IRoleService<DTO> extends IBaseService<DTO, Long>{

	Optional<DTO> findByName(String name);
}
