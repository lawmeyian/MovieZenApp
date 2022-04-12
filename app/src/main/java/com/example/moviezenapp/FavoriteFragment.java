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

import com.example.moviezenapp.adapters.FavoriteMoviesAdapter;
import com.example.moviezenapp.adapters.FavoriteMoviesListener;
import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.ui.MovieDetails;
import com.example.moviezenapp.viewmodels.WatchlistViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements FavoriteMoviesListener {
    private WatchlistViewModel watchlistViewModel;
    RecyclerView movieList;
    private FavoriteMoviesAdapter watchlistRecyclerAdapter;
   List<Movie> moviesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_favorite, container, false);
        movieList = view.findViewById(R.id.recyclerView);
        watchlistViewModel = new ViewModelProvider(this).get(WatchlistViewModel.class);
moviesList = new ArrayList<>();
//        watchlistRecyclerAdapter = new WatchlistAdapter(moviesList,this);


        watchlistViewModel.loadWatchlist().observe(getViewLifecycleOwner(), movies -> {
//            if (!movies.isEmpty()) {
//                textView.setText("");
//                for (Movie m : movies) {
//                    textView.append(m.getTitle() + "\n");
//                }
//            } else {
//                textView.setText("Empty");
//            }
            moviesList.addAll(movies);
            watchlistRecyclerAdapter = new FavoriteMoviesAdapter(moviesList,this);
            movieList.setAdapter(watchlistRecyclerAdapter);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2);

            movieList.setLayoutManager(gridLayoutManager);
            movieList.setHasFixedSize(true);

        });

        return view;
    }

    private void ConfigureRecyclerView() {

        watchlistRecyclerAdapter = new FavoriteMoviesAdapter(moviesList,this);
        movieList.setAdapter(watchlistRecyclerAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2);

        movieList.setLayoutManager(gridLayoutManager);
        movieList.setHasFixedSize(true);
//        movieList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        //RecyclerView Pagination
        // Loading next page of api response

//        movieList.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
//                if (!movieList.canScrollVertically(1)) {
//                    // Here we display the next search results
//                    movieViewModel.searchNextPage();
//                }
//            }
//        });
    }


    @Override
    public void onMovieClick(int position) {
//        Toast.makeText(this.getContext(), "The Position" + position, Toast.LENGTH_SHORT).show();
        // We need id of movie in order to get all it's details
        Context context = getContext();
        Class destination = MovieDetails.class;


        Intent intent = new Intent(context, destination);
        intent.putExtra("movie", watchlistRecyclerAdapter.getSelectedMovie(position));
        startActivity(intent);
    }
}