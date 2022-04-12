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
import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.viewmodels.MovieDetailsViewModel;

public class MovieDetails extends AppCompatActivity {

    //Widgets
    private ImageView imageViewDetails;
    private TextView titleDetails, descDetails, release_date, language, vote_count, vote_average;
    Button favButton;
    Movie movie;
    MovieDetailsViewModel movieDetailsViewModel;

//    private RatingBar ratingBarDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        movieDetailsViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        imageViewDetails = findViewById(R.id.imageView_details);
        titleDetails = findViewById(R.id.textView_title_details);
        descDetails = findViewById(R.id.textView_detail);
        release_date = findViewById(R.id.release_date);
        language = findViewById(R.id.language);
        vote_count = findViewById(R.id.vote_count);
        vote_average = findViewById(R.id.vote_average);
//        ratingBarDetails = findViewById(R.id.ratingBar_details);
        favButton = findViewById(R.id.favButton);
        GetDataFromIntent();

    }

    public void addToWatchList(View v) {
        movieDetailsViewModel.addToFavorite(movie);
        Toast.makeText(getApplicationContext(), "Added to watchlist: " + movie.getTitle(), Toast.LENGTH_SHORT).show();
    }


    private void GetDataFromIntent() {

        movie = (Movie) getIntent().getSerializableExtra("movie");


        titleDetails.setText(movie.getTitle());
        Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + movie.getPoster_path()).into(imageViewDetails);
        descDetails.setText(movie.getOverview());
        release_date.setText(movie.getRelease_date());
        language.setText(movie.getOriginal_language());

        vote_average.setText(Float.toString(movie.getVote_average()));
        vote_count.setText(Float.toString(movie.getVote_count()));

        Log.v("Tag", "Avg############# " + movie.getVote_average());
        Log.v("Tag", "Lang############# " + movie.getOriginal_language());
        Log.v("Tag", "count############# " + movie.getVote_count());

    }
}