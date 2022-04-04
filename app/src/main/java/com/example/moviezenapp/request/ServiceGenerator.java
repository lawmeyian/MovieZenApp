package com.example.moviezenapp.request;

import com.example.moviezenapp.MovieApi;
import com.example.moviezenapp.utils.Credentials;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
//Singleton pattern
//    private static  Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
//                    .baseUrl(Credentials.BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create());
//
//    private static Retrofit retrofit = retrofitBuilder.build();
//
//    private static MovieApi movieApi = retrofit.create(MovieApi.class);

    private static MovieApi movieApi;

    public static MovieApi getMovieApi() {
        if (movieApi == null) {
            movieApi = new Retrofit.Builder()
                    .baseUrl(Credentials.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MovieApi.class);
        }
        return movieApi;
    }

}
