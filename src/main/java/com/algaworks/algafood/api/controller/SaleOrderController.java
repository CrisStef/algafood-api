package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.response.SaleOrderListResponse;
import com.algaworks.algafood.api.model.response.SaleOrderResponse;
import com.algaworks.algafood.domain.service.SaleOrderService;

@RestController
@RequestMapping("/saleOrders")
public class SaleOrderController {
	@Autowired
	private SaleOrderService saleOrderService;

	@GetMapping
	public List<SaleOrderListResponse> findAll() {
		return saleOrderService.findAll();
	}

	@GetMapping("/{sale_order_id}")
	public SaleOrderResponse getById(@PathVariable("sale_order_id") Long saleOrderId) {
		return saleOrderService.getById(saleOrderId);
	}
}