package com.nguyenkenny.anilog.service;

import com.nguyenkenny.anilog.dao.ImageRepository;
import com.nguyenkenny.anilog.dto.ImageDto;
import com.nguyenkenny.anilog.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ImageServiceImpl implements ImageService {

    private CloudinaryService cloudinaryService;
    private ImageRepository imageRepository;

    @Value("${anilog.default_profile_pic}")
    private String defaultPicUrl;

    @Autowired
    public ImageServiceImpl(CloudinaryService cloudinaryService, ImageRepository imageRepository) {
        this.cloudinaryService = cloudinaryService;
        this.imageRepository = imageRepository;
    }

    @Override
    public void save(Image newImage) {
        imageRepository.save(newImage);
    }

    @Override
    public void delete(Image image) {
        String url = image.getUrl();

        if (!url.equals(defaultPicUrl)) {
            Matcher matcher = Pattern.compile("(?<=v1/)(.*?)(?=\\?_a=)").matcher(url);
            if (matcher.find()) {
                String publicId = matcher.group(1);
                cloudinaryService.deleteImage(publicId);
            }
        }

        imageRepository.deleteById(image.getId());
    }

    @Override
    public Image uploadImage(ImageDto imageDto) {
        try {
            if (imageDto.getName().isEmpty() || imageDto.getFile().isEmpty()) {
                return null;
            }

            Image image = new Image(imageDto.getName(), cloudinaryService.uploadImage(imageDto.getFile(), "AniLog"));
            if (image.getUrl() == null) {
                return null;
            }
            save(image);

            return image;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
