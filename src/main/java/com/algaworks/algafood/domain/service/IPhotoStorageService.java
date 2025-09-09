package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.dto.NewPhoto;

import java.util.UUID;

public interface IPhotoStorageService {
    void store(NewPhoto newPhoto);

    default String generateFileName(String fileName) {
        return UUID.randomUUID().toString() + "_" + fileName;
    }
}