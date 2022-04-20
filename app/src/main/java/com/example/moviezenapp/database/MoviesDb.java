package com.example.moviezenapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviezenapp.dao.MovieDao;
import com.example.moviezenapp.dao.WatchlistDao;
import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.models.Watchlist;

@Database(entities = {Movie.class, Watchlist.class}, version = 14)
public abstract class MoviesDb extends RoomDatabase {
    private static MoviesDb instance;

    public abstract MovieDao movieDao();
    public abstract WatchlistDao watchlistDao();


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
