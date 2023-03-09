package com.nichi.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nichi.ecommerce.common.ApiResponse;
import com.nichi.ecommerce.dto.ResponseDto;
import com.nichi.ecommerce.dto.user.SignInDto;
import com.nichi.ecommerce.dto.user.SigninResponseDto;
import com.nichi.ecommerce.dto.user.SignupDto;
import com.nichi.ecommerce.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	//Two apis signup and sign in
	@PostMapping("/signup")
	public ResponseDto signUp(@RequestBody SignupDto signupDto) {
		return userService.signUp(signupDto);
	}
	
	//signin
	@PostMapping("/signin")
	public SigninResponseDto signin(@RequestBody SignInDto signInDto) {
		return userService.signIn(signInDto);
	}
}
