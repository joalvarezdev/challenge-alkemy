package com.joalvarez.challengealkemy.controller;

import com.joalvarez.challengealkemy.data.dto.MovieDTO;
import com.joalvarez.challengealkemy.data.dto.MovieRequestDTO;
import com.joalvarez.challengealkemy.service.interfaces.IMovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("movies")
public class MovieController {

	private final IMovieService service;

	public MovieController(IMovieService service) {
		this.service = service;
	}

	@PostMapping
	@Operation(summary = "Create a new movie")
	@ApiResponse(responseCode = "201", description = "Successful operation",
		content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class)))
	public ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieRequestDTO dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dto));
	}

	@GetMapping
	@Operation(summary = "Get all paginated movies with optional sorting and filtering.")
	@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(
		mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class)))
	public ResponseEntity<Page<MovieDTO>> findAllPaginated(
		@Parameter(hidden = true) Pageable pageable,
		@Parameter(name = "page", description = "Page number", schema = @Schema(type = "integer"))
		@RequestParam(value = "page", required = false) Integer page,
		@Parameter(name = "size", description = "Page size", schema = @Schema(type = "integer"))
		@RequestParam(value = "size", required = false) Integer size
	) {
		return ResponseEntity.ok(this.service.findAll(pageable));
	}

	@GetMapping("{id}")
	@Operation(summary = "Get a movie")
	@ApiResponse(responseCode = "200", description = "Successful operation",
		content = @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDTO.class)))
	public ResponseEntity<MovieDTO> findById(@PathVariable Long id){
		return ResponseEntity.ok(this.service.findById(id));
	}

	@PostMapping("{id}/characters/{idCharacter}")
	@Operation(summary = "Add a character to a movie")
	public ResponseEntity<MovieDTO> addCharacter(@PathVariable Long id, @PathVariable Long idCharacter) {
		return ResponseEntity.ok(this.service.addCharacter(id, idCharacter));
	}

	@DeleteMapping("{id}/characters/{idCharacter}")
	@Operation(summary = "Delete a character from a movie")
	public ResponseEntity<MovieDTO> deleteCharacter(@PathVariable Long id, @PathVariable Long idCharacter) {
		return ResponseEntity.ok(this.service.deleteCharacter(id, idCharacter));
	}
}
