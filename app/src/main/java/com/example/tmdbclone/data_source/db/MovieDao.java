package com.example.tmdbclone.data_source.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tmdbclone.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {

//    SELECT * FROM Customers
//    LIMIT 3
    @Query("SELECT * FROM Movie")
    List<Movie> getAll();

    @Insert
    void insert(Movie movie);

    @Query("DELETE FROM Movie")
    void delete();
}
