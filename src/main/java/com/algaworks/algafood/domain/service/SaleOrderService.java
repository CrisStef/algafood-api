package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.api.mapper.SaleOrderMapper;
import com.algaworks.algafood.api.model.response.SaleOrderResponse;
import com.algaworks.algafood.domain.exception.SaleOrderNotFoundException;
import com.algaworks.algafood.domain.model.SaleOrder;
import com.algaworks.algafood.domain.repository.SaleOrderRepository;

@Service
public class SaleOrderService {
	@Autowired
	private SaleOrderRepository saleOrderRepository;

	@Autowired
	private SaleOrderMapper saleOrderMapper;

	public List<SaleOrderResponse> findAll() {
		return saleOrderMapper.saleOrderListForSaleOrderListResponse(this.listAll());
	}
	
	public SaleOrderResponse getById(Long id) {
		return saleOrderMapper.saleOrderForSaleOrderResponse(this.findById(id));
	}
	
	private List<SaleOrder> listAll() {
		return saleOrderRepository.findAll();
	}

	public SaleOrder findById(Long id) {
		SaleOrder saleOrder = saleOrderRepository.findById(id)
							.orElseThrow(() -> new SaleOrderNotFoundException(id));

		return saleOrder;
	}
}