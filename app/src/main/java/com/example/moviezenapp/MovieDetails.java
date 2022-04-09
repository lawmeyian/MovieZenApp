package com.example.moviezenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.moviezenapp.models.Movie;

public class MovieDetails extends AppCompatActivity {

    //Widgets
    private ImageView imageViewDetails;
    private TextView titleDetails, descDetails, release_date,language,vote_count,vote_average;
//    private RatingBar ratingBarDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        imageViewDetails = findViewById(R.id.imageView_details);
        titleDetails = findViewById(R.id.textView_title_details);
        descDetails = findViewById(R.id.textView_detail);
        release_date = findViewById(R.id.release_date);
        language = findViewById(R.id.language);
        vote_count = findViewById(R.id.vote_count);
        vote_average = findViewById(R.id.vote_average);
//        ratingBarDetails = findViewById(R.id.ratingBar_details);

        GetDataFromIntent();

    }

    private void GetDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            Movie movieModel = getIntent().getParcelableExtra("movie");


            titleDetails.setText(movieModel.getTitle());
            Glide.with(this).load("https://image.tmdb.org/t/p/w500/" + movieModel.getPoster_path()).into(imageViewDetails);
            descDetails.setText(movieModel.getMovie_overview());
            release_date.setText(movieModel.getRelease_date());
            language.setText(movieModel.getOriginal_language());

            vote_average.setText(Float.toString(movieModel.getVote_average()));
            vote_count.setText(Float.toString(movieModel.getVote_count()));

            Log.v("Tag", "Avg############# " + movieModel.getVote_average());
            Log.v("Tag", "Lang############# " + movieModel.getOriginal_language());
            Log.v("Tag", "count############# " + movieModel.getVote_count());
//            ratingBarDetails.setRating((movieModel.getVote_average())/2);
//            Log.v("Tag", "Overview############# " + movieModel.getMovie_overview());
        }

    }
}