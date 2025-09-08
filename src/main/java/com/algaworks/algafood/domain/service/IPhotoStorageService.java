package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.model.dto.NewPhoto;

public interface IPhotoStorageService {
    void store(NewPhoto newPhoto);
}