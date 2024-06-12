package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.algaworks.algafood.api.model.request.SaleOrderRequest;
import com.algaworks.algafood.api.model.response.SaleOrderListResponse;
import com.algaworks.algafood.api.model.response.SaleOrderResponse;
import com.algaworks.algafood.domain.exception.BusinessException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
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

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SaleOrderResponse create(@Valid @RequestBody SaleOrderRequest saleOrderRequest) {
		try {
			return saleOrderService.create(saleOrderRequest);
		} catch (EntityNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}
}