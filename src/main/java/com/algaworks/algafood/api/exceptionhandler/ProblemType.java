package com.algaworks.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	ENTITY_NOT_FOUND("/entity-not-found", "Entity not found"),
	ENTITY_IN_USE("/entity-in-use", "Entity in use"),
	BUSINESS_ERROR("/business-error", "Business Error"),
	INCOMPREENSIBLE_MESSAGE("/incompreensible-message", "Incompreensible message"),
	INVALID_PARAMETER("/invalid-parameter", "Invalid parameter");
	
	private static final String URL = "https://algafood.com.br";
	private String title;
	private String uri;

	ProblemType(String path, String title) {
		StringBuilder uri = new StringBuilder();
		this.uri = uri.append(URL).append(path).toString();
		this.title = title;
	}
}