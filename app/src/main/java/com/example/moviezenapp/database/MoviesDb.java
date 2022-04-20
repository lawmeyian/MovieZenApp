package com.example.moviezenapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviezenapp.dao.FavoriteDao;
import com.example.moviezenapp.dao.WatchlistDao;
import com.example.moviezenapp.models.FavoriteList;
import com.example.moviezenapp.models.Watchlist;

@Database(entities = {Watchlist.class, FavoriteList.class}, version = 21)
public abstract class MoviesDb extends RoomDatabase {
    private static MoviesDb instance;

    public abstract WatchlistDao watchlistDao();
    public abstract FavoriteDao favoriteDao();


    public static synchronized MoviesDb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context,
                    MoviesDb.class, "movie_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
