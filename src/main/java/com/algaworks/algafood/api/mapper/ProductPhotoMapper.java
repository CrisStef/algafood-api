package com.algaworks.algafood.api.mapper;

import com.algaworks.algafood.api.model.response.ProductPhotoResponse;
import com.algaworks.algafood.domain.model.ProductPhoto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductPhotoMapper {
	@Autowired
	private ModelMapper modelMapper;

	public ProductPhotoResponse productPhotoForProductPhotoResponse(ProductPhoto photoRequest) {
		return modelMapper.map(photoRequest, ProductPhotoResponse.class);
	}
}