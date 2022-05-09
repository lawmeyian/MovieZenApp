package com.example.moviezenapp.ui.movieDetails;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.models.MovieList;
import com.example.moviezenapp.repositories.MovieRepository;
import com.example.moviezenapp.repositories.UserRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailsViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private MovieRepository repository;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);

        userRepository = UserRepository.getInstance(application);
        repository = MovieRepository.getInstance();
        init();
    }

    public void init() {
        String userId = userRepository.getCurrentUser().getValue().getUid();
        repository.init(userId);
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return userRepository.getCurrentUser();
    }

    public void saveMovie(String listId, Movie movieToSave) {
        repository.saveMovie(listId, movieToSave);
    }
    public void remove(String listId, String id) {
        repository.remove(listId, id);
    }

    public LiveData<ArrayList<MovieList>> getAllListsFromDB() {
        return repository.getAllListsFromDB();
    }

}