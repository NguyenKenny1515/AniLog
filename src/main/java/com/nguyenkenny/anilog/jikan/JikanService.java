package com.nguyenkenny.anilog.jikan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<Anime> searchAnimeByTitle(String query) {
        return webClient.get()
                .uri("/anime?q=" + query)
                .retrieve()
                .bodyToMono(AnimeListResponse.class)
                .map(AnimeListResponse::getData)
                .block();
    }
}
