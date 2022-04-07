package com.example.moviezenapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

public class SearchFragment extends Fragment {
    Button btn;
    private MovieViewModel movieViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        btn = view.findViewById(R.id.button);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        // Call the observers
        ObserveAnyChange();


        // testing the method

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                GetRetrofitResponseAccordingToID();

                // Displaying only results of page 1
                searchMovieApi("the",1);
            }
        });

        return view;
    }

    // observing data changes
    private void ObserveAnyChange() {
        movieViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
          // observing for any data change
                if(movieModels != null){
                    for(MovieModel movieModel: movieModels){
                        // Get the data in log
                     Log.v("Tag", "onChanged " + movieModel.getTitle());
                    }
                }
            }
        });
    }

 // Calling method in main activity
    private void searchMovieApi(String query, int pageNumber)
    {
        movieViewModel.searchMovieApi(query,pageNumber);
    }



//    private void GetRetrofitResponse() {
//        MovieApi movieApi = ServiceGenerator.getMovieApi();
//        Call<MovieSearchResponse> responseCall = movieApi.searchMovie(
//                Credentials.API_KEY,
//                "The Power of the Dog",
//                1);
//
//        responseCall.enqueue(new Callback<MovieSearchResponse>() {
//            @Override
//            public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
//                if (response.code() == 200) {
//                    Log.v("Tag", "the response" + response.body().toString());
//                    List<MovieModel> movies = new ArrayList<>(response.body().getMovies());
//                    for (MovieModel movie : movies) {
//                        Log.v("Tag", "The title: " + movie.getTitle());
//                    }
//                } else {
//                    Log.v("Tag", "Error" + response.errorBody().toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
//
//            }
//        });
//    }
//
//    private void GetRetrofitResponseAccordingToID() {
//        MovieApi movieApi = ServiceGenerator.getMovieApi();
//
//        Call<MovieModel> responseCall = movieApi.getMovie(550, Credentials.API_KEY);
//
//        responseCall.enqueue(new Callback<MovieModel>() {
//            @Override
//            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
//                if (response.code() == 200) {
//                    MovieModel movie = response.body();
//                    Log.v("Tag", "The Response: " + movie.getTitle());
//                } else {
//                    try {
//                        Log.v("Tag", "Error: " + response.errorBody().toString());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MovieModel> call, Throwable t) {
//
//            }
//        });
//    }
}