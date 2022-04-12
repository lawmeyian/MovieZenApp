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

public class MovieDetailsViewModel extends AndroidViewModel {
    private MoviesDb moviesDb;
    private final LiveData<List<Movie>> allMovies;
    private final ExecutorService executorService;
private final MovieDao movieDao;
    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        moviesDb = MoviesDb.getInstance(application);
        movieDao = moviesDb.movieDao();
        allMovies = movieDao.getWatchlist();
        executorService = Executors.newFixedThreadPool(2);
    }

    public void addToWatchlist(Movie movie)
    {
        executorService.execute(() ->  moviesDb.movieDao().insert(movie));
    }

    public LiveData<List<Movie>> getAllMovies() {
        return allMovies;
    }
}
