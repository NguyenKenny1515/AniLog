package com.nguyenkenny.anilog.service;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {

    String uploadImage(MultipartFile file, String folderName);

    void deleteImage(String publicId);
}
