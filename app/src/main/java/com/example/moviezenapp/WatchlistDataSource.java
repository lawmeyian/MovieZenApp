package com.example.moviezenapp;

import androidx.lifecycle.LiveData;

import com.example.moviezenapp.dao.WatchlistDao;
import com.example.moviezenapp.models.Watchlist;
import com.example.moviezenapp.repositories.MovieRepository;

import java.util.List;

public class WatchlistDataSource implements IWatchlistDataSource {
    private WatchlistDao watchlistDao;
    private static WatchlistDataSource instance;

    public WatchlistDataSource(WatchlistDao watchlistDao) {
        this.watchlistDao = watchlistDao;
    }

    public static WatchlistDataSource getInstance(WatchlistDao watchlistDao) {
        if (instance == null) {
            instance = new WatchlistDataSource(watchlistDao);
        }
        return instance;
    }

    @Override
    public LiveData<List<Watchlist>> getAll() {
        return watchlistDao.getAll();
    }

    @Override
    public int isWatchlist(int itemId) {
        return watchlistDao.isWatchlist(itemId);
    }

    @Override
    public void delete(Watchlist watchlist) {
        watchlistDao.delete(watchlist);
    }

    @Override
    public void insertWatchlistItem(Watchlist... watchlists) {
        watchlistDao.insertWatchlistItem(watchlists);
    }
}
