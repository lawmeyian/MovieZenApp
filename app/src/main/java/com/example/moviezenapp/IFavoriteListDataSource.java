package com.example.moviezenapp;

import androidx.lifecycle.LiveData;

import com.example.moviezenapp.models.FavoriteList;
import com.example.moviezenapp.models.Watchlist;

import java.util.List;

public interface IFavoriteListDataSource {
    LiveData<List<FavoriteList>> getAll();

    int isFavoriteList(int itemId);


    void delete(FavoriteList favoriteList);
    void insertFavoriteListItem(FavoriteList...favoriteLists);
}
