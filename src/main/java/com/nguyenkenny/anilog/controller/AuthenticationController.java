package com.nguyenkenny.anilog.controller;

import com.nguyenkenny.anilog.authenticationfacade.AuthenticationFacade;
import com.nguyenkenny.anilog.dao.AppUserRepository;
import com.nguyenkenny.anilog.entity.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AuthenticationController {

    private AuthenticationFacade authenticationFacade;
    private AppUserRepository appUserRepository;

    @Autowired
    public AuthenticationController(AuthenticationFacade authenticationFacade, AppUserRepository appUserRepository) {
        this.authenticationFacade = authenticationFacade;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping(value = {"", "/", "home"})
    public String showHomePage(Model model) {
        Authentication authentication = authenticationFacade.getAuthenticatedUser();
        AppUser user = appUserRepository.findByUsername(authentication.getName());

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
