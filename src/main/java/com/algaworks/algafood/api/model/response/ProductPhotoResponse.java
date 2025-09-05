package com.algaworks.algafood.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoResponse {
	private String fileName;
	private String description;
	private String contentType;
	private Long fileSize;
}