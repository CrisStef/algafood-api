package com.algaworks.algafood.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.service.SaleOrderManagementService;

@RestController
@RequestMapping("/saleOrders/{sale_order_id}")
public class SaleOrderManagementController {
	@Autowired
	private SaleOrderManagementService saleOrderManagementService;

	@PutMapping("/confirmation")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmationSaleOrder(@PathVariable("sale_order_id") Long saleOrderId) {
		saleOrderManagementService.confirmationSaleOrder(saleOrderId);
	}

	@PutMapping("/delivery")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deliverySaleOrder(@PathVariable("sale_order_id") Long saleOrderId) {
		saleOrderManagementService.deliverySaleOrder(saleOrderId);
	}

	@PutMapping("/cancellation")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancellationSaleOrder(@PathVariable("sale_order_id") Long saleOrderId) {
		saleOrderManagementService.cancellationSaleOrder(saleOrderId);
	}
}