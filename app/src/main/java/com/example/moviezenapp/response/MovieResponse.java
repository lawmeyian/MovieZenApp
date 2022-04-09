package com.example.moviezenapp.response;

import com.example.moviezenapp.models.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// this class is for single movie request
public class MovieResponse {

    // 1. Find the movie object
    @SerializedName("results")
    @Expose
    private Movie movie;

    public Movie getMovie()
    {
        return movie;
    }

    @Override
    public String toString() {
        return "MovieResponse{" +
                "movie=" + movie +
                '}';
    }
}
