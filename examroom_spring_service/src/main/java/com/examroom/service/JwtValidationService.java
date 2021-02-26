package com.examroom.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.examroom.repository.CredentialRepository;

@Service
public class JwtValidationService implements UserDetailsService {

	@Autowired
	private CredentialRepository credRepository;
	
	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		Long mailCount = credRepository.checkMailExistence(emailId);
		if( mailCount > 0) {
			return new User(emailId , "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
					new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("No user found with mailId: "+ emailId);
		}
	}
}
