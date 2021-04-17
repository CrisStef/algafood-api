package com.algaworks.algafood.api.controller;

import java.util.List;

import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.service.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cities")
public class CityController {
	@Autowired
	private CityService cityService;

	@GetMapping
	public List<City> list() {
		return cityService.list();
	}

	@GetMapping("/{city_id}")
	public City findById(@PathVariable("city_id") Long id) {
		City city = cityService.findById(id);

		return city;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public City create(@RequestBody City city) {
		city = cityService.create(city);

		return city;
	}

	@PutMapping("/{city_id}")
	public City update(@RequestBody City city, @PathVariable("city_id") Long id) {
		city = cityService.update(city, id);

		return city;
	}

	@DeleteMapping("/{city_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("city_id") Long id) {
		cityService.remove(id);
	}
}