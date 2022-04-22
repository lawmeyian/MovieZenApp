package com.example.moviezenapp.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.moviezenapp.dao.WatchlistDao;
import com.example.moviezenapp.database.MoviesDb;
import com.example.moviezenapp.models.Watchlist;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WatchlistRepository {

    private WatchlistDao watchlistDao;
    private final ExecutorService executorService;
    private static WatchlistRepository instance;

    public WatchlistRepository(Application application) {
        MoviesDb database = MoviesDb.getInstance(application);
        watchlistDao = database.watchlistDao();
        executorService = Executors.newFixedThreadPool(2);

    }


    public static WatchlistRepository getInstance(Application application) {
        if (instance == null) {
            instance = new WatchlistRepository(application);
        }
        return instance;
    }


    public LiveData<List<Watchlist>> getAll() {
        return watchlistDao.getAll();
    }


    public int isWatchlist(int itemId) {
        return watchlistDao.isWatchlist(itemId);
    }
public     LiveData<List<Watchlist>>getMoviesFromWatchlist(int movieId)
{
    return watchlistDao.getMoviesFromWatchlist(movieId);
}

    public void delete(Watchlist watchlist) {
        executorService.execute(() -> watchlistDao.delete(watchlist));
    }


    public void insertWatchlistItem(Watchlist... watchlists) {
        executorService.execute(() -> watchlistDao.insertWatchlistItem(watchlists));
    }
}
