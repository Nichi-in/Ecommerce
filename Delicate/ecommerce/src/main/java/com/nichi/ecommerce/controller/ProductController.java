package com.nichi.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nichi.ecommerce.common.ApiResponse;
import com.nichi.ecommerce.dto.ProductDto;
import com.nichi.ecommerce.model.Category;
import com.nichi.ecommerce.model.Product;
import com.nichi.ecommerce.repository.CategoryRepository;
import com.nichi.ecommerce.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto){
		Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
	
		if(!optionalCategory.isPresent()) {
			return new ResponseEntity<>(new ApiResponse(false, "Category does not exist"),HttpStatus.BAD_REQUEST);
		}
		productService.createProduct(productDto, optionalCategory.get());
		return new ResponseEntity<>(new ApiResponse(true, "Product has been added"),HttpStatus.CREATED);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<ProductDto>> getProducts(){
		List<ProductDto> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ApiResponse> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductDto productDto) throws Exception{
		Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
	
		if(!optionalCategory.isPresent()) {
			return new ResponseEntity<>(new ApiResponse(false, "Category does not exist"),HttpStatus.BAD_REQUEST);
		}
		productService.updateProduct(productDto, id);
		return new ResponseEntity<>(new ApiResponse(true, "Product has been Updated"),HttpStatus.OK);
	}
}
