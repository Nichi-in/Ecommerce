package com.nichi.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nichi.ecommerce.model.User;
import com.nichi.ecommerce.model.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer>{

	List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}
