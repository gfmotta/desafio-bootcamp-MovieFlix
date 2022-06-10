package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dtos.GenreDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.repositories.GenreRepository;

@Service
public class GenreService {

	@Autowired
	private GenreRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Transactional
	public List<GenreDTO> findAll() {
		List<Genre> lista = repository.findAll();
		return lista.stream().map(x -> mapper.map(x, GenreDTO.class)).collect(Collectors.toList());
	}

}
