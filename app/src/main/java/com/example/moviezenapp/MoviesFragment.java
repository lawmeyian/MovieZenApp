package com.example.moviezenapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.moviezenapp.adapters.MovieRecyclerView;
import com.example.moviezenapp.adapters.OnMovieListener;
import com.example.moviezenapp.models.MovieModel;
import com.example.moviezenapp.viewmodels.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment implements OnMovieListener {
    RecyclerView movieList;
    private MovieRecyclerView movieRecyclerAdapter;

    // View model
    private MovieViewModel movieViewModel;

    boolean isPopular = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        movieList = view.findViewById(R.id.recyclerView);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        movieRecyclerAdapter = new MovieRecyclerView(this);

        ConfigureRecyclerView();
        ObserveAnyChange();
        ObservePopularMovies();
        movieViewModel.searchMovieApiPopular(1);
        return view;
    }

    private void ObservePopularMovies() {
        movieViewModel.getMoviesPopular().observe(getViewLifecycleOwner(), new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // observing for any data change
                if (movieModels != null) {
                    for (MovieModel movieModel : movieModels) {
                        // Get the data in log
                        Log.v("Tag", "onChanged " + movieModel.getTitle());
                        movieRecyclerAdapter.setMovies(movieModels);
                    }
                }
            }
        });
    }

    private void ObserveAnyChange() {
        movieViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                // observing for any data change
                if (movieModels != null) {
                    for (MovieModel movieModel : movieModels) {
                        // Get the data in log
                        Log.v("Tag", "onChanged " + movieModel.getTitle());
                        movieRecyclerAdapter.setMovies(movieModels);
                    }
                }
            }
        });
    }

    private void ConfigureRecyclerView() {

        movieRecyclerAdapter = new MovieRecyclerView(this);
        movieList.setAdapter(movieRecyclerAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2);

        movieList.setLayoutManager(gridLayoutManager);
        movieList.setHasFixedSize(true);
//        movieList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        //RecyclerView Pagination
        // Loading next page of api response

        movieList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if (!movieList.canScrollVertically(1)) {
                    // Here we display the next search results
                    movieViewModel.searchNextPage();
                }
            }
        });
    }


    @Override
    public void onMovieClick(int position) {
//        Toast.makeText(this.getContext(), "The Position" + position, Toast.LENGTH_SHORT).show();
        // We need id of movie in order to get all it's details
        Context context = getContext();
        Class destination = MovieDetails.class;


        Intent intent = new Intent(context, destination);
        intent.putExtra("movie", movieRecyclerAdapter.getSelectedMovie(position));
        startActivity(intent);
    }
}