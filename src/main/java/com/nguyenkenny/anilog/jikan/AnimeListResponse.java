package com.nguyenkenny.anilog.jikan;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class AnimeListResponse {

    private List<Anime> data;
    private Pagination pagination;

    public static class Pagination {
        @JsonProperty("last_visible_page")
        private int lastVisiblePage;
        @JsonProperty("has_next_page")
        private boolean hasNextPage;
        @JsonProperty("current_page")
        private int currentPage;
        private Item items;

        public int getLastVisiblePage() {
            return lastVisiblePage;
        }

        public void setLastVisiblePage(int lastVisiblePage) {
            this.lastVisiblePage = lastVisiblePage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public Item getItems() {
            return items;
        }

        public void setItems(Item items) {
            this.items = items;
        }
    }

    public static class Item {
        private int count;
        private int total;
        @JsonProperty("per_page")
        private int perPage;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPerPage() {
            return perPage;
        }

        public void setPerPage(int perPage) {
            this.perPage = perPage;
        }
    }

    public List<Anime> getData() {
        return data;
    }

    public void setData(List<Anime> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
