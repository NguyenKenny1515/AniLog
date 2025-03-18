package com.nguyenkenny.anilog.dao;

import com.nguyenkenny.anilog.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, String> {

    public AppUser findByUsername(String username);
}
