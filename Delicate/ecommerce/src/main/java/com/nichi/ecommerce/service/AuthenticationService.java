package com.nichi.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nichi.ecommerce.model.AuthenticationToken;
import com.nichi.ecommerce.model.User;
import com.nichi.ecommerce.repository.TokenRepository;

@Service
public class AuthenticationService {

	@Autowired
	TokenRepository tokenRepository;
	
	public void saveConfirmationToken(AuthenticationToken authenticationToken) {
		
		tokenRepository.save(authenticationToken);
	}

	public AuthenticationToken getToken(User user) {
		
		return tokenRepository.findByUser(user);
	}

}
