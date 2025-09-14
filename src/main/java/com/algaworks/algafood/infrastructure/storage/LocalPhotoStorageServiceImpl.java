package com.algaworks.algafood.infrastructure.storage;

import com.algaworks.algafood.domain.exception.StorageException;
import com.algaworks.algafood.domain.model.dto.NewPhoto;
import com.algaworks.algafood.domain.service.IPhotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
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

    @Override
    public void remove(String fileName) {
        try {
            Path filePath = getFilePath(fileName);
            Files.deleteIfExists(filePath);

        } catch (Exception e) {
            throw new StorageException("Não foi possível deletar o arquivo.", e);
        }
    }

    @Override
    public InputStream recover(String fileName) {
        try {
            Path filePath = getFilePath(fileName);
            return Files.newInputStream(filePath);
        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar arquivo.", e);
        }
    }

    private Path getFilePath(String fileName) {
        return folderPhotos.resolve(Path.of(fileName));
    }
}