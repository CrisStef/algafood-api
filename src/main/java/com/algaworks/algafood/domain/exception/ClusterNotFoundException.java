package com.algaworks.algafood.domain.exception;

public class ClusterNotFoundException extends EntityNotFoundException {
	private static final long serialVersionUID = 4879555631582992766L;

	public ClusterNotFoundException(String message) {
		super(message);
	}

	public ClusterNotFoundException(Long id) {
		super(String.format("Cluster not found! Id: %d", id));
	}
}