package com.example.moviezenapp.viewmodels;

import androidx.lifecycle.ViewModel;

import com.example.moviezenapp.models.MovieList;

public class ListsViewModel extends ViewModel {

    private static ListsViewModel instance;
    //    private MovieData repository;
    private MovieList list;

    private ListsViewModel() {
//        repository = MovieData.getInstance();
    }

    public static synchronized ListsViewModel getInstance() {
        if (instance == null) {
            instance = new ListsViewModel();
        }
        return instance;
    }

    public MovieList getListOfMovies() {
        return list;
    }

    public void setListOfMovies(MovieList list) {
        this.list = list;
    }
}
