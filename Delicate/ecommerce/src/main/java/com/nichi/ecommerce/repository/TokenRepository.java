package com.nichi.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nichi.ecommerce.model.AuthenticationToken;
import com.nichi.ecommerce.model.User;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer>{
	AuthenticationToken findByUser(User user);
	
	AuthenticationToken findByToken(String token);
}
