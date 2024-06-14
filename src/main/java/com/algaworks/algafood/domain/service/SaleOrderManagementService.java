package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.SaleOrder;

@Service
public class SaleOrderManagementService {
	@Autowired
	private SaleOrderService saleOrderService;

	@Transactional
	public void confirmationSaleOrder(String saleOrderCode) {
		SaleOrder saleOrder = saleOrderService.findByCode(saleOrderCode);

		saleOrder.confirmation();
	}

	@Transactional
	public void deliverySaleOrder(String saleOrderCode) {
		SaleOrder saleOrder = saleOrderService.findByCode(saleOrderCode);

		saleOrder.delivered();
	}

	@Transactional
	public void cancellationSaleOrder(String saleOrderCode) {
		SaleOrder saleOrder = saleOrderService.findByCode(saleOrderCode);

		saleOrder.canceled();
	}
}