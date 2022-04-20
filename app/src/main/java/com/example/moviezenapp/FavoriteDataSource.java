package com.example.moviezenapp;

import androidx.lifecycle.LiveData;

import com.example.moviezenapp.dao.FavoriteDao;
import com.example.moviezenapp.dao.WatchlistDao;
import com.example.moviezenapp.models.FavoriteList;
import com.example.moviezenapp.models.Watchlist;

import java.util.List;

public class FavoriteDataSource implements IFavoriteListDataSource {
    private FavoriteDao favoriteDao;
    private static FavoriteDataSource instance;

    public FavoriteDataSource(FavoriteDao favoriteDao) {
        this.favoriteDao = favoriteDao;
    }

    public static FavoriteDataSource getInstance(FavoriteDao favoriteDao) {
        if (instance == null) {
            instance = new FavoriteDataSource(favoriteDao);
        }
        return instance;
    }

    @Override
    public LiveData<List<FavoriteList>> getAll() {
        return favoriteDao.getAll();
    }

    @Override
    public int isFavoriteList(int itemId) {
        return favoriteDao.isFavoriteList(itemId);
    }

    @Override
    public void delete(FavoriteList favoriteList) {
        favoriteDao.delete(favoriteList);
    }

    @Override
    public void insertFavoriteListItem(FavoriteList... favoriteLists) {
        favoriteDao.insertFavoriteListItem(favoriteLists);
    }
}
