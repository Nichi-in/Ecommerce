package com.nichi.ecommerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nichi.ecommerce.dto.ProductDto;
import com.nichi.ecommerce.model.Category;
import com.nichi.ecommerce.model.Product;
import com.nichi.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public void createProduct(ProductDto productDto, Category category) {
		Product product = new Product();
		product.setDescription(productDto.getDescription());
		product.setImageUrl(productDto.getImageUrl());
		product.setName(productDto.getName());
		product.setCategory(category);
		product.setPrice(productDto.getPrice());
		productRepository.save(product);
	}

	//Convert product into productDto
	public ProductDto getProductDto(Product product) {
		ProductDto productDto=new ProductDto();
		productDto.setDescription(product.getDescription());
		productDto.setImageUrl(product.getImageUrl());
		productDto.setName(product.getName());
		productDto.setCategoryId(product.getCategory().getId());
		productDto.setPrice(product.getPrice());
		productDto.setId(product.getId());
		return productDto;
	}
	
	
	public List<ProductDto> getAllProducts() {
		List<Product> allProducts =	productRepository.findAll();
	
		List<ProductDto> productDtos = new ArrayList<>();
		for(Product product: allProducts) {
			productDtos.add(getProductDto(product));
		}
		return productDtos;
	}

	public void updateProduct(ProductDto productDto, Integer productId) throws Exception {
		Optional<Product> optionalProduct= productRepository.findById(productId);
		// throw an exception if product does not exist
		if(!optionalProduct.isPresent()) {
			throw new Exception("Product not present");
		}
		Product product= optionalProduct.get();
		product.setDescription(productDto.getDescription());
		product.setImageUrl(productDto.getImageUrl());
		product.setName(productDto.getName());
		product.setPrice(productDto.getPrice());
		productRepository.save(product);
	}
}
