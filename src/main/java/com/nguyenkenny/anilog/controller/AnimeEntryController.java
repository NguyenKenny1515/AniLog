package com.nguyenkenny.anilog.controller;

import com.nguyenkenny.anilog.entity.AnimeEntry;
import com.nguyenkenny.anilog.service.AnimeEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/entries")
public class AnimeEntryController {

    private final AnimeEntryService animeEntryService;

    @Autowired
    public AnimeEntryController(AnimeEntryService animeEntryService) {
        this.animeEntryService = animeEntryService;
    }

    @GetMapping("/{id}")
    public AnimeEntry getAnimeEntry(@PathVariable int id) {
        AnimeEntry animeEntry = animeEntryService.findById(id);

        if (animeEntry == null) {
            throw new RuntimeException("Anime entry id not found - " + id);
        }

        return animeEntry;
    }

    @PostMapping("")
    public String addAnimeEntry(@ModelAttribute AnimeEntry animeEntry) {
        AnimeEntry createdEntry = animeEntryService.createEntry(animeEntry, animeEntry.getAppUser());

        return "redirect:/anime/" + createdEntry.getMalId();
    }

    @PatchMapping("/{id}")
    public String updateAnimeEntry(@PathVariable int id, @ModelAttribute AnimeEntry animeEntry,
                                   @RequestParam(value = "source") String source) {
        AnimeEntry existingEntry = animeEntryService.findById(id);

        if (animeEntry == null) {
            throw new RuntimeException("Anime entry id not found - " + id);
        }

        existingEntry.setEntryStatus(animeEntry.getEntryStatus());
        existingEntry.setEpisodesWatched(animeEntry.getEpisodesWatched());
        existingEntry.setUserScore(animeEntry.getUserScore());

        animeEntryService.save(existingEntry);

        if (source.equals("profile")) {
            return "redirect:/profile";
        }
        return "redirect:/anime/" + existingEntry.getMalId();
    }

    @DeleteMapping("/{id}")
    public String deleteAnimeEntry(@PathVariable int id, @RequestParam(value = "source") String source) {
        AnimeEntry animeEntry = animeEntryService.findById(id);

        if (animeEntry == null) {
            throw new RuntimeException("Anime entry id not found - " + id);
        }

        int malId = animeEntry.getMalId();

        animeEntryService.delete(animeEntry);

        if (source.equals("profile")) {
            return "redirect:/profile";
        }
        return "redirect:/anime/" + malId;
    }
}
