package com.nguyenkenny.anilog.controller;

import com.nguyenkenny.anilog.dto.UserRegistrationDto;
import com.nguyenkenny.anilog.entity.AppUser;
import com.nguyenkenny.anilog.service.AppUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    private final AppUserService appUserService;

    @Autowired
    public RegistrationController(AppUserService appUserService) {
        this.appUserService = appUserService;
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
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirAttr) {
        AppUser appUser = appUserService.findByUsername(userRegistrationDto.getUsername());
        if (appUser != null) {
            bindingResult.addError(new FieldError("user", "username", "This username has already been taken"));
        }

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        // If no validation errors, create the account!
        try {
            appUserService.createNewUser(userRegistrationDto);
            model.addAttribute("user", new UserRegistrationDto());
            redirAttr.addFlashAttribute("success", true);
        } catch (Exception e) {
            bindingResult.addError(new FieldError("user", "username", e.getMessage()));
        }

        return "redirect:registration";
    }
}
