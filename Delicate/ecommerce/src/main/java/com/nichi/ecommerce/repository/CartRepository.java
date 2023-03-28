package com.nichi.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nichi.ecommerce.model.Cart;
import com.nichi.ecommerce.model.User;

public interface CartRepository extends JpaRepository<Cart, Integer>{

	List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
