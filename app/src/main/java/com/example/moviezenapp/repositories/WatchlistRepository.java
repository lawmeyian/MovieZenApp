package com.example.moviezenapp.repositories;

import androidx.lifecycle.LiveData;

import com.example.moviezenapp.IWatchlistDataSource;
import com.example.moviezenapp.models.Watchlist;

import java.util.List;

public class WatchlistRepository implements IWatchlistDataSource {

    private IWatchlistDataSource watchlistDataSource;

    public WatchlistRepository(IWatchlistDataSource watchlistDataSource) {
        this.watchlistDataSource = watchlistDataSource;
    }

    private static WatchlistRepository instance;

    public static WatchlistRepository getInstance(IWatchlistDataSource watchlistDataSource) {
        if (instance == null) {
            instance = new WatchlistRepository(watchlistDataSource);
        }
        return instance;
    }

    @Override
    public LiveData<List<Watchlist>> getAll() {
        return watchlistDataSource.getAll();
    }

    @Override
    public int isWatchlist(int itemId) {
        return watchlistDataSource.isWatchlist(itemId);
    }

    @Override
    public void delete(Watchlist watchlist) {
        watchlistDataSource.delete(watchlist);
    }

    @Override
    public void insertWatchlistItem(Watchlist... watchlists) {
        watchlistDataSource.insertWatchlistItem(watchlists);
    }
}
