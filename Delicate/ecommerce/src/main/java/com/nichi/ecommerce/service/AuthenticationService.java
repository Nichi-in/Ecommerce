package com.nichi.ecommerce.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nichi.ecommerce.exceptions.AuthenticationFailException;
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

	public User getUser(String token) {
		AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
		if(Objects.isNull(authenticationToken)) {
			return null;
		}
		return authenticationToken.getUser();
	}
	
	public void authenticate(String token) throws AuthenticationFailException{
		//checking if the token is null or not
		if(Objects.nonNull(token)) {
			throw new AuthenticationFailException("Token not present");
		}
		
		//check user present or not
		if(Objects.isNull(getUser(token))) {
			throw new AuthenticationFailException("Token not valid");
		}
	}
}
