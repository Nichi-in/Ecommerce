package com.nichi.ecommerce.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.nichi.ecommerce.dto.cart.AddToCartDto;
import com.nichi.ecommerce.dto.cart.CartDto;
import com.nichi.ecommerce.dto.cart.CartItemDto;
import com.nichi.ecommerce.exceptions.CustomException;
import com.nichi.ecommerce.model.Cart;
import com.nichi.ecommerce.model.Product;
import com.nichi.ecommerce.model.User;
import com.nichi.ecommerce.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	 private ProductService productService;
	
	@Autowired
	private CartRepository cartRepository;
	
	public void addToCart(AddToCartDto addToCartDto, User user) {
	
		//validate if the product id is valid
		Product product = productService.findById(addToCartDto.getProductId());
		//save the cart
		
		Cart cart = new Cart();
		cart.setProduct(product);
		cart.setUser(user);
		cart.setQuantity(addToCartDto.getQuantity());
		cart.setCreatedDate(new Date());
		
		cartRepository.save(cart);
	}

	public CartDto listCartItems(User user) {
		 List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
		
		 //change cart to cartdto
		List<CartItemDto> cartItems = new ArrayList<>();
		double totalCost = 0;
		for(Cart cart:cartList) {
			CartItemDto cartItemDto = new CartItemDto(cart);
			totalCost += cartItemDto.getQuantity()*cart.getProduct().getPrice();
			cartItems.add(cartItemDto);
		}
		CartDto cartDto = new CartDto();
		cartDto.setTotalCost(totalCost);
		cartDto.setCartItems(cartItems);
		
		return cartDto;
	}

	public void deleteCartItem(Integer id, User user) {
		//Check the item belongs to user
		
		Optional<Cart> optionalCart = cartRepository.findById(id);
		if(optionalCart.isEmpty()) {
			throw new CustomException("Cart item id is invalid" +id);
		}
		Cart cart = optionalCart.get();
		
		if(cart.getUser() != user) {
			throw new CustomException("Cart item doenot belong to user: "+id);
		}
		
		cartRepository.deleteById(id);
	}

}
