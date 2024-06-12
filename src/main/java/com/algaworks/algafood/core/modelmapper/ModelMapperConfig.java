package com.algaworks.algafood.core.modelmapper;

import com.algaworks.algafood.api.model.request.SaleOrderItemRequest;
import com.algaworks.algafood.api.model.response.AddressResponse;
import com.algaworks.algafood.domain.model.Address;
import com.algaworks.algafood.domain.model.SaleOrderItem;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setSkipNullEnabled(true);

		var addressToModelTypeMap = modelMapper.createTypeMap(Address.class, AddressResponse.class);

		addressToModelTypeMap.<String>addMapping(
				addressOrigin -> addressOrigin.getCity().getState().getName(),
				(addressDestiny, value) -> addressDestiny.getCity().setState(value)
		);

		modelMapper.createTypeMap(SaleOrderItemRequest.class, SaleOrderItem.class)
    		.addMappings(mapper -> mapper.skip(SaleOrderItem::setId));

        return modelMapper;
	}
}