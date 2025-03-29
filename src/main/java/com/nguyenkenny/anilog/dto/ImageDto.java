package com.nguyenkenny.anilog.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImageDto {

    private final String name;
    private final MultipartFile file;

    public ImageDto(String name, MultipartFile file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public MultipartFile getFile() {
        return file;
    }
}
