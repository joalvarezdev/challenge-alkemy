package com.joalvarez.challengealkemy.service.interfaces;

import com.joalvarez.challengealkemy.service.generals.IBaseService;

public interface IUserService<DTO> extends IBaseService<DTO, Long>{

	DTO register(DTO dto);
}
