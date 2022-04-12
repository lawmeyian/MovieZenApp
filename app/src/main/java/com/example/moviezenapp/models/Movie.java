package com.example.moviezenapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "movies")

public class Movie implements Serializable {

    private String title;
    private String poster_path;
    private String release_date;
    @PrimaryKey
    private int id;
    private float vote_average;
    private float vote_count;
    private String overview;
    private String original_language;


    public Movie(String title, String poster_path, String release_date, int id, float vote_average, float vote_count, String overview, String original_language) {
        this.title = title;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.id = id;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.overview = overview;
        this.original_language = original_language;
    }

    public String getTitle() {
        return title;
    }


    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public int getId() {
        return id;
    }

    public float getVote_average() {
        return vote_average;
    }

    public float getVote_count() {
        return vote_count;
    }

    public String getOverview() {
        return overview;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setMovie_id(int id) {
        this.id = id;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public void setVote_count(float vote_count) {
        this.vote_count = vote_count;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }
}
