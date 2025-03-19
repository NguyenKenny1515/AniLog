package com.nguyenkenny.anilog.dao;

import com.nguyenkenny.anilog.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}
