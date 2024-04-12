package com.jhonsc.springbootapp.product.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jhonsc.springbootapp.product.models.entity.ProductEntity;
import com.jhonsc.springbootapp.product.models.service.IProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ProductController {

	private final IProductService productService;

	private final Environment environment;

	@GetMapping("/list")
	public List<ProductEntity> findAllProducts() {
		return productService.findAll().stream().map(product -> {
			product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
			return product;
		}).collect(Collectors.toList());
	}

	@GetMapping("/product/{id}")
	public ProductEntity findProductById(@PathVariable Long id) {
		ProductEntity product = productService.findById(id);
		product.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return product;
	}

}
