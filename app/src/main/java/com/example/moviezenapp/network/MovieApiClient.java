package com.example.moviezenapp.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviezenapp.models.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {

    //live data for search
    private MutableLiveData<List<Movie>> movies;

    private static MovieApiClient instance;

    // making Global RUNNABLE
    private RetrieveMoviesRunnable retrieveMoviesRunnable;

    // live data for popular movies
    private MutableLiveData<List<Movie>> popularMovies;

    // making popular RUNNABLE
    private RetrieveMoviesRunnablePopular retrieveMoviesRunnablePopular;

    public static MovieApiClient getInstance() {
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
        movies = new MutableLiveData<>();
        popularMovies = new MutableLiveData<>();
    }


    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public LiveData<List<Movie>> getPopularMovies() {
        return popularMovies;
    }

    public void searchMoviesApi(String query, int pageNumber) {
        if (retrieveMoviesRunnable != null) {
            retrieveMoviesRunnable = null;
        }
        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
// cancelling the retrofit call
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }

    public void searchMoviesApiPopular(int pageNumber) {
        if (retrieveMoviesRunnablePopular != null) {
            retrieveMoviesRunnablePopular = null;
        }
        retrieveMoviesRunnablePopular = new RetrieveMoviesRunnablePopular(pageNumber);

        final Future myHandler2 = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnablePopular);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
// cancelling the retrofit call
                myHandler2.cancel(true);
            }
        }, 1000, TimeUnit.MILLISECONDS);
    }

    // retrieving data from REST API by runnable class
    private class RetrieveMoviesRunnable implements Runnable {
        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(String query, int pageNumber) {
            this.query = query;
            this.pageNumber = pageNumber;
            this.cancelRequest = false;
        }

        @Override
        public void run() {

            // getting the response objects
            try {
                Response response = getMovies(query, pageNumber).execute();

                if (cancelRequest) {
                    return;
                }

                if (response.code() == 200) {
                    List<Movie> list = new ArrayList<>(((MovieResponse) response.body()).getMovies());
                    if (pageNumber == 1) {
                        // sending data live
                        // post value used for background thread
                        //setValue : not for background thread
                        movies.postValue(list);
                    } else {
                        List<Movie> currentMovies = movies.getValue();
                        currentMovies.addAll(list);
                        movies.postValue(currentMovies);

                    }
                } else {
                    String error = response.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    movies.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                movies.postValue(null);
            }

        }
        // search Method/query

        private Call<MovieResponse> getMovies(String query, int pageNumber) {
            return ServiceGenerator.getMovieApi().searchMovie(
                    "ca89b4fd28642ed9d9ef8611e1978832",
                    query,
                    pageNumber
            );

        }

        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }

    private class RetrieveMoviesRunnablePopular implements Runnable {
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnablePopular(int pageNumber) {
            this.pageNumber = pageNumber;
            this.cancelRequest = false;
        }

        @Override
        public void run() {

            // getting the response objects
            try {
                Response response2 = getMoviesPopular(pageNumber).execute();

                if (cancelRequest) {
                    return;
                }

                if (response2.code() == 200) {
                    List<Movie> list = new ArrayList<>(((MovieResponse) response2.body()).getMovies());
                    if (pageNumber == 1) {
                        // sending data live
                        // post value used for background thread
                        //setValue : not for background thread
                        popularMovies.postValue(list);
                    } else {
                        List<Movie> currentMovies = popularMovies.getValue();
                        currentMovies.addAll(list);
                        popularMovies.postValue(currentMovies);

                    }
                } else {
                    String error = response2.errorBody().string();
                    Log.v("Tag", "Error: " + error);
                    popularMovies.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                movies.postValue(null);
            }

        }
        // search Method/query

        private Call<MovieResponse> getMoviesPopular(int pageNumber) {
            return ServiceGenerator.getMovieApi().getPopularMovies(
                    "ca89b4fd28642ed9d9ef8611e1978832",
                    pageNumber
            );

        }

        private void cancelRequest() {
            Log.v("Tag", "Cancelling Search Request");
            cancelRequest = true;
        }
    }
}
