package com.example.moviezenapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moviezenapp.database.MoviesDb;
import com.example.moviezenapp.models.FavoriteList;
import com.example.moviezenapp.models.Movie;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {

    private MoviesDb moviesDb;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        moviesDb = MoviesDb.getInstance(application);
    }

    public LiveData<List<FavoriteList>> loadFavoriteList() {
        return moviesDb.favoriteDao().getAll();
    }

}
