package com.devsuperior.movieflix.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.movieflix.dtos.ExtendedMovieDTO;
import com.devsuperior.movieflix.dtos.MovieDTO;
import com.devsuperior.movieflix.dtos.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

	@Autowired
	private MovieService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ExtendedMovieDTO> findById(@PathVariable Long id) {
		ExtendedMovieDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@GetMapping(value = "/{id}/reviews")
	public ResponseEntity<List<ReviewDTO>> movieReviews(@PathVariable Long id) {
		List<ReviewDTO> reviews = service.findReviews(id);
		return ResponseEntity.ok().body(reviews);
	}
	
	@GetMapping
	public ResponseEntity<Page<MovieDTO>> findByGenre(Pageable pageInfo, 
			@RequestParam(name = "genreId", defaultValue = "0") Long genreId) {
		Page<MovieDTO> page = service.findByGenre(pageInfo, genreId);
		return ResponseEntity.ok().body(page);
	}
}
