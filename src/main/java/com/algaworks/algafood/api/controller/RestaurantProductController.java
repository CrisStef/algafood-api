package com.algaworks.algafood.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.model.request.ProductPhotoRequest;
import com.algaworks.algafood.api.model.response.ProductPhotoResponse;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Product;
import com.algaworks.algafood.domain.model.dto.ProductPhotoData;
import com.algaworks.algafood.domain.service.ProductPhotoCatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	private ProductPhotoCatalogService productPhotoCatalogService;

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

	@PutMapping(value = "/{product_id}/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ProductPhotoResponse createRestaurantProductPhoto(
			@PathVariable("restaurant_id") Long restaurantId,
			@PathVariable("product_id") Long productId,
			@Valid ProductPhotoRequest photoRequest) throws IOException {
		return productPhotoCatalogService.createProductPhoto(ProductPhotoData.builder()
						.photoRequest(photoRequest)
						.productId(productId)
						.restaurantId(restaurantId)
						.build());
	}

	@GetMapping(value = "/{product_id}/photo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductPhotoResponse createRestaurantProductPhoto(
			@PathVariable("restaurant_id") Long restaurantId,
			@PathVariable("product_id") Long productId) throws IOException {
		return productPhotoCatalogService.findProductPhoto(restaurantId, productId);
	}

	@GetMapping(value = "/{product_id}/photo", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<InputStreamResource> createRestaurantProductPhotoFile(
			@PathVariable("restaurant_id") Long restaurantId,
			@PathVariable("product_id") Long productId) throws IOException {
		try {
			InputStream inputStream = productPhotoCatalogService.getProductPhotoFile(restaurantId, productId);

			return ResponseEntity.ok()
					.contentType(MediaType.IMAGE_PNG)
					.body(new InputStreamResource(inputStream));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}