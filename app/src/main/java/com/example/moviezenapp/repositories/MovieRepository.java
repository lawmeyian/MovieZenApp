package com.example.moviezenapp.repositories;

import androidx.lifecycle.LiveData;

import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.request.MovieApiClient;

import java.util.List;

public class MovieRepository {
    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    private String query;
    private int pageNumber;

    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<Movie>> getMovies() {
        return movieApiClient.getMovies();
    }

    public LiveData<List<Movie>> getPopularMovies() {
        return movieApiClient.getPopularMovies();
    }


    public void searchMovieApi(String query, int pageNumber) {

        this.query = query;
        this.pageNumber = pageNumber;
        movieApiClient.searchMoviesApi(query, pageNumber);
    }

    public void searchPopularMovie(int pageNumber) {

        this.pageNumber = pageNumber;
        movieApiClient.searchMoviesApiPopular(pageNumber);
    }

    public void searchNextPage() {
        searchMovieApi(query, pageNumber + 1);
    }
}
