package com.nguyenkenny.anilog.controller;

import com.nguyenkenny.anilog.entity.AppUser;
import com.nguyenkenny.anilog.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AuthenticationController {

    private AppUserService appUserService;

    @Autowired
    public AuthenticationController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping(value = {"", "/", "home"})
    public String showHomePage(Model model) {
        AppUser user = appUserService.getAuthenticatedUser();

        if (user != null) {
            model.addAttribute("user", user);
            return "home";
        }

        return "landing-page";
    }

    @GetMapping("/access-denied")
    public String showAccessDeniedPage() {
        return "access-denied";
    }

    @GetMapping("/error")
    public String showErrorPage() {
        return "error";
    }
}
