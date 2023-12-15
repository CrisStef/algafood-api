package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.request.PaymentRequest;
import com.algaworks.algafood.api.model.response.PaymentResponse;
import com.algaworks.algafood.domain.exception.BusinessException;
import com.algaworks.algafood.domain.exception.StateNotFoundException;
import com.algaworks.algafood.domain.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	@GetMapping
	public List<PaymentResponse> findAll() {
		return paymentService.findAll();
	}

	@GetMapping("/{payment_id}")
	public PaymentResponse getById(@PathVariable("payment_id") Long id) {
		return paymentService.getById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PaymentResponse create(@RequestBody @Valid PaymentRequest payment) {
		try {
			return paymentService.create(payment);
		} catch (StateNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@PutMapping("/{payment_id}")
	public PaymentResponse alter(@RequestBody @Valid PaymentRequest payment, @PathVariable("payment_id") Long id) {
		try {
			return paymentService.alter(payment, id);
		} catch (StateNotFoundException e) {
			throw new BusinessException(e.getMessage(), e);
		}
	}

	@DeleteMapping("/{payment_id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remove(@PathVariable("payment_id") Long id) {
		paymentService.remove(id);
	}
}