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

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MovieDetailsViewModel extends AndroidViewModel {
    private MoviesDb moviesDb;
    private final LiveData<List<Watchlist>> allWatchlistMovies;
    private final LiveData<List<FavoriteList>> allFavoriteMovies;
    private final ExecutorService executorService;
    private final WatchlistDao watchlistDao;
    private final FavoriteDao favoriteDao;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        moviesDb = MoviesDb.getInstance(application);

        watchlistDao = moviesDb.watchlistDao();
        favoriteDao = moviesDb.favoriteDao();
        allWatchlistMovies = watchlistDao.getAll();
        allFavoriteMovies = favoriteDao.getAll();
        executorService = Executors.newFixedThreadPool(2);
    }

//    public void addMovie(Movie movie) {
//        executorService.execute(() -> moviesDb.movieDao().add(movie));
//    }




    public LiveData<List<Watchlist>> getAllWatchlistMovies() {
        return allWatchlistMovies;
    }

    public LiveData<List<FavoriteList>> getMovieFromFavorites(int movieId) {
        return moviesDb.favoriteDao().getMoviesFromFavoriteList(movieId);
    }
    public LiveData<List<Watchlist>> getMovieFromWatchlist(int movieId) {
        return moviesDb.watchlistDao().getMoviesFromWatchlist(movieId);
    }



    public void removeFromFavorites(FavoriteList movie)
    {
        executorService.execute(() -> moviesDb.favoriteDao().delete(movie));
    }

    public void removeFromWatchlist(Watchlist movie)
    {
        executorService.execute(() -> moviesDb.watchlistDao().delete(movie));
    }

    public void insertWatchlistItem(Watchlist... watchlists) {
        executorService.execute(() -> moviesDb.watchlistDao().insertWatchlistItem(watchlists));
    }
    public void insertFavoriteListItem(FavoriteList... favoriteLists) {

        executorService.execute(() -> moviesDb.favoriteDao().insertFavoriteListItem(favoriteLists));
    }
}
