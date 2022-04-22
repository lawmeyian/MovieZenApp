package com.example.moviezenapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moviezenapp.dao.FavoriteDao;
import com.example.moviezenapp.dao.WatchlistDao;
import com.example.moviezenapp.database.MoviesDb;
import com.example.moviezenapp.models.FavoriteList;
import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.models.Watchlist;
import com.example.moviezenapp.repositories.FavoriteListRepository;
import com.example.moviezenapp.repositories.WatchlistRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MovieDetailsViewModel extends AndroidViewModel {
    private final FavoriteListRepository favoriteListRepository;
    private final WatchlistRepository watchlistRepository;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        favoriteListRepository = FavoriteListRepository.getInstance(application);
        watchlistRepository = WatchlistRepository.getInstance(application);

    }

//    public void addMovie(Movie movie) {
//        executorService.execute(() -> moviesDb.movieDao().add(movie));
//    }


    public LiveData<List<Watchlist>> getAllWatchlistMovies() {
        return watchlistRepository.getAll();
    }

    public LiveData<List<FavoriteList>> getMovieFromFavorites(int movieId) {
        return favoriteListRepository.getMoviesFromFavoriteList(movieId);
    }

    public LiveData<List<Watchlist>> getMovieFromWatchlist(int movieId) {
        return watchlistRepository.getMoviesFromWatchlist(movieId);
    }


    public void removeFromFavorites(FavoriteList movie) {
        favoriteListRepository.delete(movie);
    }

    public void removeFromWatchlist(Watchlist movie) {
        watchlistRepository.delete(movie);
    }

    public void insertWatchlistItem(Watchlist... watchlists) {
        watchlistRepository.insertWatchlistItem(watchlists);
    }

    public void insertFavoriteListItem(FavoriteList... favoriteLists) {

        favoriteListRepository.insertFavoriteListItem(favoriteLists);
    }
}
