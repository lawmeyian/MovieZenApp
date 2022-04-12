package com.example.moviezenapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
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
import com.example.moviezenapp.viewmodels.MovieViewModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
//        favButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////             movieDetailsViewModel.addToWatchlist(movie);
//                Toast.makeText(getApplicationContext(), "Added to watchlist", Toast.LENGTH_SHORT).show();
//
//            }
//        });
        GetDataFromIntent();

    }

    public void addToWatchList(View v) {
        movieDetailsViewModel.addToWatchlist(movie);
        Toast.makeText(getApplicationContext(), "Added to watchlist: " , Toast.LENGTH_SHORT).show();
    }

    private void GetDataFromIntent() {

//            Movie movieModel = getIntent().getParcelableExtra("movie");
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
//            ratingBarDetails.setRating((movieModel.getVote_average())/2);
//            Log.v("Tag", "Overview############# " + movieModel.getMovie_overview());


    }
}