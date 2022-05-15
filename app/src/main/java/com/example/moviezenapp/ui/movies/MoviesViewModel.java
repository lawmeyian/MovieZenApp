package com.example.moviezenapp.ui.movies;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.models.MovieList;
import com.example.moviezenapp.repositories.MovieRepository;

import java.util.ArrayList;

public class MoviesViewModel extends ViewModel {

    private static MoviesViewModel instance;
    private MovieRepository repository;

    private MoviesViewModel() {
        repository = repository.getInstance();
    }

    public static synchronized MoviesViewModel getInstance() {
        if (instance == null) {
            instance = new MoviesViewModel();
        }
        return instance;
    }

    public void remove(String listId, String id) {
        repository.remove(listId, id);
    }

    public void saveMovie(String listId, Movie movieToSave) {
        repository.saveMovie(listId, movieToSave);
    }

    public LiveData<ArrayList<MovieList>> getAllListsFromDB() {
        return repository.getAllListsFromDB();
    }

    public void editMoviePersonalRating(String listId, String movieId, double rating) {
        repository.editMoviePersonalRating(listId, movieId, rating);
    }

    public void editMoviePersonalRatingKeyword(String listId, String movieId, String keyword) {
        repository.editMoviePersonalRatingKeyword(listId, movieId, keyword);
    }

}
