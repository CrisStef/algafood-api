package com.algaworks.algafood.domain.service;

import java.util.List;

import javax.validation.Valid;

import com.algaworks.algafood.api.mapper.CityMapper;
import com.algaworks.algafood.api.model.request.CityRequest;
import com.algaworks.algafood.api.model.response.CityResponse;
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
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityService {
	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private StateService stateService;

	@Autowired
	private CityMapper cityMapper;

	private static final String MSG_CITY_IN_USE = "City (%d) in use and cannot be removed";

	public CityResponse create(CityRequest cityRequest) {
		City city = cityMapper.cityRequestForCity(cityRequest);

		return cityMapper.cityForCityResponse(this.save(city));
	}

	public List<CityResponse> findAll() {
		return cityMapper.cityListForCityListResponse(this.listAll());
	}

	public CityResponse getById(Long id) {
		return cityMapper.cityForCityResponse(this.findById(id));
	}
	
	public CityResponse alter(@Valid CityRequest cityRequest, Long id) {
		City city = cityMapper.cityRequestForCity(cityRequest);

		return cityMapper.cityForCityResponse(this.update(city, id));
	}

	public List<City> listAll() {
		return cityRepository.findAll();
	}

	public City findById(Long id) {
		City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException(id));

		return city;
	}

	@Transactional
	public City save(City city) {
		Long stateId = city.getState().getId();
		State state = stateService.findById(stateId);
		city.setState(state);

		return cityRepository.save(city);
	}

	@Transactional
	public City update(City city, Long id) {
		City currentCity = findById(id);

		Long stateId = city.getState().getId();
		State state = stateService.findById(stateId);

		cityMapper.copyCityForCurrentCity(city, currentCity);

		currentCity.setState(state);

		return cityRepository.save(currentCity);
	}

	@Transactional
	public void remove(Long id) {
		try {
			cityRepository.deleteById(id);
			cityRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new CityNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(String.format(MSG_CITY_IN_USE, id));
		}
	}
}