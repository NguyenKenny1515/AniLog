package com.nguyenkenny.anilog.controller;

import com.nguyenkenny.anilog.entity.AppUser;
import com.nguyenkenny.anilog.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MiscellaneousController {

    private final AppUserService appUserService;

    @Autowired
    public MiscellaneousController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }

    @GetMapping("/admin/list")
    public String listUsers(Model model) {
        List<AppUser> users = appUserService.findAll();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/admin/delete")
    public String deleteUser(@RequestParam("username") String username) {
        appUserService.deleteById(username);
        return "redirect:/admin/list";
    }
}
