package com.example.moviezenapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.moviezenapp.models.FavoriteList;
import com.example.moviezenapp.models.Watchlist;

import java.util.List;
@Dao
public interface FavoriteDao {
    @Query("SELECT * FROM FavoriteList")
    LiveData<List<FavoriteList>> getAll();

    @Query("SELECT EXISTS (SELECT 1 FROM FavoriteList WHERE id=:itemId)")
    int isFavoriteList(int itemId);

    @Query("SELECT * FROM FavoriteList WHERE id = :movieId")
    LiveData<List<FavoriteList>>getMoviesFromFavoriteList(int movieId);

    @Delete
    void delete(FavoriteList favoriteList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavoriteListItem(FavoriteList...favoriteLists);


}
