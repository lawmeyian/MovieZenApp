package com.example.moviezenapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.moviezenapp.models.Watchlist;

import java.util.List;

@Dao
public interface WatchlistDao {
    @Query("SELECT * FROM Watchlist")
    LiveData<List<Watchlist>> getAll();

    @Query("SELECT EXISTS (SELECT 1 FROM Watchlist WHERE id=:itemId)")
    int isWatchlist(int itemId);

    @Delete
    void delete(Watchlist watchlist);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWatchlistItem(Watchlist...watchlists);


}
