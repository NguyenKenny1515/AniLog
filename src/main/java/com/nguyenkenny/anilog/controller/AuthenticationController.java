package com.nguyenkenny.anilog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping(value = {"", "/"})
    public String showLandingPage(Model model) {
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
