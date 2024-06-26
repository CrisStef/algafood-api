package com.algaworks.algafood.api.mapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.request.SaleOrderRequest;
import com.algaworks.algafood.api.model.response.SaleOrderListResponse;
import com.algaworks.algafood.api.model.response.SaleOrderResponse;
import com.algaworks.algafood.domain.model.SaleOrder;

@Component
public class SaleOrderMapper {
	@Autowired
	private ModelMapper modelMapper;

	public void copySaleOrderForCurrentSaleOrder(SaleOrder saleOrder, SaleOrder currentSaleOrder) {
		modelMapper.map(saleOrder, currentSaleOrder);
	}

	public SaleOrderResponse saleOrderForSaleOrderResponse(SaleOrder saleOrder) {
		return modelMapper.map(saleOrder, SaleOrderResponse.class);
	}

	public List<SaleOrderListResponse> saleOrderListForSaleOrderListResponse(List<SaleOrder> saleOrder) {
		return modelMapper.map(saleOrder, new TypeToken<List<SaleOrderListResponse>>(){}.getType());
	}

	public SaleOrder saleOrderRequestForSaleOrder(SaleOrderRequest saleOrderRequest) {
		return modelMapper.map(saleOrderRequest, SaleOrder.class);
	}
}