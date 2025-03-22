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

    public AnimeResponse getAnimeById() {
        return webClient.get()
                .uri("/anime/1/full")
                .retrieve()
                .bodyToMono(AnimeResponse.class)
                .block();
    }
}
