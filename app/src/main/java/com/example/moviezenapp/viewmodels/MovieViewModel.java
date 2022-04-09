package com.example.moviezenapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.repositories.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private MovieRepository repository;

    public MovieViewModel() {
        repository = MovieRepository.getInstance();
    }

    public LiveData<List<Movie>> getMovies() {
        return repository.getMovies();
    }

    public LiveData<List<Movie>> getMoviesPopular() {
        return repository.getPopularMovies();
    }

    public void searchMovieApi(String query, int pageNumber) {
        repository.searchMovieApi(query, pageNumber);
    }


    public void searchMovieApiPopular(int pageNumber) {
        repository.searchPopularMovie(pageNumber);
    }

    public void searchNextPage() {
        repository.searchNextPage();
    }
}
