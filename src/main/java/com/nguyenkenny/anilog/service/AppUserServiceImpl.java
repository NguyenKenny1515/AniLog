package com.nguyenkenny.anilog.service;

import com.nguyenkenny.anilog.authenticationfacade.AuthenticationFacade;
import com.nguyenkenny.anilog.dao.AppUserRepository;
import com.nguyenkenny.anilog.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;
    private AuthenticationFacade authenticationFacade;
    private ImageService imageService;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, AuthenticationFacade authenticationFacade,
                              ImageService imageService) {
        this.appUserRepository = appUserRepository;
        this.authenticationFacade = authenticationFacade;
        this.imageService = imageService;
    }

    @Override
    public List<AppUser> findAll() {
        return appUserRepository.findAllByOrderByUsername();
    }

    @Override
    public AppUser findByUsername(String username) {
        Optional<AppUser> result = appUserRepository.findByUsername(username);
        return result.orElse(null);
    }

    @Override
    public AppUser getAuthenticatedUser() {
        String username = authenticationFacade.getAuthenticatedUser().getName();
        return findByUsername(username);
    }

    @Override
    public AppUser save(AppUser newUser) {
        return appUserRepository.save(newUser);
    }

    @Override
    public void deleteById(String username) {
        AppUser user = findByUsername(username);
        // Deleting image directly instead of just relying on the cascade because we need to delete it from Cloudinary
        imageService.delete(user.getProfilePic());
        appUserRepository.deleteById(username);
    }
}
