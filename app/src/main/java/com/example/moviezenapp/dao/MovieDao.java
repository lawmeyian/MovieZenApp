package com.example.moviezenapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.moviezenapp.models.Movie;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.Disposable;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movies ")
    LiveData<List<Movie>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(Movie movie);

    @Delete
    void delete(Movie movie);

    @Query("SELECT * FROM movies WHERE id = :movieId")
    LiveData<List<Movie>>getMoviesFromFavorites(int movieId);
}
