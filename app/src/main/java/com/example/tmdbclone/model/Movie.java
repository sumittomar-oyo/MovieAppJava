package com.example.tmdbclone.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Movie {
    private String title;
    @PrimaryKey
    private int id;
    private String overview;
    private String poster_path;
    private String backdrop_path;

    public Movie(){
    }
    public Movie(int id, String title,String overview,String poster_path,String backdrop_path){
        this.id= id;
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
    }

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

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String urlToImage) {
        this.poster_path = urlToImage;
    }
}
