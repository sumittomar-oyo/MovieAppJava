package com.example.tmdbclone.model;

import java.util.ArrayList;

public class Movie {
    private String title;
    private int id;
    private String overview;
    private String poster_path;
    private String backdrop_path;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
                this.overview = overview;
    }

    public String getPosterPath() {
        return poster_path;
    }

    public void setPosterPath(String urlToImage) {
        this.poster_path = urlToImage;
    }
}
