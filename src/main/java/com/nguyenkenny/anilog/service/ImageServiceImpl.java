package com.nguyenkenny.anilog.service;

import com.nguyenkenny.anilog.dao.ImageRepository;
import com.nguyenkenny.anilog.dto.ImageDto;
import com.nguyenkenny.anilog.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    private CloudinaryService cloudinaryService;
    private ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(CloudinaryService cloudinaryService, ImageRepository imageRepository) {
        this.cloudinaryService = cloudinaryService;
        this.imageRepository = imageRepository;
    }

    @Override
    public Image uploadImage(ImageDto imageDto) {
        try {
            if (imageDto.getName().isEmpty() || imageDto.getFile().isEmpty()) {
                return null;
            }

            Image image = new Image();
            image.setName(imageDto.getName());
            image.setUrl(cloudinaryService.uploadFile(imageDto.getFile(), "AniLog"));
            if (image.getUrl() == null) {
                return null;
            }
            imageRepository.save(image);

            return image;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
