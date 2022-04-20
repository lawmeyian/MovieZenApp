package com.example.moviezenapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moviezenapp.adapters.MovieAdapter;
import com.example.moviezenapp.adapters.OnMovieClickListener;
import com.example.moviezenapp.adapters.WatchlistAdapter;
import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.models.Watchlist;
import com.example.moviezenapp.ui.MovieDetails;
import com.example.moviezenapp.viewmodels.WatchlistViewModel;

import java.util.ArrayList;
import java.util.List;


public class WatchlistFragment extends Fragment implements OnMovieClickListener {

    private WatchlistViewModel watchlistViewModel;
    RecyclerView movieList;
    private WatchlistAdapter watchlistMoviesAdapter;
    List<Watchlist> moviesList;


    public WatchlistFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_watchlist, container, false);
        movieList = view.findViewById(R.id.recyclerView);
        watchlistViewModel = new ViewModelProvider(this).get(WatchlistViewModel.class);
        moviesList = new ArrayList<>();
        watchlistMoviesAdapter = new WatchlistAdapter(moviesList, this);

        watchlistViewModel.loadWatchList().observe(getViewLifecycleOwner(), movies -> {
            moviesList.addAll(movies);
            watchlistMoviesAdapter = new WatchlistAdapter(moviesList, this);
            movieList.setAdapter(watchlistMoviesAdapter);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2);

            movieList.setLayoutManager(gridLayoutManager);
            movieList.setHasFixedSize(true);
        });
        return view;
    }


    @Override
    public void onMovieClick(int position) {
        Context context = getContext();
        Class destination = MovieDetails.class;

        Intent intent = new Intent(context, destination);
        intent.putExtra("watchlist", watchlistMoviesAdapter.getSelectedMovie(position));
        startActivity(intent);

    }
}