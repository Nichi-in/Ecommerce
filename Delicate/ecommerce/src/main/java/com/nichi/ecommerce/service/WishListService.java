package com.nichi.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nichi.ecommerce.model.WishList;
import com.nichi.ecommerce.repository.WishListRepository;

@Service
public class WishListService {

	@Autowired
	WishListRepository wishListRepository;

	public void createWishlist(WishList wishList) {
		wishListRepository.save(wishList);
		
	}
}
