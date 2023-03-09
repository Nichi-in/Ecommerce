package com.nichi.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nichi.ecommerce.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	User findByEmail(String email);
}
