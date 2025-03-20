package com.nguyenkenny.anilog.service;

import com.nguyenkenny.anilog.dto.ImageDto;
import com.nguyenkenny.anilog.entity.Image;

import java.util.UUID;

public interface ImageService {

    Image save(Image newImage);

    Image findById(UUID id);

    Image uploadImage(ImageDto imageDto);
}
