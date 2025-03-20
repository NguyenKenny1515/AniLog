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

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, AuthenticationFacade authenticationFacade) {
        this.appUserRepository = appUserRepository;
        this.authenticationFacade = authenticationFacade;
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
        appUserRepository.deleteById(username);
    }
}
