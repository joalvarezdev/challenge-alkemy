package com.joalvarez.challengealkemy.controller;

import com.joalvarez.challengealkemy.data.dto.GenreDTO;
import com.joalvarez.challengealkemy.service.interfaces.IGenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
@SecurityRequirement(name = "Bearer Authentication")
public class GenreController {

	private final IGenreService service;

	public GenreController(IGenreService service) {
		this.service = service;
	}

	@GetMapping
	@Operation(summary = "Get all genres")
	@ApiResponse(responseCode = "200", description = "Successful operation",
		content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenreDTO[].class)))
	public ResponseEntity<List<GenreDTO>> findAll() {
		return ResponseEntity.ok(this.service.findAll());
	}

	@PostMapping
	@Operation(summary = "Create a new genre")
	@ApiResponse(responseCode = "201", description = "Successful operation",
		content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenreDTO.class)))
	public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(dto));
	}

	@PutMapping("{id}")
	@Operation(summary = "Update a genre")
	@ApiResponse(responseCode = "200", description = "Successful operation",
		content = @Content(mediaType = "application/json", schema = @Schema(implementation = GenreDTO.class)))
	public ResponseEntity<GenreDTO> update(@RequestBody GenreDTO dto, @PathVariable Long id) {
		return ResponseEntity.ok(this.service.update(dto, id));
	}
}
