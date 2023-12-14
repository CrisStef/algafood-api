package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.api.mapper.RestaurantMapper;
import com.algaworks.algafood.api.model.request.RestaurantRequest;
import com.algaworks.algafood.api.model.response.RestaurantResponse;
import com.algaworks.algafood.domain.exception.RestaurantNotFoundException;
import com.algaworks.algafood.domain.exception.ValidatorException;
import com.algaworks.algafood.domain.model.Kitchen;
import com.algaworks.algafood.domain.model.Restaurant;
import com.algaworks.algafood.domain.repository.RestaurantRepository;
import com.algaworks.algafood.domain.util.MergeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private KitchenService kitchenService;

	@Autowired
	private SmartValidator validator;

	@Autowired 
	private RestaurantMapper restaurantMapper;

	public RestaurantResponse create(RestaurantRequest restaurantRequest) {
		Restaurant restaurant = restaurantMapper.restaurantRequestForRestaurant(restaurantRequest);

		return restaurantMapper.restaurantForRestaurantResponse(this.save(restaurant));
	}

	public RestaurantResponse getById(Long id) {
		return restaurantMapper.restaurantForRestaurantResponse(this.findById(id));
	}

	public RestaurantResponse alter(Map<String, Object> restaurantMap, Long id, HttpServletRequest request) {
        RestaurantRequest restaurantRequest = new RestaurantRequest();
        MergeMapper.merge(restaurantMap, restaurantRequest, request);

        validate(restaurantRequest, "restaurantRequest");

		return restaurantMapper.restaurantForRestaurantResponse(this.update(restaurantRequest, id, request));
	}

	public List<RestaurantResponse> findAll() {
		return restaurantMapper.restaurantListForRestaurantListResponse(this.listAll());
	}

	public List<RestaurantResponse> getByName(String name, Long kitchenId) {
		return restaurantMapper.restaurantListForRestaurantListResponse(this.findByName(name, kitchenId));
	}

	@Transactional
	private Restaurant save(Restaurant restaurant) {
		Long kitchenId = restaurant.getKitchen().getId();
		Kitchen kitchen = kitchenService.findById(kitchenId);

		restaurant.setKitchen(kitchen);

		return restaurantRepository.save(restaurant);
	}

	@Transactional
	public void activateRestaurant(Long id) {
		Restaurant restaurant = this.findById(id);

		restaurant.activate();
	}

	@Transactional
	public void disableRestaurant(Long id) {
		Restaurant restaurant = this.findById(id);

		restaurant.disable();
	}

	private Restaurant findById(Long id) {
		return restaurantRepository.findById(id).orElseThrow(() -> new RestaurantNotFoundException(id));
	}

	private List<Restaurant> listAll() {
		return restaurantRepository.findAll();
	}

	@Transactional
	private Restaurant update(RestaurantRequest restaurantRequest, Long id, HttpServletRequest request) {
		Restaurant currentRestaurant = findById(id);

        restaurantMapper.copyRestaurantRequestForRestaurant(restaurantRequest, currentRestaurant);

		Long kitchenId = currentRestaurant.getKitchen().getId();

		Kitchen kitchen = kitchenService.findById(kitchenId);

		currentRestaurant.setKitchen(kitchen);

		return restaurantRepository.save(currentRestaurant);
	}

	public List<Restaurant> findByFraightRate(BigDecimal initialFraightRate, BigDecimal finalFraightRate) {
		return restaurantRepository.findByFreightRateBetween(initialFraightRate, finalFraightRate);
	}

	public List<Restaurant> findByName(String name, Long kitchenId) {
		return restaurantRepository.findByNameAndKitchenId(name, kitchenId);
	}

	public List<Restaurant> find(String name, BigDecimal initialFraightRate, BigDecimal finalFraightRate) {
		return restaurantRepository.find(name, initialFraightRate, finalFraightRate);
	}

	public List<Restaurant> findRestaurantFilter(String name) {
		return restaurantRepository.findRestaurantFilter(name);
	}

	public Restaurant findRestaurantFirst() {
		return restaurantRepository.findFirst().get();
	}

	private void validate(RestaurantRequest restaurant, String objectName) {
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurant, objectName);
		validator.validate(restaurant, bindingResult);
		
		if (bindingResult.hasErrors()) {
			throw new ValidatorException(bindingResult);
		}
	}
}