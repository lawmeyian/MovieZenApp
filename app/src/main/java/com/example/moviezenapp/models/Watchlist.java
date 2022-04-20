package com.example.moviezenapp.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Watchlist")
public class Watchlist {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "movieId")
    private int movieId;

    @NonNull
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
