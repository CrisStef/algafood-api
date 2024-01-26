package com.algaworks.algafood.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
	private Long id;
	private String name;
	private String email;
}