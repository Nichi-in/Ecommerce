package com.nichi.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nichi.ecommerce.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
