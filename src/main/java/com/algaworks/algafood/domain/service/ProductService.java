package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.api.mapper.ProductMapper;
import com.algaworks.algafood.api.model.request.ProductRequest;
import com.algaworks.algafood.api.model.response.ProductResponse;
import com.algaworks.algafood.domain.exception.ProductNotFoundException;
import com.algaworks.algafood.domain.model.Product;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private ProductMapper productMapper;

	public Product getRestaurantProduct(Long restaurantId, Long productId) {
		Restaurant restaurant = restaurantService.findById(restaurantId);
		Product product = this.findById(productId);

		if (!restaurant.getProducts().contains(product)) {
			throw new ProductNotFoundException(productId, restaurantId);
		}

		return product;
	}

	public Product findById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

		return product;
	}

	public ProductResponse create(Long restaurantId, ProductRequest productRequest) {
		Product product = productMapper.productRequestForProduct(productRequest);

		return productMapper.productForProductResponse(this.save(restaurantId, product));
	}

	public ProductResponse alter(Long restaurantId, Long productId, ProductRequest productRequest) {
		Product product = productMapper.productRequestForProduct(productRequest);

		return productMapper.productForProductResponse(this.update(restaurantId, productId, product));
	}

	@Transactional
	public Product save(Long restaurantId, Product product) {
		Restaurant restaurant = restaurantService.findById(restaurantId);

		product.setRestaurant(restaurant);

		return productRepository.save(product);
	}

	@Transactional
	private Product update(Long restaurantId, Long productId, Product product) {
		Restaurant restaurant = restaurantService.findById(restaurantId);
		Product currentProduct = this.findById(productId);

		if (!restaurant.getProducts().contains(currentProduct)) {
			throw new ProductNotFoundException(productId, restaurantId);
		}

		productMapper.copyProductForCurrentProduct(product, currentProduct);

		return productRepository.save(currentProduct);
	}
}