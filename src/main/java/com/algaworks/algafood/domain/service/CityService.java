package com.algaworks.algafood.domain.service;

import java.util.List;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.CityRepository;
import com.algaworks.algafood.domain.repository.StateRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CityService {
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private StateRepository stateRepository;

	public List<City> list() {
		return cityRepository.findAll();
	}

	public City findById(Long id) {
		City city = cityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("City not found! Id: %d", id)));

		return city;
	}

	public City create(City city) {
		Long stateId = city.getState().getId();
		State state = stateRepository.findById(stateId).orElseThrow(() -> new EntityNotFoundException(String.format("State not found! Id: %d", stateId)));
		city.setState(state);

		return cityRepository.save(city);
	}

	public City update(City city, Long id) {
		City currentCity = cityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("City not found! Id: %d", id)));

		Long stateId = city.getState().getId();
		State state = stateRepository.findById(stateId).orElseThrow(() -> new RuntimeException(String.format("State not found! Id: %d", stateId)));

		BeanUtils.copyProperties(city, currentCity, "id");
		currentCity.setState(state);

		return cityRepository.save(currentCity);
	}

	public void remove(Long id) {
		try {
			cityRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(String.format("City (%d) Not found", id));
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format("City (%d) in use and cannot be removed", id));
		}
	}
}