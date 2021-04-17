package com.algaworks.algafood.domain.service;

import java.util.List;

import com.algaworks.algafood.domain.exception.CityNotFoundException;
import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.model.City;
import com.algaworks.algafood.domain.model.State;
import com.algaworks.algafood.domain.repository.CityRepository;

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
	private StateService stateService;

	private static final String MSG_CITY_IN_USE = "City (%d) in use and cannot be removed";

	public List<City> list() {
		return cityRepository.findAll();
	}

	public City findById(Long id) {
		City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));

		return city;
	}

	public City create(City city) {
		Long stateId = city.getState().getId();
		State state = stateService.findById(stateId);
		city.setState(state);

		return cityRepository.save(city);
	}

	public City update(City city, Long id) {
		City currentCity = findById(id);

		Long stateId = city.getState().getId();
		State state = stateService.findById(stateId);

		BeanUtils.copyProperties(city, currentCity, "id");
		currentCity.setState(state);

		return cityRepository.save(currentCity);
	}

	public void remove(Long id) {
		try {
			cityRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new CityNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_CITY_IN_USE, id));
		}
	}
}