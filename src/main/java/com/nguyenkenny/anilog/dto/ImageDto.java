package com.nguyenkenny.anilog.dto;

import org.springframework.web.multipart.MultipartFile;

public class ImageDto {

    private String name;
    private MultipartFile file;

    public ImageDto(String name, MultipartFile file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
