package com.example.moviezenapp.network;

import com.example.moviezenapp.network.MovieApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static MovieApi movieApi;

    public static MovieApi getMovieApi() {
        if (movieApi == null) {
            movieApi = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MovieApi.class);
        }
        return movieApi;
    }

}
