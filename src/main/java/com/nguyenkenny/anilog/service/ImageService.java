package com.nguyenkenny.anilog.service;

import com.nguyenkenny.anilog.dto.ImageDto;
import com.nguyenkenny.anilog.entity.Image;

public interface ImageService {

    Image uploadImage(ImageDto imageDto);
}
