package com.nguyenkenny.anilog.controller;

import com.nguyenkenny.anilog.jikan.Anime;
import com.nguyenkenny.anilog.jikan.AnimeListResponse;
import com.nguyenkenny.anilog.jikan.JikanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/anime")
public class AnimeController {

    private JikanService jikanService;

    @Autowired
    public AnimeController(JikanService jikanService) {
        this.jikanService = jikanService;
    }

    @GetMapping("/search")
    public String showSearchPage(@RequestParam(value = "q") String query,
                                 @RequestParam(defaultValue = "1") int page,
                                 Model model) {
        AnimeListResponse animeListResponse = jikanService.searchAnimeByTitle(query, page);
        model.addAttribute("results", animeListResponse.getData());
        model.addAttribute("pagination", animeListResponse.getPagination());
        model.addAttribute("query", query);

        return "search";
    }

    @GetMapping("/search/api")
    @ResponseBody
    public List<Anime> searchAnime(@RequestParam("q") String query, @RequestParam(defaultValue = "1") int page) {
        List<Anime> results = new ArrayList<>();
        if (query != null) {
            results = jikanService.searchAnimeByTitle(query, page).getData();
        }

        return results;
    }
}
