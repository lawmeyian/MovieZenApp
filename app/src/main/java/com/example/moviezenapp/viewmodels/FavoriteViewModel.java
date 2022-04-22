package com.example.moviezenapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moviezenapp.database.MoviesDb;
import com.example.moviezenapp.models.FavoriteList;
import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.repositories.FavoriteListRepository;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {

private final FavoriteListRepository favoriteListRepository;

    public FavoriteViewModel(@NonNull Application application) {
        super(application);
        favoriteListRepository = FavoriteListRepository.getInstance(application);
    }

    public LiveData<List<FavoriteList>> loadFavoriteList() {
        return favoriteListRepository.getAll();
    }

}
