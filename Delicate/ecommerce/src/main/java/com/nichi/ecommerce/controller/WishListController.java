package com.nichi.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nichi.ecommerce.common.ApiResponse;
import com.nichi.ecommerce.model.Product;
import com.nichi.ecommerce.model.User;
import com.nichi.ecommerce.model.WishList;
import com.nichi.ecommerce.service.AuthenticationService;
import com.nichi.ecommerce.service.WishListService;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

	@Autowired
	WishListService wishListService;
	
	@Autowired
	AuthenticationService authenticationService;
	
	//save  product in wishlist item
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product,
													@RequestParam("token") String token){
		//Authenticate the token
		authenticationService.authenticate(token);
		
		//find the user
		User user = authenticationService.getUser(token);
		
		//save the item in wishlist
		WishList wishList = new WishList(user,product);
		wishListService.createWishlist(wishList);
		
		ApiResponse apiResponse = new ApiResponse(true, "Added to wishlist");
		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
	}
	
//	@PostMapping("/add")
//	public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product){
//		//Authenticate the token
//	
//		//save the item in wishlist
//		
//		WishList wishList = new WishList(product);
//		wishListService.createWishlist(wishList);
//		
//		ApiResponse apiResponse = new ApiResponse(true, "Added to wishlist");
//		return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
//	}
	//get all wishlist item for a user
}
