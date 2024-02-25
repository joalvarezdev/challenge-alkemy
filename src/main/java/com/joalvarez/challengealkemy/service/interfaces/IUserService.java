package com.joalvarez.challengealkemy.service.interfaces;

import com.joalvarez.challengealkemy.data.dto.UserDTO;
import com.joalvarez.challengealkemy.service.generals.IBaseService;

public interface IUserService extends IBaseService<UserDTO, Long>{

	UserDTO register(UserDTO dto);
}
