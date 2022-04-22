package com.example.moviezenapp.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.moviezenapp.dao.FavoriteDao;
import com.example.moviezenapp.database.MoviesDb;
import com.example.moviezenapp.models.FavoriteList;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoriteListRepository {
    private FavoriteDao favoriteDao;
    private final ExecutorService executorService;
    private static FavoriteListRepository instance;

    public FavoriteListRepository(Application application) {
        MoviesDb database = MoviesDb.getInstance(application);
        favoriteDao = database.favoriteDao();
        executorService = Executors.newFixedThreadPool(2);
    }

    public static FavoriteListRepository getInstance(Application application)
    {
        if (instance == null) {
            instance = new FavoriteListRepository(application);
        }
        return instance;
    }

    public LiveData<List<FavoriteList>> getAll() {
        return favoriteDao.getAll();
    }


    public int isFavoriteList(int itemId) {
        return favoriteDao.isFavoriteList(itemId);
    }

    public LiveData<List<FavoriteList>>getMoviesFromFavoriteList(int movieId)
    {
        return favoriteDao.getMoviesFromFavoriteList(movieId);
    }

    public void delete(FavoriteList favoriteList) {
        executorService.execute(() ->  favoriteDao.delete(favoriteList));
    }


    public void insertFavoriteListItem(FavoriteList... favoriteLists) {
        executorService.execute(() ->favoriteDao.insertFavoriteListItem(favoriteLists));
    }

}
