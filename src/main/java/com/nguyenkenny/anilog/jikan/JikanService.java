package com.nguyenkenny.anilog.jikan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

@Service
public class JikanService {

    private final WebClient webClient;

    @Autowired
    public JikanService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Anime getAnimeById(int id) {
        return webClient.get()
                .uri(String.format("/anime/%d/full", id))
                .retrieve()
                .bodyToMono(AnimeResponse.class)
                .map(AnimeResponse::getData)
                .block();
    }

    public AnimeListResponse searchAnimeByTitle(String query, int page) {
        return webClient.get()
                .uri("/anime?q=" + query + "&page=" + page)
                .retrieve()
                .bodyToMono(AnimeListResponse.class)
                .block();
    }

    public List<Anime> fetchBasicListOfAnime(String uri) {
        return webClient.get()
                .uri(uri + "?limit=15")
                .retrieve()
                .bodyToMono(AnimeListResponse.class)
                .map(AnimeListResponse::getData)
                .block();
    }

    public List<Anime> getTopAnime() {
        List<Anime> animeList = fetchBasicListOfAnime("/top/anime");
        animeList.sort(Comparator.comparingDouble(Anime::getScore).reversed());

        return animeList;
    }

    public List<Anime> getTopAiringAnime() {
        List<Anime> animeList = fetchBasicListOfAnime("/seasons/now");
        animeList.sort(Comparator.comparingDouble(Anime::getScore).reversed());

        return animeList;
    }

    public List<Anime> getTopUpcomingAnime() {
        List<Anime> animeList = fetchBasicListOfAnime("/seasons/upcoming");
        List<Anime> uniqueAnimeList = new ArrayList<>(new HashSet<>(animeList));
        uniqueAnimeList.sort(Comparator.comparingDouble(Anime::getPopularity));

        return uniqueAnimeList;
    }
}
