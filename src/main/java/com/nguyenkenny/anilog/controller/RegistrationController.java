package com.nguyenkenny.anilog.controller;

import com.nguyenkenny.anilog.dao.AppUserRepository;
import com.nguyenkenny.anilog.dto.UserRegistrationDto;
import com.nguyenkenny.anilog.entity.AppAuthority;
import com.nguyenkenny.anilog.entity.AppUser;
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

import java.time.ZonedDateTime;

@Controller
public class RegistrationController {

    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/registration")
    public String showRegistrationPage(Model model) {
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        model.addAttribute("user", userRegistrationDto);
        model.addAttribute("success", false);
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid UserRegistrationDto userRegistrationDto,
                               BindingResult bindingResult, Model model) {
        AppUser appUser = appUserRepository.findByUsername(userRegistrationDto.getUsername());
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

            AppAuthority newAuthority = new AppAuthority();
            newAuthority.setAuthority("ROLE_USER");
            newAuthority.setAppUser(newUser);
            newUser.addRole(newAuthority);

            appUserRepository.save(newUser);

            model.addAttribute("user", new UserRegistrationDto());
            model.addAttribute("success", true);
        } catch (Exception e) {
            bindingResult.addError(new FieldError("user", "username", e.getMessage()));
        }

        return "registration";
    }
}
