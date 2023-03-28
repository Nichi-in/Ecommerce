package com.nichi.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nichi.ecommerce.dto.ProductDto;
import com.nichi.ecommerce.model.User;
import com.nichi.ecommerce.model.WishList;
import com.nichi.ecommerce.repository.WishListRepository;

@Service
public class WishListService {

	@Autowired
	WishListRepository wishListRepository;

	@Autowired
	ProductService productService;
	
	public void createWishlist(WishList wishList) {
        wishListRepository.save(wishList);
    }

	public List<ProductDto> getWishListForUser(User user) {
		final List<WishList> wishLists	=wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
		List<ProductDto> productDtos = new ArrayList<>();
		
		for(WishList wishList: wishLists) {
			productDtos.add(productService.getProductDto(wishList.getProduct()));
		}
		return productDtos;
	}
}
