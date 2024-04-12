package com.jhonsc.springbootapp.item.model.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jhonsc.springbootapp.item.client.ProductClientRest;
import com.jhonsc.springbootapp.item.model.Item;
import com.jhonsc.springbootapp.item.model.Product;
import com.jhonsc.springbootapp.item.model.service.ItemService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemServiceFeignImpl implements ItemService {

	private final ProductClientRest productClientRest;

	@Override
	public List<Item> findAll() {
		List<Product> productList = productClientRest.findAllProducts();
		return productList.stream().map(product -> new Item(product, 1L)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Long quantity) {
		Map<String, String> pathVariable = new HashMap<String, String>();
		pathVariable.put("id", id.toString());
		Product product = productClientRest.detail(id);

		return new Item(product, quantity);
	}

}
