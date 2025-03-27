package com.nguyenkenny.anilog.service;

import com.nguyenkenny.anilog.dao.AnimeEntryRepository;
import com.nguyenkenny.anilog.entity.AnimeEntry;
import com.nguyenkenny.anilog.entity.AppUser;
import com.nguyenkenny.anilog.jikan.Anime;
import com.nguyenkenny.anilog.jikan.JikanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnimeEntryServiceImpl implements AnimeEntryService {

    private final AnimeEntryRepository animeEntryRepository;
    private final JikanService jikanService;

    @Autowired
    public AnimeEntryServiceImpl(AnimeEntryRepository animeEntryRepository, JikanService jikanService) {
        this.animeEntryRepository = animeEntryRepository;
        this.jikanService = jikanService;
    }

    @Override
    public AnimeEntry findById(int id) {
        Optional<AnimeEntry> result = animeEntryRepository.findById(id);
        return result.orElse(null);
    }

    @Override
    public AnimeEntry save(AnimeEntry animeEntry) {
        return animeEntryRepository.save(animeEntry);
    }

    @Override
    public void delete(AnimeEntry animeEntry) {
        animeEntryRepository.delete(animeEntry);
    }

    @Override
    public AnimeEntry createEntry(AnimeEntry animeEntry, AppUser appUser) {
        Anime anime = jikanService.getAnimeById(animeEntry.getMalId());
        this.setValues(animeEntry, anime);
        animeEntryRepository.save(animeEntry);
        appUser.addAnimeEntry(animeEntry);
        return animeEntry;
    }

    @Override
    public Map<String, Double> calculateAnimeStats(Map<Integer, AnimeEntry> animeEntries) {
        Map<String, Double> animeStats = new HashMap<>(Map.of(
                "averageUserScore", 0.0,
                "totalEntries", 0.0,
                "totalEpisodesWatched", 0.0,
                "totalWatching", 0.0,
                "totalCompleted", 0.0,
                "totalDropped", 0.0,
                "totalPlanned", 0.0
        ));

        for (AnimeEntry animeEntry : animeEntries.values()) {
            animeStats.put("averageUserScore", animeStats.get("averageUserScore") + animeEntry.getUserScore());
            animeStats.put("totalEntries", animeStats.get("totalEntries") + 1);
            animeStats.put("totalEpisodesWatched", animeStats.get("totalEpisodesWatched") + 1);

            switch(animeEntry.getEntryStatus()) {
                case WATCHING -> animeStats.put("totalWatching", animeStats.get("totalWatching") + 1);
                case COMPLETED -> animeStats.put("totalCompleted", animeStats.get("totalCompleted") + 1);
                case DROPPED -> animeStats.put("totalDropped", animeStats.get("totalDropped") + 1);
                case PLANNED -> animeStats.put("totalPlanned", animeStats.get("totalPlanned") + 1);
            }
        }

        if (animeStats.get("totalEntries") != 0) {
            animeStats.put("averageUserScore", animeStats.get("averageUserScore") / animeStats.get("totalEntries"));
        }

        return animeStats;
    }

    private void setValues(AnimeEntry animeEntry, Anime anime) {
        animeEntry.setImageUrl(anime.getImages().getWebp().getImageUrl());
        animeEntry.setTitle(anime.getTitle());
        animeEntry.setType(anime.getType());
        animeEntry.setStatus(anime.getStatus());
        animeEntry.setAired(anime.getAired().getAiredDates());
        animeEntry.setScore(anime.getScore());
        animeEntry.setEpisodes(anime.getEpisodes());

        String genres = anime.getGenres()
                .stream()
                .map(Anime.Genre::getName)
                .collect(Collectors.joining(", "));
        animeEntry.setGenres(genres);

        String studios = anime.getStudios()
                .stream()
                .map(Anime.Studio::getName)
                .collect(Collectors.joining(", "));
        animeEntry.setStudios(studios);
    }
}
