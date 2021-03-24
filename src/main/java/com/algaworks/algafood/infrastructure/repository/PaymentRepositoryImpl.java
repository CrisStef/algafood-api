package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.algaworks.algafood.domain.model.Payment;
import com.algaworks.algafood.domain.repository.PaymentRepository;

import org.springframework.stereotype.Component;

@Component
public class PaymentRepositoryImpl implements PaymentRepository {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Payment> listAll() {
		return manager.createQuery("from Payment", Payment.class).getResultList();
	}

	@Override
	public Payment findById(Long id) {
		return manager.find(Payment.class, id);
	}

	@Transactional
	@Override
	public Payment save(Payment payment) {
		return manager.merge(payment);
	}

	@Transactional
	@Override
	public void remove(Payment payment) {
		payment = findById(payment.getId());
		manager.remove(payment);
	}
}