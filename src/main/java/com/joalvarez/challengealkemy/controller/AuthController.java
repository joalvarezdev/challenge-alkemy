package com.joalvarez.challengealkemy.controller;

import com.joalvarez.challengealkemy.data.dto.UserDTO;
import com.joalvarez.challengealkemy.data.dto.generals.TokenResponseDTO;
import com.joalvarez.challengealkemy.data.dto.generals.UserLoginDTO;
import com.joalvarez.challengealkemy.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

	private final AuthService service;

	public AuthController(AuthService service) {
		this.service = service;
	}

	@PostMapping("register")
	@Operation(summary = "Register User")
	@ApiResponse(responseCode = "201", description = "Successful operation", content = @Content(
		mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserDTO.class)))
	public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO dto) {
		return ResponseEntity.ok(this.service.register(dto));
	}

	@PostMapping("login")
	@Operation(summary = "Login User")
	@ApiResponse(responseCode = "200", description = "Successful operation",
		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = TokenResponseDTO.class)))
	public ResponseEntity<TokenResponseDTO> login(@Valid @RequestBody UserLoginDTO dto) {
		return ResponseEntity.ok(this.service.login(dto));
	}
}
