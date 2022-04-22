package com.example.moviezenapp.ui;

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

import com.example.moviezenapp.R;
import com.example.moviezenapp.adapters.FavoriteListAdapter;
import com.example.moviezenapp.adapters.OnMovieClickListener;
import com.example.moviezenapp.models.FavoriteList;
import com.example.moviezenapp.viewmodels.FavoriteViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements OnMovieClickListener {

    private FavoriteViewModel favoriteViewModel;
    RecyclerView movieList;
    private FavoriteListAdapter favoriteMoviesAdapter;
    List<FavoriteList> moviesList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        movieList = view.findViewById(R.id.recyclerView);
        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);

        favoriteMoviesAdapter = new FavoriteListAdapter(moviesList, this);


        return view;
    }

    private void ConfigureRecyclerView() {

        favoriteMoviesAdapter = new FavoriteListAdapter(moviesList, this);
        movieList.setAdapter(favoriteMoviesAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2);

        movieList.setLayoutManager(gridLayoutManager);
        movieList.setHasFixedSize(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        moviesList = new ArrayList<>();
        favoriteViewModel.loadFavoriteList().observe(getViewLifecycleOwner(), movies -> {
            moviesList.addAll(movies);
            favoriteMoviesAdapter = new FavoriteListAdapter(moviesList, this);
            movieList.setAdapter(favoriteMoviesAdapter);

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2);

            movieList.setLayoutManager(gridLayoutManager);
            movieList.setHasFixedSize(true);

        });
//        favoriteMoviesAdapter.notifyDataSetChanged();
    }


    @Override
    public void onMovieClick(int position) {
        Context context = getContext();
        Class destination = MovieDetails.class;

        Intent intent = new Intent(context, destination);
        intent.putExtra("favoriteList", favoriteMoviesAdapter.getSelectedMovie(position));
        startActivity(intent);
    }

}