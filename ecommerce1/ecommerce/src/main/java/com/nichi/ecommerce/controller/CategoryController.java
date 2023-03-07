package com.nichi.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nichi.ecommerce.model.Category;
import com.nichi.ecommerce.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	private CategoryService categoryService;
	
	
	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@PostMapping("/create")
	public String createCategory(@RequestBody Category category) {
		categoryService.createCategory(category);
		return "success";
	}
	
	@GetMapping("/list")
	public List<Category> listCategory() {
		return categoryService.listcategory();
		 
	}
}
