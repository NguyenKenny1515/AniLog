package com.nguyenkenny.anilog.dao;

import com.nguyenkenny.anilog.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, String> {

    List<AppUser> findAllByOrderByUsername();

    Optional<AppUser> findByUsername(String username);
}
