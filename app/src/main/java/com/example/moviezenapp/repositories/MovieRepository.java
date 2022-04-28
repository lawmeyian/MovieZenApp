package com.example.moviezenapp.repositories;

import androidx.lifecycle.LiveData;

import com.example.moviezenapp.MovieLiveData;
import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.models.MovieList;
import com.example.moviezenapp.network.MovieApiClient;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
    private static MovieRepository instance;
    private MovieLiveData movieDAO;

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

    public void init(String userId) {
        movieDAO = new MovieLiveData(userId);
    }

    public void remove(String listId, String id) {
        movieDAO.remove(listId, id);
    }

    public void saveMovie(String listId, Movie movieToSave) {
        movieDAO.saveMovie(listId, movieToSave);
    }

    public void editMoviePersonalRating(String listId, String movieId, double rating) {
        movieDAO.editMoviePersonalRating(listId, movieId, rating);
    }

    public LiveData<ArrayList<MovieList>> getAllListsFromDB() {
        return movieDAO.getAllListsFromDB();
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
