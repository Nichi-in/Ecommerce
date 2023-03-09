package com.nichi.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nichi.ecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
