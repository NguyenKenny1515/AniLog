package com.nguyenkenny.anilog.controller;

import com.nguyenkenny.anilog.jikan.Anime;
import com.nguyenkenny.anilog.jikan.AnimeListResponse;
import com.nguyenkenny.anilog.jikan.JikanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/anime")
public class AnimeController {

    private final JikanService jikanService;

    @Autowired
    public AnimeController(JikanService jikanService) {
        this.jikanService = jikanService;
    }

    @GetMapping("/overview")
    public String showOverviewPage(Model model) throws InterruptedException {
        List<Anime> top = jikanService.getTopAnime();
        List<Anime> current = jikanService.getTopAiringAnime();
        List<Anime> upcoming = jikanService.getTopUpcomingAnime();

        List<List<Anime>> groupedTop = IntStream.range(0, (top.size() + 4) / 5)
                .mapToObj(i -> top.subList(i * 5, Math.min((i + 1) * 5, top.size())))
                .toList();
        List<List<Anime>> groupedCurrent = IntStream.range(0, (current.size() + 4) / 5)
                .mapToObj(i -> current.subList(i * 5, Math.min((i + 1) * 5, current.size())))
                .toList();
        List<List<Anime>> groupedUpcoming = IntStream.range(0, (upcoming.size() + 4) / 5)
                .mapToObj(i -> upcoming.subList(i * 5, Math.min((i + 1) * 5, upcoming.size())))
                .toList();

        model.addAttribute("groupedTop", groupedTop);
        model.addAttribute("groupedCurrent", groupedCurrent);
        model.addAttribute("groupedUpcoming", groupedUpcoming);

        return "anime-overview";
    }

    @GetMapping("/{id}")
    public String showDetailsPage(@PathVariable int id, Model model) {
        Anime anime = jikanService.getAnimeById(id);

        if (anime == null) {
            throw new RuntimeException("Anime not found - " + id);
        }

        model.addAttribute("anime", anime);

        return "anime-details";
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
