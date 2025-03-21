package com.nguyenkenny.anilog.service;

import com.nguyenkenny.anilog.authenticationfacade.AuthenticationFacade;
import com.nguyenkenny.anilog.dao.AppUserRepository;
import com.nguyenkenny.anilog.dto.ImageDto;
import com.nguyenkenny.anilog.dto.UserRegistrationDto;
import com.nguyenkenny.anilog.entity.AppAuthority;
import com.nguyenkenny.anilog.entity.AppUser;
import com.nguyenkenny.anilog.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;
    private AuthenticationFacade authenticationFacade;
    private ImageService imageService;
    private PasswordEncoder passwordEncoder;

    @Value("${anilog.default_profile_pic}")
    private String defaultPicUrl;

    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository, AuthenticationFacade authenticationFacade,
                              ImageService imageService, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.authenticationFacade = authenticationFacade;
        this.imageService = imageService;
        this.passwordEncoder = passwordEncoder;
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

    @Override
    public AppUser createNewUser(UserRegistrationDto userRegistrationDto) {
        AppUser newUser = new AppUser(userRegistrationDto.getUsername(),
                passwordEncoder.encode(userRegistrationDto.getPassword()), true, ZonedDateTime.now());

        Image defaultPicture = new Image("default_" + newUser.getUsername(), defaultPicUrl);
        imageService.save(defaultPicture);
        newUser.setProfilePic(defaultPicture);

        AppAuthority newAuthority = new AppAuthority(newUser, "ROLE_USER");
        newUser.addRole(newAuthority);

        this.save(newUser);
        return newUser;
    }

    @Override
    public void changeProfilePicture(ImageDto imageDto) {
        Image profilePic = imageService.uploadImage(imageDto);

        AppUser user = this.getAuthenticatedUser();
        Image oldProfilePic = user.getProfilePic();
        user.setProfilePic(profilePic);

        imageService.delete(oldProfilePic);

        this.save(user);
    }
}
