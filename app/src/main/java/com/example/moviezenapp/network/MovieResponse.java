package com.example.moviezenapp.network;

import com.example.moviezenapp.models.Movie;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    @Expose()
    private List<Movie> movies;


    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public String toString() {
        return "MovieSearchResponse{" +
                ", movies=" + movies +
                '}';
    }
}
