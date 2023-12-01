package com.algaworks.algafood.api.mapper;

import java.util.List;

import com.algaworks.algafood.api.model.request.CityRequest;
import com.algaworks.algafood.api.model.response.CityResponse;
import com.algaworks.algafood.domain.model.City;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {
	@Autowired
	private ModelMapper modelMapper;

	public City cityRequestForCity(CityRequest cityRequest) {
		return modelMapper.map(cityRequest, City.class);
	}

	public void copyCityForCurrentCity(City city, City currentCity) {
		modelMapper.map(city, currentCity);
	}

	public CityResponse cityForCityResponse(City city) {
		return modelMapper.map(city, CityResponse.class);
	}

	public List<CityResponse> cityListForCityListResponse(List<City> city) {
		return modelMapper.map(city, new TypeToken<List<CityResponse>>(){}.getType());
	}
}