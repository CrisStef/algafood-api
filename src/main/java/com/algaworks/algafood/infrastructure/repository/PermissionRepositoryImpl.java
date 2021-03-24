package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.algaworks.algafood.domain.model.Permission;
import com.algaworks.algafood.domain.repository.PermissionRepository;

import org.springframework.stereotype.Component;

@Component
public class PermissionRepositoryImpl implements PermissionRepository {
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Permission> listAll() {
		return manager.createQuery("from Permission", Permission.class).getResultList();
	}

	@Override
	public Permission findById(Long id) {
		return manager.find(Permission.class, id);
	}

	@Transactional
	@Override
	public Permission save(Permission permission) {
		return manager.merge(permission);
	}

	@Transactional
	@Override
	public void remove(Permission permission) {
		permission = findById(permission.getId());
		manager.remove(permission);
	}
}