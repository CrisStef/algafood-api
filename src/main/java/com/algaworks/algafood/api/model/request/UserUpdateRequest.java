package com.algaworks.algafood.api.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
}