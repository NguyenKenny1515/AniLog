package com.nguyenkenny.anilog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping(value={"", "/", "home"})
    public String showHomePage(Principal principal) {
        if (principal != null) {
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
