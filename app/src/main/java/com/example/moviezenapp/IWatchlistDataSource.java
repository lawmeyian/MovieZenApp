package com.example.moviezenapp;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.moviezenapp.models.Watchlist;

import java.util.List;

public interface IWatchlistDataSource {

    LiveData<List<Watchlist>> getAll();

    int isWatchlist(int itemId);


    void delete(Watchlist watchlist);
    void insertWatchlistItem(Watchlist...watchlists);
}
