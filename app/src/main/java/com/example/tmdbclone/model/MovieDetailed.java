package com.example.tmdbclone.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieDetailed implements Serializable {
    private String title;
    //    private String author;
    private int id;
    private String overview;
    private String poster_path;
    private String backdrop_path;
    private List<Genres> genres;
    private String release_date;
    private double vote_average;







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
    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }




    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRelease_date() {
        return release_date;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }
}
