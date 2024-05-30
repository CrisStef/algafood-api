package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Product getRestaurantProduct(Long restaurantId, Long productId) {
		Restaurant restaurant = restaurantService.findById(restaurantId);
		Product product = findById(productId);

		if (!restaurant.getProducts().contains(product)) {
			throw new ProductNotFoundException(
				String.format("There is no product registration with code %s for the restaurant with code %s", productId, restaurantId));
		}

		return product;
	}

	public Product findById(Long id) {
		Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

		return product;
	}
}