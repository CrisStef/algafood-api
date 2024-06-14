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
@RequestMapping("/saleOrders/{sale_order_code}")
public class SaleOrderManagementController {
	@Autowired
	private SaleOrderManagementService saleOrderManagementService;

	@PutMapping("/confirmation")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmationSaleOrder(@PathVariable("sale_order_code") String saleOrderCode) {
		saleOrderManagementService.confirmationSaleOrder(saleOrderCode);
	}

	@PutMapping("/delivery")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deliverySaleOrder(@PathVariable("sale_order_code") String saleOrderCode) {
		saleOrderManagementService.deliverySaleOrder(saleOrderCode);
	}

	@PutMapping("/cancellation")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancellationSaleOrder(@PathVariable("sale_order_code") String saleOrderCode) {
		saleOrderManagementService.cancellationSaleOrder(saleOrderCode);
	}
}