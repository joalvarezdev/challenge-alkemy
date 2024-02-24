package com.joalvarez.challengealkemy.controller;

import com.joalvarez.challengealkemy.data.dto.UserDTO;
import com.joalvarez.challengealkemy.service.interfaces.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

	private final IUserService<UserDTO> service;

	public UserController(IUserService<UserDTO> service) {
		this.service = service;
	}

	@PostMapping("register")
	public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.register(dto));
	}
}
