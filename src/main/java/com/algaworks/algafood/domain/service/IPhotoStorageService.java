package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.dto.NewPhoto;

import java.util.UUID;

public interface IPhotoStorageService {
    void store(NewPhoto newPhoto);

    void remove(String fileName);

    default String generateFileName(String fileName) {
        return UUID.randomUUID().toString() + "_" + fileName;
    }

    default void replace(String existingFileName, NewPhoto newPhoto) {
        this.store(newPhoto);

        if (existingFileName != null) {
            this.remove(existingFileName);
        }
    }
}