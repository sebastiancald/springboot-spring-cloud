package com.jhonsc.springbootapp.item.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jhonsc.springbootapp.item.model.Product;

@FeignClient(name = "product")
public interface ProductClientRest {

	@GetMapping("/list")
	List<Product> findAllProducts();
	
	@GetMapping("/product/{id}")
	Product detail(@PathVariable Long id);
}
