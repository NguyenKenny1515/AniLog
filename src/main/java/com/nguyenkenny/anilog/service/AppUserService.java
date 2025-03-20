package com.nguyenkenny.anilog.service;

import com.nguyenkenny.anilog.entity.AppUser;

public interface AppUserService {

    AppUser findByUsername(String username);

    AppUser getAuthenticatedUser();

    AppUser save(AppUser newUser);
}
