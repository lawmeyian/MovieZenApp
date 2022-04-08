package com.example.moviezenapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;

import com.example.moviezenapp.adapters.MovieRecyclerView;
import com.example.moviezenapp.adapters.OnMovieListener;
import com.example.moviezenapp.models.MovieModel;
import com.example.moviezenapp.request.ServiceGenerator;
import com.example.moviezenapp.response.MovieSearchResponse;
import com.example.moviezenapp.utils.Credentials;
import com.example.moviezenapp.viewmodels.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment implements OnMovieListener {
    RecyclerView movieList;
    private MovieRecyclerView movieRecyclerAdapter;
    SearchView searchMovie;
    private MovieViewModel movieViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        searchMovie = view.findViewById(R.id.search_movie);
        movieList = view.findViewById(R.id.recyclerView);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        movieRecyclerAdapter = new MovieRecyclerView(this);
        // Call the observers

        ObserveAnyChange();
//        searchMovieApi("Spider", 1);
        SetUpSearchView();

        ConfigureRecyclerView();

        return view;
    }


    // observing data changes
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

    // Calling method in main activity
    private void searchMovieApi(String query, int pageNumber) {
        movieViewModel.searchMovieApi(query, pageNumber);
    }

    private void SetUpSearchView() {

        searchMovie.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                movieViewModel.searchMovieApi(
                        s, 1
                );
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
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
        Context context = getContext();
        Class destination = MovieDetails.class;


        Intent intent = new Intent(context, destination);
        intent.putExtra("movie", movieRecyclerAdapter.getSelectedMovie(position));
        startActivity(intent);
    }

}