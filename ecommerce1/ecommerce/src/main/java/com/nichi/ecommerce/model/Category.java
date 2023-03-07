package com.nichi.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name="category")
@Setter
@Getter
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="category_name")
	private @NotBlank String categoryName;
	
	@Column(name="description")
	private @NotBlank String description;
	
	@Column(name="image_url")
	private @NotBlank String imageUrl;
}
