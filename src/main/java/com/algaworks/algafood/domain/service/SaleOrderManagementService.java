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
	public void confirmationSaleOrder(Long saleOrderId) {
		SaleOrder saleOrder = saleOrderService.findById(saleOrderId);

		saleOrder.confirmation();
	}

	@Transactional
	public void deliverySaleOrder(Long saleOrderId) {
		SaleOrder saleOrder = saleOrderService.findById(saleOrderId);

		saleOrder.delivered();
	}

	@Transactional
	public void cancellationSaleOrder(Long saleOrderId) {
		SaleOrder saleOrder = saleOrderService.findById(saleOrderId);

		saleOrder.canceled();
	}
}