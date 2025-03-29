package com.nguyenkenny.anilog.jikan;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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

    public int getMalId() {
        return malId;
    }

    public void setMalId(int malId) {
        this.malId = malId;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }

    public Trailer getTrailer() {
        return trailer;
    }

    public void setTrailer(Trailer trailer) {
        this.trailer = trailer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEnglish() {
        return titleEnglish;
    }

    public void setTitleEnglish(String titleEnglish) {
        this.titleEnglish = titleEnglish;
    }

    public String getTitleJapanese() {
        return titleJapanese;
    }

    public void setTitleJapanese(String titleJapanese) {
        this.titleJapanese = titleJapanese;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isAiring() {
        return airing;
    }

    public void setAiring(boolean airing) {
        this.airing = airing;
    }

    public Aired getAired() {
        return aired;
    }

    public void setAired(Aired aired) {
        this.aired = aired;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public List<Studio> getStudios() {
        return studios;
    }

    public void setStudios(List<Studio> studios) {
        this.studios = studios;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Theme> getThemes() {
        return themes;
    }

    public void setThemes(List<Theme> themes) {
        this.themes = themes;
    }

    public Song getSongs() {
        return songs;
    }

    public void setSongs(Song songs) {
        this.songs = songs;
    }

    public List<External> getExternal() {
        return external;
    }

    public void setExternal(List<External> external) {
        this.external = external;
    }

    public List<Streaming> getStreaming() {
        return streaming;
    }

    public void setStreaming(List<Streaming> streaming) {
        this.streaming = streaming;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public static class Image {
        private Jpg jpg;
        private Webp webp;

        public Jpg getJpg() {
            return jpg;
        }

        public void setJpg(Jpg jpg) {
            this.jpg = jpg;
        }

        public Webp getWebp() {
            return webp;
        }

        public void setWebp(Webp webp) {
            this.webp = webp;
        }
    }

    public static class Jpg {
        @JsonProperty("image_url")
        private String imageUrl;
        @JsonProperty("small_image_url")
        private String smallImageUrl;
        @JsonProperty("large_image_url")
        private String largeImageUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getSmallImageUrl() {
            return smallImageUrl;
        }

        public void setSmallImageUrl(String smallImageUrl) {
            this.smallImageUrl = smallImageUrl;
        }

        public String getLargeImageUrl() {
            return largeImageUrl;
        }

        public void setLargeImageUrl(String largeImageUrl) {
            this.largeImageUrl = largeImageUrl;
        }
    }

    public static class Webp {
        @JsonProperty("image_url")
        private String imageUrl;
        @JsonProperty("small_image_url")
        private String smallImageUrl;
        @JsonProperty("large_image_url")
        private String largeImageUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getSmallImageUrl() {
            return smallImageUrl;
        }

        public void setSmallImageUrl(String smallImageUrl) {
            this.smallImageUrl = smallImageUrl;
        }

        public String getLargeImageUrl() {
            return largeImageUrl;
        }

        public void setLargeImageUrl(String largeImageUrl) {
            this.largeImageUrl = largeImageUrl;
        }
    }

    public static class Trailer {
        @JsonProperty("youtube_id")
        private String youtubeId;
        private String url;
        @JsonProperty("embed_url")
        private String embedUrl;

        public String getYoutubeId() {
            return youtubeId;
        }

        public void setYoutubeId(String youtubeId) {
            this.youtubeId = youtubeId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getEmbedUrl() {
            return embedUrl;
        }

        public void setEmbedUrl(String embedUrl) {
            this.embedUrl = embedUrl;
        }
    }

    public static class Aired {
        @JsonProperty("string")
        private String airedDates;

        public String getAiredDates() {
            return airedDates;
        }

        public void setAiredDates(String airedDates) {
            this.airedDates = airedDates;
        }
    }

    public static class Studio {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Genre {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Theme {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Song {
        private List<String> openings;
        private List<String> endings;

        public List<String> getOpenings() {
            return openings;
        }

        public void setOpenings(List<String> openings) {
            this.openings = openings;
        }

        public List<String> getEndings() {
            return endings;
        }

        public void setEndings(List<String> endings) {
            this.endings = endings;
        }
    }

    public static class External {
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class Streaming {
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class Relation {
        private String relation;
        @JsonProperty("entry")
        private List<Entry> entries;

        public String getRelation() {
            return relation;
        }

        public void setRelation(String relation) {
            this.relation = relation;
        }

        public List<Entry> getEntries() {
            return entries;
        }

        public void setEntries(List<Entry> entries) {
            this.entries = entries;
        }
    }

    public static class Entry {
        @JsonProperty("mal_id")
        public int malId;
        public String type;
        public String name;

        public int getMalId() {
            return malId;
        }

        public void setMalId(int malId) {
            this.malId = malId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
