package com.nichi.ecommerce.controller;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.nichi.ecommerce.common.ApiResponse;
import com.nichi.ecommerce.dto.ProductDto;
import com.nichi.ecommerce.model.Product;
import com.nichi.ecommerce.model.User;
import com.nichi.ecommerce.model.WishList;
import com.nichi.ecommerce.service.AuthenticationService;
import com.nichi.ecommerce.service.WishListService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

        @Autowired
        private WishListService wishListService;

        @Autowired
        private AuthenticationService authenticationService;

//        @GetMapping("/{token}")
//        public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token) {
//                int user_id = authenticationService.getUser(token).getId();
//                List<WishList> body = wishListService.readWishList(user_id);
//                List<ProductDto> products = new ArrayList<ProductDto>();
//                for (WishList wishList : body) {
//                        products.add(ProductService.getDtoFromProduct(wishList.getProduct()));
//                }
//
//                return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
//        }

        @PostMapping("/add")
        public ResponseEntity<ApiResponse> addWishList(@RequestBody Product product, @RequestParam("token") String token) {
            
        	
        	//Authenticate the token
        	authenticationService.authenticate(token);
        	
        	//find the user
            User user = authenticationService.getUser(token);      WishList wishList = new WishList(user, product);
                wishListService.createWishlist(wishList);
                return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Add to wishlist"), HttpStatus.CREATED);

        }

        @GetMapping("/{token}")
        public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token") String token)
        {
        	//Authenticate the token
        	authenticationService.authenticate(token);
        	
        	//find the user
            User user = authenticationService.getUser(token);
       
            List<ProductDto> productDtos = wishListService.getWishListForUser(user);
        
            return new ResponseEntity<>(productDtos, HttpStatus.OK);
        }
        
}
