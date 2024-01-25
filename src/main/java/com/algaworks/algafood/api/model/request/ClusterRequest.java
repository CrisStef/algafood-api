package com.algaworks.algafood.api.model.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClusterRequest {
	@NotBlank
	private String name;
}