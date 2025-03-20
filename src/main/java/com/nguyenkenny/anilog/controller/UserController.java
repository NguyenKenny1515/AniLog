package com.nguyenkenny.anilog.controller;

import com.nguyenkenny.anilog.authenticationfacade.AuthenticationFacade;
import com.nguyenkenny.anilog.dao.AppUserRepository;
import com.nguyenkenny.anilog.dto.ImageDto;
import com.nguyenkenny.anilog.entity.AppUser;
import com.nguyenkenny.anilog.entity.Image;
import com.nguyenkenny.anilog.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {

    private ImageService imageService;
    private AuthenticationFacade authenticationFacade;
    private AppUserRepository appUserRepository;

    @Autowired
    public UserController(ImageService imageService, AuthenticationFacade authenticationFacade,
                          AppUserRepository appUserRepository) {
        this.imageService = imageService;
        this.authenticationFacade = authenticationFacade;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/edit-picture")
    public String editProfilePicture(@RequestParam("image") MultipartFile file, Model model) {
        try {
            if (file.isEmpty()) {
                return "redirect:home";
            }

            ImageDto imageDto = new ImageDto();
            imageDto.setName(file.getOriginalFilename());
            imageDto.setFile(file);
            Image profilePic = imageService.uploadImage(imageDto);

            Authentication authentication = authenticationFacade.getAuthenticatedUser();
            AppUser user = appUserRepository.findByUsername(authentication.getName());
            user.setProfilePic(profilePic);
            appUserRepository.save(user);

            model.addAttribute("user", user);
            model.addAttribute("uploadSuccess", true);
        } catch (Exception e) {
            model.addAttribute("uploadSuccess", false);
        }

        return "redirect:home";
    }
}
