package com.nguyenkenny.anilog.service;

import com.nguyenkenny.anilog.dto.ImageDto;
import com.nguyenkenny.anilog.entity.Image;

public interface ImageService {

    void save(Image newImage);

    void delete(Image image);

    Image uploadImage(ImageDto imageDto);
}
