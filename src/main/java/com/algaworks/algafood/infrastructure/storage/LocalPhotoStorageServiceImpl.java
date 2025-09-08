package com.algaworks.algafood.infrastructure.storage;

import com.algaworks.algafood.domain.exception.StorageException;
import com.algaworks.algafood.domain.model.dto.NewPhoto;
import com.algaworks.algafood.domain.service.IPhotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LocalPhotoStorageServiceImpl implements IPhotoStorageService {
    @Value("${algafood.storage.local.folder-photos}")
    private Path folderPhotos;
    @Override
    public void store(NewPhoto newPhoto) {
        try {
            Path filePath = getFilePath(newPhoto.getFileName());

            FileCopyUtils.copy(newPhoto.getInputStream(),
                    Files.newOutputStream(filePath));

        } catch (Exception e) {
            throw new StorageException("Não foi possível armazenar o arquivo.", e);
        }
    }

    private Path getFilePath(String fileName) {
        return folderPhotos.resolve(Path.of(fileName));
    }
}