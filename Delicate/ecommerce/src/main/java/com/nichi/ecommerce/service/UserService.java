package com.nichi.ecommerce.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nichi.ecommerce.dto.ResponseDto;
import com.nichi.ecommerce.dto.user.SignInDto;
import com.nichi.ecommerce.dto.user.SignOutDto;
import com.nichi.ecommerce.dto.user.SigninResponseDto;
import com.nichi.ecommerce.dto.user.SignupDto;
import com.nichi.ecommerce.exceptions.AuthenticationFailException;
import com.nichi.ecommerce.exceptions.CustomException;
import com.nichi.ecommerce.model.AuthenticationToken;
import com.nichi.ecommerce.model.User;
import com.nichi.ecommerce.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.xml.bind.DatatypeConverter;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	AuthenticationService authenticationService;
	
	@Transactional
	public ResponseDto signUp(SignupDto signupDto) {
		
		//check if user already exist
		if(Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
			//we have user
			throw new CustomException("User Already present");
		}
		
		//hash the password
		String encryptedpassword = signupDto.getPassword();
		try {
			encryptedpassword = hashPassword(signupDto.getPassword());
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		
		}
		
		//Save the user
		User user = new User(signupDto.getFirstName(),
								signupDto.getLastName(),
								signupDto.getEmail(),
								encryptedpassword);
		userRepository.save(user);
		
		//create the token
		final AuthenticationToken authenticationToken =  new AuthenticationToken(user);
		authenticationService.saveConfirmationToken(authenticationToken);
		
		ResponseDto responseDto = new ResponseDto("Success", "User Created Successfully");
		return responseDto;
	}

	String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return myHash;
    }

	public SigninResponseDto signIn(SignInDto signInDto) {
		//find user by email
		User user = userRepository.findByEmail(signInDto.getEmail());
		if(Objects.isNull(user)) {
			throw new AuthenticationFailException("User is not valid");
		}
		
		//hash the password
		try {
			if(!user.getPassword().equals(hashPassword(signInDto.getPassword()))) {
				throw new AuthenticationFailException("Wrong password");

			}
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		}
			
		//compare the password in db
		
		//if password is matched
		AuthenticationToken token = authenticationService.getToken(user);
		
		//retrieve the token 
		if(Objects.isNull(token)) {
			throw new CustomException("Token is not present");
		}
		
		//return response
		return new SigninResponseDto("Success",token.getToken());
	}

	
	
	public String signOut(SignOutDto signOutDto) {
		//find user by email
		User user = userRepository.findByEmail(signOutDto.getEmail());
		if(Objects.isNull(user)) {
			throw new AuthenticationFailException("User is not valid");
		}
		
		//hash the password
//		try {
//			if(!user.getPassword().equals(hashPassword(SignOutDto.getPassword()))) {
//				throw new AuthenticationFailException("Wrong password");
//
//			}
//		} catch (NoSuchAlgorithmException e) {
//			
//			e.printStackTrace();
//		}
//			
		//compare the password in db
		
//		//if password is matched
//		AuthenticationToken token = authenticationService.getToken(user);
//		
//		//retrieve the token 
//		if(Objects.isNull(token)) {
//			throw new CustomException("Token is not present");
//		}
		
		//return response
		return "User Logged Out Successfully";
	}
}
