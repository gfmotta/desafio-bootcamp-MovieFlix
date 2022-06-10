package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dtos.ExtendedMovieDTO;
import com.devsuperior.movieflix.dtos.MovieDTO;
import com.devsuperior.movieflix.dtos.ReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class MovieService {

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private ModelMapper mapper;

	@Transactional
	public ExtendedMovieDTO findById(Long id) {
		Optional<Movie> entity = movieRepository.findById(id);
		Movie movie = entity.orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado!"));
		return mapper.map(movie, ExtendedMovieDTO.class);
	}

	@Transactional
	public List<ReviewDTO> findReviews(Long id) {
		try {
			Movie movie = movieRepository.getOne(id);
			List<Review> lista = movie.getReviews();
			return lista.stream().map(x -> mapper.map(x, ReviewDTO.class)).collect(Collectors.toList());
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Filme não encontrado!");
		}
	}

	public Page<MovieDTO> findByGenre(Pageable pageInfo, Long genreId) {
		Genre genre = (genreId == 0) ? null : genreRepository.getOne(genreId);
		Page<Movie> page = movieRepository.findByGenre(pageInfo, genre);
		return page.map(x -> mapper.map(x, MovieDTO.class));
	}
}
