package com.nguyenkenny.anilog.service;

import com.nguyenkenny.anilog.entity.AppUser;

import java.util.List;

public interface AppUserService {

    List<AppUser> findAll();

    AppUser findByUsername(String username);

    AppUser getAuthenticatedUser();

    AppUser save(AppUser newUser);

    void deleteById(String username);
}
