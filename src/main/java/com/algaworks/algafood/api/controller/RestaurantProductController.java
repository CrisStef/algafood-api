package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.domain.model.Product;
import com.algaworks.algafood.domain.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.algaworks.algafood.api.mapper.ProductMapper;
import com.algaworks.algafood.api.model.request.ProductRequest;
import com.algaworks.algafood.api.model.response.ProductResponse;
import com.algaworks.algafood.domain.service.ProductService;
import com.algaworks.algafood.domain.service.RestaurantService;


@RestController
@RequestMapping("/restaurants/{restaurant_id}/products")
public class RestaurantProductController {
	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductMapper productMapper;

	@GetMapping
	public List<ProductResponse> findAll(@PathVariable("restaurant_id") Long restaurantId,
			@RequestParam(required = false) boolean inative) {
		List<Product> productList = productService.listAllRestaurantProduct(restaurantId, inative);

		return productMapper.productListForProductListResponse(productList);
	}

	@GetMapping("/{product_id}")
	public ProductResponse getRestaurantProduct(@PathVariable("restaurant_id") Long restaurantId, @PathVariable("product_id") Long productId) {
		return productMapper.productForProductResponse(productService.getRestaurantProduct(restaurantId, productId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductResponse create(@PathVariable("restaurant_id") Long restaurantId, @RequestBody @Valid ProductRequest product) {
		return productService.create(restaurantId, product);
	}

	@PutMapping("/{product_id}")
	public ProductResponse alter(@PathVariable("restaurant_id") Long restaurantId, @PathVariable("product_id") Long productId, @RequestBody @Valid ProductRequest product) {
		return productService.alter(restaurantId, productId, product);
	}
}