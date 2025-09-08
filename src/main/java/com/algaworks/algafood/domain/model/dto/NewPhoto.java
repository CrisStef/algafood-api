package com.algaworks.algafood.domain.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.io.InputStream;

@Builder
@Getter
public class NewPhoto {
    private String fileName;
    private InputStream inputStream;
}