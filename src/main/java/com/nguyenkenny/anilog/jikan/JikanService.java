package com.nguyenkenny.anilog.jikan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

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
}
