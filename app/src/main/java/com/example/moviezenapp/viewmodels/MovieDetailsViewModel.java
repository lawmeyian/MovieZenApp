package com.example.moviezenapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moviezenapp.dao.MovieDao;
import com.example.moviezenapp.database.MoviesDb;
import com.example.moviezenapp.models.Movie;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.rxjava3.core.Flowable;

public class MovieDetailsViewModel extends AndroidViewModel {
    private MoviesDb moviesDb;
    private final LiveData<List<Movie>> allMovies;
    private final ExecutorService executorService;
    private final MovieDao movieDao;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        moviesDb = MoviesDb.getInstance(application);
        movieDao = moviesDb.movieDao();
        allMovies = movieDao.getAll();
        executorService = Executors.newFixedThreadPool(2);
    }

    public void addMovie(Movie movie) {
        executorService.execute(() -> moviesDb.movieDao().add(movie));
    }

    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }

    public LiveData<List<Movie>> getMovieFromFavorites(int movieId) {
        return moviesDb.movieDao().getMoviesFromFavorites(movieId);
    }

    public void removeFromFavorites(Movie movie)
    {
        executorService.execute(() -> moviesDb.movieDao().delete(movie));
    }
}
