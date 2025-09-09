package com.algaworks.algafood.domain.util;

public class FileNameUtils {
    public static String removeExtension(String originalFilename) {
        if (originalFilename == null || originalFilename.isEmpty()) {
            return originalFilename;
        }
        int dotIndex = originalFilename.lastIndexOf('.');
        return (dotIndex == -1) ? originalFilename : originalFilename.substring(0, dotIndex);
    }

    public static String getExtension(String originalFilename) {
        if (originalFilename == null || originalFilename.isEmpty()) {
            return "";
        }
        int dotIndex = originalFilename.lastIndexOf('.');

        return (dotIndex == -1) ? "" : originalFilename.substring(dotIndex + 1);
    }
}
