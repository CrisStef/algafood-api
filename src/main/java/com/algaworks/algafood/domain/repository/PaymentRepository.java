package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Payment;

public interface PaymentRepository {
	List<Payment> listAll();

	Payment findById(Long id);

	Payment save(Payment payment);

	void remove(Payment payment);
}