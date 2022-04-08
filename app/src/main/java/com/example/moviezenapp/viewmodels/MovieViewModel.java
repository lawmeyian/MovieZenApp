package com.example.moviezenapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviezenapp.models.MovieModel;
import com.example.moviezenapp.repositories.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private MovieRepository repository;

    public MovieViewModel() {
        repository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return repository.getMovies();
    }

    public LiveData<List<MovieModel>> getMoviesPopular() {
        return repository.getMoviesPopular();
    }

    public void searchMovieApi(String query, int pageNumber) {
        repository.searchMovieApi(query, pageNumber);
    }


    public void searchMovieApiPopular(int pageNumber) {
        repository.searchMoviePopular(pageNumber);
    }

    public void searchNextPage() {
        repository.searchNextPage();
    }
}
