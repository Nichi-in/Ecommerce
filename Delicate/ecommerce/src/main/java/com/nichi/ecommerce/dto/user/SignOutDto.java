package com.nichi.ecommerce.dto.user;

public class SignOutDto {
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public SignOutDto(String email) {
		super();
		this.email = email;
	}

	public SignOutDto() {
		super();
	}
	
	
}
