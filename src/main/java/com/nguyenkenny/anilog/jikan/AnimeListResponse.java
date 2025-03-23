package com.nguyenkenny.anilog.jikan;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AnimeListResponse {

    private List<Anime> data;
    private Pagination pagination;

    @Getter
    @Setter
    public static class Pagination {
        @JsonProperty("last_visible_page")
        private int lastVisiblePage;
        @JsonProperty("has_next_page")
        private boolean hasNextPage;
        @JsonProperty("current_page")
        private int currentPage;
        private Item items;
    }

    @Getter
    @Setter
    public static class Item {
        private int count;
        private int total;
        @JsonProperty("per_page")
        private int perPage;
    }
}
