package com.example.moviezenapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviezenapp.R;
import com.example.moviezenapp.WatchlistDataSource;
import com.example.moviezenapp.database.MoviesDb;
import com.example.moviezenapp.models.FavoriteList;
import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.models.Watchlist;
import com.example.moviezenapp.repositories.WatchlistRepository;
import com.example.moviezenapp.viewmodels.MovieDetailsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MovieDetails extends AppCompatActivity {

    //Widgets
    private ImageView imageViewDetails;
    private TextView titleDetails, descDetails, release_date, language, vote_count, vote_average;
    Movie movie;
    MovieDetailsViewModel movieDetailsViewModel;
    ImageView imageFav;
Watchlist watchlistObject;
FavoriteList favoriteListObject;
    private Boolean isMovieAvailableInFavorites = false;
    private Boolean isMovieAvailableInWatchlist = false;
    ImageView watchlist;
    Button watchlistButton;
//    private RatingBar ratingBarDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movieDetailsViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        imageFav = findViewById(R.id.img_favorite);
        watchlistObject = new Watchlist();
        favoriteListObject = new FavoriteList();
        watchlist = findViewById(R.id.img_watchlist);
//        watchlistButton = findViewById(R.id.addToWatchlist);
        imageViewDetails = findViewById(R.id.imageView_details);
        titleDetails = findViewById(R.id.textView_title_details);
        descDetails = findViewById(R.id.textView_detail);
        release_date = findViewById(R.id.release_date);
        language = findViewById(R.id.language);
        vote_count = findViewById(R.id.vote_count);
        vote_average = findViewById(R.id.vote_average);
//        ratingBarDetails = findViewById(R.id.ratingBar_details);

//        watchlistButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                addToWatchlist(movie);
//                Toast.makeText(getApplicationContext(), "Added to watchlist: " + movie.getTitle(), Toast.LENGTH_SHORT).show();
//            }
//        });

watchlist.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (isMovieAvailableInWatchlist) {
            movieDetailsViewModel.removeFromWatchlist(watchlistObject);
            isMovieAvailableInWatchlist = false;
            watchlist.setImageResource(R.drawable.ic_watch);
            Toast.makeText(getApplicationContext(), "Removed from watchlist: " + watchlistObject.getTitle(), Toast.LENGTH_SHORT).show();
        } else {
            addToWatchlist(movie);
            isMovieAvailableInWatchlist = true;
            watchlist.setImageResource(R.drawable.ic_watched);

            Toast.makeText(getApplicationContext(), "Added to watchlist: " + movie.getTitle(), Toast.LENGTH_SHORT).show();
        }
    }
});

        imageFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMovieAvailableInFavorites) {
                    movieDetailsViewModel.removeFromFavorites(favoriteListObject);
                    isMovieAvailableInFavorites = false;
                    imageFav.setImageResource(R.drawable.ic_favorite_outline);
                    Toast.makeText(getApplicationContext(), "Removed from fav: " + movie.getTitle(), Toast.LENGTH_SHORT).show();
                } else {
                 addToFavoriteList(movie);
                    isMovieAvailableInFavorites = true;
                    imageFav.setImageResource(R.drawable.ic_favorite);

                    Toast.makeText(getApplicationContext(), "Added to fav: " + movie.getTitle(), Toast.LENGTH_SHORT).show();
                }
            }
        });



        if(getIntent().hasExtra("movie")) {
            GetDataFromIntent();
            imageFav.setVisibility(View.VISIBLE);
//            watchlistButton.setVisibility(View.VISIBLE);
            watchlist.setVisibility(View.VISIBLE);
        } else if (getIntent().hasExtra("watchlist")){
            getData();
            watchlist.setVisibility(View.VISIBLE);
        }
        else if(getIntent().hasExtra("favoriteList"))
        {
            getFavoriteData();
            imageFav.setVisibility(View.VISIBLE);
        }

    }
    private void addToWatchlist(Movie movie) {
        watchlistObject.setId(movie.getId());
        watchlistObject.setTitle(movie.getTitle());
        watchlistObject.setPoster_path(movie.getPoster_path());
        watchlistObject.setOverview(movie.getOverview());
        watchlistObject.setOriginal_language(movie.getOriginal_language());
        watchlistObject.setRelease_date(movie.getRelease_date());
        watchlistObject.setVote_average(movie.getVote_average());
        watchlistObject.setVote_count(movie.getVote_count());
//     watchlistObject.setPoster_path("https://image.tmdb.org/t/p/w500/" + movie.getPoster_path());

      movieDetailsViewModel.insertWatchlistItem(watchlistObject);
    }
    private void addToFavoriteList(Movie movie) {
        favoriteListObject.setId(movie.getId());
        favoriteListObject.setTitle(movie.getTitle());
        favoriteListObject.setPoster_path(movie.getPoster_path());
        favoriteListObject.setOverview(movie.getOverview());
        favoriteListObject.setOriginal_language(movie.getOriginal_language());
        favoriteListObject.setRelease_date(movie.getRelease_date());
        favoriteListObject.setVote_average(movie.getVote_average());
        favoriteListObject.setVote_count(movie.getVote_count());
//     watchlistObject.setPoster_path("https://image.tmdb.org/t/p/w500/" + movie.getPoster_path());
        movieDetailsViewModel.insertFavoriteListItem(favoriteListObject);
    }



    private void getData()
    {
        watchlistObject = (Watchlist) getIntent().getSerializableExtra("watchlist");
        checkMoviesInWatchlist();

        titleDetails.setText(watchlistObject.getTitle());
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + watchlistObject.getPoster_path()).into(imageViewDetails);
        descDetails.setText(watchlistObject.getOverview());
        release_date.setText(watchlistObject.getRelease_date());
        language.setText(watchlistObject.getOriginal_language());

        vote_average.setText(Float.toString(watchlistObject.getVote_average()));
        vote_count.setText(Float.toString(watchlistObject.getVote_count()));
        movieDetailsViewModel.insertWatchlistItem(watchlistObject);

    }
    private void getFavoriteData()
    {
        favoriteListObject = (FavoriteList) getIntent().getSerializableExtra("favoriteList");
//        checkMoviesInWatchlist();
        checkMoviesInFavoriteList();

        titleDetails.setText(favoriteListObject.getTitle());
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + favoriteListObject.getPoster_path()).into(imageViewDetails);
        descDetails.setText(favoriteListObject.getOverview());
        release_date.setText(favoriteListObject.getRelease_date());
        language.setText(favoriteListObject.getOriginal_language());

        vote_average.setText(Float.toString(favoriteListObject.getVote_average()));
        vote_count.setText(Float.toString(favoriteListObject.getVote_count()));
        movieDetailsViewModel.insertFavoriteListItem(favoriteListObject);
    }
    private void GetDataFromIntent() {


        movie = (Movie) getIntent().getSerializableExtra("movie");
//     checkMoviesInFavoriteListInMovies();
//     checkMoviesInWatchlistInMovies();

        titleDetails.setText(movie.getTitle());
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + movie.getPoster_path()).into(imageViewDetails);
        descDetails.setText(movie.getOverview());
        release_date.setText(movie.getRelease_date());
        language.setText(movie.getOriginal_language());

        vote_average.setText(Float.toString(movie.getVote_average()));
        vote_count.setText(Float.toString(movie.getVote_count()));



    }

    private void checkMoviesInFavoriteList() {
        movieDetailsViewModel.getMovieFromFavorites(favoriteListObject.getId()).observe(this, movies -> {
            Log.v("sizefav", "onChanged " + movies.size());
            if (movies.size() == 1) {
                isMovieAvailableInFavorites = true;
                imageFav.setImageResource(R.drawable.ic_favorite);
            } else {
                isMovieAvailableInFavorites = false;
                imageFav.setImageResource(R.drawable.ic_favorite_outline);
            }
        });

    }

    private void checkMoviesInFavoriteListInMovies() {


        movieDetailsViewModel.getMovieFromFavorites(movie.getId()).observe(this, movies -> {
            Log.v("sizefav", "onChanged " + movies.size());
            if (movies.size() == 1) {
                isMovieAvailableInFavorites = true;
                imageFav.setImageResource(R.drawable.ic_favorite);
            } else {
                isMovieAvailableInFavorites = false;
                imageFav.setImageResource(R.drawable.ic_favorite_outline);
            }
        });


    }

    private void checkMoviesInWatchlist() {

        movieDetailsViewModel.getMovieFromWatchlist(watchlistObject.getId()).observe(this, movies -> {
            Log.v("sizee", "onChanged " + movies.size());
            if (movies.size() == 1) {
                isMovieAvailableInWatchlist = true;
                watchlist.setImageResource(R.drawable.ic_watched);
            } else {
                isMovieAvailableInWatchlist = false;
                watchlist.setImageResource(R.drawable.ic_watch);
            }
        });



    }

    private void checkMoviesInWatchlistInMovies() {

        movieDetailsViewModel.getMovieFromWatchlist(movie.getId()).observe(this, movies -> {
            Log.v("sizee", "onChanged " + movies.size());
            if (movies.size() == 1) {
                isMovieAvailableInWatchlist = true;
                watchlist.setImageResource(R.drawable.ic_watched);
            } else {
                isMovieAvailableInWatchlist = false;
                watchlist.setImageResource(R.drawable.ic_watch);
            }
        });



    }



}