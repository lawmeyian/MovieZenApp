package com.example.moviezenapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moviezenapp.database.MoviesDb;
import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.models.Watchlist;
import com.example.moviezenapp.repositories.WatchlistRepository;

import java.util.List;

public class WatchlistViewModel extends AndroidViewModel {

    private final WatchlistRepository watchlistRepository;

    public WatchlistViewModel(@NonNull Application application) {
        super(application);
        watchlistRepository = WatchlistRepository.getInstance(application);
    }

    public LiveData<List<Watchlist>> loadWatchList() {
        return watchlistRepository.getAll();
    }}
