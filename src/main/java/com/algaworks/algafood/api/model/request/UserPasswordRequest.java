package com.algaworks.algafood.api.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPasswordRequest {
	@NotBlank
	private String currentPassword;
	@NotBlank
	private String newPassword;
}