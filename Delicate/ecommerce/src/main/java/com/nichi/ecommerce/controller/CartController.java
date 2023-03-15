package com.nichi.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nichi.ecommerce.common.ApiResponse;
import com.nichi.ecommerce.dto.cart.AddToCartDto;
import com.nichi.ecommerce.dto.cart.CartDto;
import com.nichi.ecommerce.model.Cart;
import com.nichi.ecommerce.model.User;
import com.nichi.ecommerce.service.AuthenticationService;
import com.nichi.ecommerce.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	//post cart api
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
										@RequestParam("token") String token) {
		//Authenticate the token
		authenticationService.authenticate(token);
		
		//find the user
		User user = authenticationService.getUser(token); 
		cartService.addToCart(addToCartDto, user);
	
		return new ResponseEntity<>(new ApiResponse(true, "Product Added to cart"),HttpStatus.CREATED);
	}
	
	//get all cart item for user
	@GetMapping("/")
	public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token){
		//Authenticate the token
		authenticationService.authenticate(token);
				
		//find the user
		User user = authenticationService.getUser(token); 
		
		//get cart items
		CartDto cartDto = cartService.listCartItems(user);
		
		return new ResponseEntity<> (cartDto, HttpStatus.OK);
	}
	
	//delete a cart item for a user
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("id") Integer id,
														@RequestParam("token") String token){
		
		//Authenticate the token
				authenticationService.authenticate(token);
						
				//find the user
				User user = authenticationService.getUser(token); 
			
				cartService.deleteCartItem(id,user);
				return new ResponseEntity<>(new ApiResponse(true, "Item has been deleted"),HttpStatus.OK);

	}
}
