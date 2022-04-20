package com.example.moviezenapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moviezenapp.database.MoviesDb;
import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.models.Watchlist;

import java.util.List;

public class WatchlistViewModel extends AndroidViewModel {

    private MoviesDb moviesDb;

    public WatchlistViewModel(@NonNull Application application) {
        super(application);
        moviesDb = MoviesDb.getInstance(application);
    }

    public LiveData<List<Watchlist>> loadWatchList() {
        return moviesDb.watchlistDao().getAll();
    }
}