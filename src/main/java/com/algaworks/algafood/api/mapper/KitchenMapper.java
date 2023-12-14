package com.algaworks.algafood.api.mapper;

import java.util.List;

import com.algaworks.algafood.api.model.request.KitchenRequest;
import com.algaworks.algafood.api.model.response.KitchenResponse;
import com.algaworks.algafood.domain.model.Kitchen;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KitchenMapper {
	@Autowired
	private ModelMapper modelMapper;

	public Kitchen kitchenRequestForKitchen(KitchenRequest kitchenRequest) {
		return modelMapper.map(kitchenRequest, Kitchen.class);
	}

	public void copyKitchenForCurrentKitchen(Kitchen kitchen, Kitchen currentKitchen) {
		modelMapper.map(kitchen, currentKitchen);
	}

	public KitchenResponse kitchenForKitchenResponse(Kitchen kitchen) {
		return modelMapper.map(kitchen, KitchenResponse.class);
	}

	public List<KitchenResponse> kitchenListForKitchenListResponse(List<Kitchen> kitchen) {
		return modelMapper.map(kitchen, new TypeToken<List<KitchenResponse>>(){}.getType());
	}
}