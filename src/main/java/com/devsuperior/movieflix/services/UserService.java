package com.devsuperior.movieflix.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dtos.UserDTO;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import com.devsuperior.movieflix.services.exceptions.UnauthorizedException;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Transactional(readOnly = true)
	public UserDTO loggedUserProfile() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			User user = repository.findByEmail(username);
			return mapper.map(user, UserDTO.class);
		}
		catch(Exception e) {
			throw new UnauthorizedException("Autorização negada: Você não pode realizar essa operação");
		}
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			return repository.findByEmail(username);
		}
		catch (Exception e) {
			throw new ResourceNotFoundException("Usuario não encontrado");
		}
	}
}
