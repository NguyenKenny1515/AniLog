package com.nguyenkenny.anilog.controller;

import com.nguyenkenny.anilog.dto.ImageDto;
import com.nguyenkenny.anilog.entity.AppUser;
import com.nguyenkenny.anilog.entity.Image;
import com.nguyenkenny.anilog.service.AppUserService;
import com.nguyenkenny.anilog.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class UserController {

    private ImageService imageService;
    private AppUserService appUserService;

    @Autowired
    public UserController(ImageService imageService, AppUserService appUserService) {
        this.imageService = imageService;
        this.appUserService = appUserService;
    }

    @PostMapping("/edit-picture")
    public String editProfilePicture(@RequestParam("image") MultipartFile file, Model model) {
        try {
            if (file.isEmpty()) {
                return "redirect:home";
            }

            ImageDto imageDto = new ImageDto(file.getOriginalFilename(), file);
            appUserService.changeProfilePicture(imageDto);
            AppUser user = appUserService.getAuthenticatedUser();

            model.addAttribute("user", user);
            model.addAttribute("uploadSuccess", true);
        } catch (Exception e) {
            model.addAttribute("uploadSuccess", false);
        }

        return "redirect:home";
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
