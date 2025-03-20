package com.nguyenkenny.anilog.controller;

import com.nguyenkenny.anilog.dto.UserRegistrationDto;
import com.nguyenkenny.anilog.entity.AppAuthority;
import com.nguyenkenny.anilog.entity.AppUser;
import com.nguyenkenny.anilog.entity.Image;
import com.nguyenkenny.anilog.service.AppUserService;
import com.nguyenkenny.anilog.service.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.ZonedDateTime;

@Controller
public class RegistrationController {

    private AppUserService appUserService;
    private PasswordEncoder passwordEncoder;
    private ImageService imageService;

    @Autowired
    public RegistrationController(AppUserService appUserService, PasswordEncoder passwordEncoder,
                                  ImageService imageService) {
        this.appUserService = appUserService;
        this.passwordEncoder = passwordEncoder;
        this.imageService = imageService;
    }

    @GetMapping("/registration")
    public String showRegistrationPage(Model model) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("user", userRegistrationDto);
        if (model.getAttribute("success") == null) {
            model.addAttribute("success", false);
        }

        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") @Valid UserRegistrationDto userRegistrationDto,
                               BindingResult bindingResult, Model model, RedirectAttributes redirAttr) {
        AppUser appUser = appUserService.findByUsername(userRegistrationDto.getUsername());
        if (appUser != null) {
            bindingResult.addError(new FieldError("user", "username", "This username has already been taken"));
        }

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        // If no validation errors, create the account!
        try {
            AppUser newUser = new AppUser();
            newUser.setUsername(userRegistrationDto.getUsername());
            newUser.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
            newUser.setEnabled(true);
            newUser.setCreatedDatetime(ZonedDateTime.now());

            Image defaultPicture = new Image();
            defaultPicture.setName("default_" + newUser.getUsername());
            defaultPicture.setUrl("https://res.cloudinary.com/dmzamfvly/image/upload/v1742401957/Default-Profile-Picture.png");
            imageService.save(defaultPicture);
            newUser.setProfilePic(defaultPicture);

            AppAuthority newAuthority = new AppAuthority();
            newAuthority.setAuthority("ROLE_USER");
            newAuthority.setAppUser(newUser);
            newUser.addRole(newAuthority);

            appUserService.save(newUser);

            model.addAttribute("user", new UserRegistrationDto());
            redirAttr.addFlashAttribute("success", true);
        } catch (Exception e) {
            bindingResult.addError(new FieldError("user", "username", e.getMessage()));
        }

        return "redirect:registration";
    }
}
