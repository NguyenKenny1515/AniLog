package com.nguyenkenny.anilog.controller;

import com.nguyenkenny.anilog.entity.AnimeEntry;
import com.nguyenkenny.anilog.entity.AppUser;
import com.nguyenkenny.anilog.enums.EntryStatus;
import com.nguyenkenny.anilog.jikan.Anime;
import com.nguyenkenny.anilog.jikan.AnimeListResponse;
import com.nguyenkenny.anilog.jikan.JikanService;
import com.nguyenkenny.anilog.service.AppUserService;
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
    private final AppUserService appUserService;

    @Autowired
    public AnimeController(JikanService jikanService, AppUserService appUserService) {
        this.jikanService = jikanService;
        this.appUserService = appUserService;
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
        AppUser appUser = appUserService.getAuthenticatedUserWithEntries();
        AnimeEntry animeEntry = appUser.getAnimeEntries().getOrDefault(id, null);

        if (anime == null) {
            throw new RuntimeException("Anime not found - " + id);
        }

        if (animeEntry == null) {
            animeEntry = new AnimeEntry(appUser, id);
        }

        model.addAttribute("anime", anime);
        model.addAttribute("animeEntry", animeEntry);
        model.addAttribute("entryStatuses", EntryStatus.values());

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
