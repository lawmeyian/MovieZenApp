package com.example.moviezenapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MoviesFragment extends Fragment {
    RecyclerView movieList;
    MovieAdapter movieAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        movieList = view.findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2);

        movieList.setLayoutManager(gridLayoutManager);
        movieList.setHasFixedSize(true);
        ArrayList<Movie> movies = new ArrayList<>();
        movieAdapter = new MovieAdapter(movies);
        movies.add(new Movie("A Single Man", R.drawable.a_single_man));
        movies.add(new Movie("The Imitation Game", R.drawable.the_imitation_game));
        movies.add(new Movie("The Power of the Dog", R.drawable.the_power_of_the_dog));
        movies.add(new Movie("Spring, Summer ...", R.drawable.spring_summer));
        movies.add(new Movie("The Power of the Dog", R.drawable.the_power_of_the_dog));
        movies.add(new Movie("Spring, Summer ...", R.drawable.spring_summer));


        movieList.setAdapter(movieAdapter);
        return view;
    }
}