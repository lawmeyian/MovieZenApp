package com.example.moviezenapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviezenapp.R;
import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.viewmodels.MovieDetailsViewModel;

public class MovieDetails extends AppCompatActivity {

    //Widgets
    private ImageView imageViewDetails;
    private TextView titleDetails, descDetails, release_date, language, vote_count, vote_average;
    Movie movie;
    MovieDetailsViewModel movieDetailsViewModel;
    ImageView imageFav;

    private Boolean isMovieAvailableInFavorites = false;

//    private RatingBar ratingBarDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movieDetailsViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        imageFav = findViewById(R.id.img_favorite);

        imageViewDetails = findViewById(R.id.imageView_details);
        titleDetails = findViewById(R.id.textView_title_details);
        descDetails = findViewById(R.id.textView_detail);
        release_date = findViewById(R.id.release_date);
        language = findViewById(R.id.language);
        vote_count = findViewById(R.id.vote_count);
        vote_average = findViewById(R.id.vote_average);
//        ratingBarDetails = findViewById(R.id.ratingBar_details);


        imageFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMovieAvailableInFavorites) {
                    movieDetailsViewModel.removeFromFavorites(movie);
                    isMovieAvailableInFavorites = false;
                    imageFav.setImageResource(R.drawable.ic_favorite_outline);
                    Toast.makeText(getApplicationContext(), "Removed from fav: " + movie.getTitle(), Toast.LENGTH_SHORT).show();
                } else {
                    movieDetailsViewModel.addMovie(movie);
                    isMovieAvailableInFavorites = true;
                    imageFav.setImageResource(R.drawable.ic_favorite);

                    Toast.makeText(getApplicationContext(), "Added to fav: " + movie.getTitle(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        imageFav.setVisibility(View.VISIBLE);
        GetDataFromIntent();
    }


    private void GetDataFromIntent() {


        movie = (Movie) getIntent().getSerializableExtra("movie");
        checkMoviesInFavoriteList();

        titleDetails.setText(movie.getTitle());
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + movie.getPoster_path()).into(imageViewDetails);
        descDetails.setText(movie.getOverview());
        release_date.setText(movie.getRelease_date());
        language.setText(movie.getOriginal_language());

        vote_average.setText(Float.toString(movie.getVote_average()));
        vote_count.setText(Float.toString(movie.getVote_count()));


    }

    private void checkMoviesInFavoriteList() {
        movieDetailsViewModel.getMovieFromFavorites(movie.getId()).observe(this, movies -> {

            if (movies.size() == 1) {
                isMovieAvailableInFavorites = true;
                imageFav.setImageResource(R.drawable.ic_favorite);
            } else {
                isMovieAvailableInFavorites = false;
                imageFav.setImageResource(R.drawable.ic_favorite_outline);
            }
        });


    }
}