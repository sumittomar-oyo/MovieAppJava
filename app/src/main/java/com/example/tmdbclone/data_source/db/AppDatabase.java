package com.example.tmdbclone.data_source.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tmdbclone.model.Movie;

@Database(entities = {Movie.class} ,  version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
}
