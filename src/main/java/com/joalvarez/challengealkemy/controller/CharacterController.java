package com.joalvarez.challengealkemy.controller;

import com.joalvarez.challengealkemy.data.dto.CharacterDTO;
import com.joalvarez.challengealkemy.service.interfaces.ICharacterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/characters")
@SecurityRequirement(name = "Bearer Authentication")
public class CharacterController {

	private final ICharacterService service;

	public CharacterController(ICharacterService service) {
		this.service = service;
	}

	@GetMapping
	@Operation(summary = "Get all paginated characters with optional sorting and filtering.")
	@ApiResponse(responseCode = "200", description = "Successful operation",
		content = @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterDTO.class)))
	public ResponseEntity<Page<CharacterDTO>> findAll(
		@Parameter(hidden = true) Pageable pageable,
		@Parameter(name = "page", description = "Page number", schema = @Schema(type = "integer"))
		@RequestParam(value = "page", required = false) Integer page,
		@Parameter(name = "size", description = "Page size", schema = @Schema(type = "integer"))
		@RequestParam(value = "size", required = false) Integer size,
		@RequestParam(value = "field", required = false) String field,
		@Parameter(name = "value", description = "Value of field selected in param field",
			schema = @Schema(type = "string")
		)
		@RequestParam(value = "value", required = false) String value
	) {
		return ResponseEntity.ok(this.service.findAll(pageable));
	}

	@GetMapping("{id}")
	@Operation(summary = "Get a character by id")
	@ApiResponse(responseCode = "200", description = "Successful operation",
		content = @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterDTO.class)))
	public ResponseEntity<CharacterDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok(this.service.findById(id));
	}

	@PostMapping
	@Operation(summary = "Create a new character")
	@ApiResponse(responseCode = "201", description = "Successful operation",
		content = @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterDTO.class)))
	public ResponseEntity<CharacterDTO> create(@Valid @RequestBody CharacterDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.create(dto));
	}

	@PutMapping("{id}")
	@Operation(summary = "Update a character")
	@ApiResponse(responseCode = "200", description = "Successful operation",
		content = @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterDTO.class)))
	public ResponseEntity<CharacterDTO> update(@RequestBody CharacterDTO dto, @PathVariable Long id) {
		return ResponseEntity.ok(this.service.update(dto, id));
	}
}
