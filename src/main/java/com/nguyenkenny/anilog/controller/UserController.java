package com.nguyenkenny.anilog.controller;

import com.nguyenkenny.anilog.dto.ImageDto;
import com.nguyenkenny.anilog.entity.AppUser;
import com.nguyenkenny.anilog.enums.EntryStatus;
import com.nguyenkenny.anilog.service.AnimeEntryService;
import com.nguyenkenny.anilog.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private AppUserService appUserService;
    private AnimeEntryService animeEntryService;

    @Autowired
    public UserController(AppUserService appUserService, AnimeEntryService animeEntryService) {
        this.appUserService = appUserService;
        this.animeEntryService = animeEntryService;
    }

    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        AppUser user = appUserService.getAuthenticatedUserWithEntries();
        Map<String, Double> animeStats = animeEntryService.calculateAnimeStats(user.getAnimeEntries());
        model.addAttribute("user", user);
        model.addAttribute("animeEntries", user.getAnimeEntries().values());
        model.addAttribute("animeStats", animeStats);
        model.addAttribute("entryStatuses", EntryStatus.values());

        return "profile";
    }

    @PostMapping("/edit-picture")
    public String editProfilePicture(@RequestParam("image") MultipartFile file, Model model) {
        try {
            if (file.isEmpty()) {
                return "redirect:profile";
            }

            ImageDto imageDto = new ImageDto(file.getOriginalFilename(), file);
            appUserService.changeProfilePicture(imageDto);
            AppUser user = appUserService.getAuthenticatedUser();

            model.addAttribute("user", user);
            model.addAttribute("uploadSuccess", true);
        } catch (Exception e) {
            model.addAttribute("uploadSuccess", false);
        }

        return "redirect:profile";
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
