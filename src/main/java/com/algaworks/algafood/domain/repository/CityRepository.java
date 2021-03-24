package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.City;

public interface CityRepository {
	List<City> listAll();

	City findById(Long id);

	City save(City city);

	void remove(City city);
}