package com.nguyenkenny.anilog.jikan;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class Anime {

    @JsonProperty("mal_id")
    private int malId;
    private Image images;
    private Trailer trailer;
    @JsonProperty("title")
    private String title;
    @JsonProperty("title_english")
    private String titleEnglish;
    @JsonProperty("title_japanese")
    private String titleJapanese;
    private String type;
    private String source;
    private int episodes;
    private String status;
    private boolean airing;
    private Aired aired;
    private String duration;
    private String rating;
    private double score;
    private int rank;
    private int popularity;
    private String synopsis;
    private String season;
    private int year;
    private List<Studio> studios;
    private List<Genre> genres;
    @JsonProperty("themes")
    private List<Theme> themes;
    @JsonProperty("theme")
    private Song songs;
    private List<External> external;
    private List<Streaming> streaming;
    private List<Relation> relations;

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Anime anime = (Anime) object;
        return malId == anime.malId && episodes == anime.episodes && airing == anime.airing &&
                Double.compare(score, anime.score) == 0 && rank == anime.rank && popularity == anime.popularity &&
                year == anime.year && Objects.equals(title, anime.title) &&
                Objects.equals(titleEnglish, anime.titleEnglish) &&
                Objects.equals(titleJapanese, anime.titleJapanese) && Objects.equals(type, anime.type) &&
                Objects.equals(source, anime.source) && Objects.equals(status, anime.status) &&
                Objects.equals(rating, anime.rating) && Objects.equals(season, anime.season);
    }

    @Override
    public int hashCode() {
        return Objects.hash(malId, title, titleEnglish, titleJapanese, type, source, episodes, status, airing, rating,
                score, rank, popularity, season, year);
    }

    @Getter
    @Setter
    public static class Image {
        private Jpg jpg;
        private Webp webp;
    }

    @Getter
    @Setter
    public static class Jpg {
        @JsonProperty("image_url")
        private String imageUrl;
        @JsonProperty("small_image_url")
        private String smallImageUrl;
        @JsonProperty("large_image_url")
        private String largeImageUrl;
    }

    @Getter
    @Setter
    public static class Webp {
        @JsonProperty("image_url")
        private String imageUrl;
        @JsonProperty("small_image_url")
        private String smallImageUrl;
        @JsonProperty("large_image_url")
        private String largeImageUrl;
    }

    @Getter
    @Setter
    public static class Trailer {
        @JsonProperty("youtube_id")
        private String youtubeId;
        private String url;
        @JsonProperty("embed_url")
        private String embedUrl;
    }

    @Getter
    @Setter
    public static class Aired {
        @JsonProperty("string")
        private String airedDates;
    }

    @Getter
    @Setter
    public static class Studio {
        private String name;
    }

    @Getter
    @Setter
    public static class Genre {
        private String name;
    }

    @Getter
    @Setter
    public static class Theme {
        private String name;
    }

    @Getter
    @Setter
    public static class Song {
        private List<String> openings;
        private List<String> endings;
    }

    @Getter
    @Setter
    public static class External {
        private String name;
        private String url;
    }

    @Getter
    @Setter
    public static class Streaming {
        private String name;
        private String url;
    }

    @Getter
    @Setter
    public static class Relation {
        private String relation;
        @JsonProperty("entry")
        private List<Entry> entries;
    }

    @Getter
    @Setter
    public static class Entry {
        @JsonProperty("mal_id")
        public int malId;
        public String type;
        public String name;
    }
}
