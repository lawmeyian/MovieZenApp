
package com.example.moviezenapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviezenapp.R;
import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.viewmodels.MovieDetailsViewModel;
import com.example.moviezenapp.viewmodels.ListsViewModel;
import com.google.gson.Gson;

public class MovieDetails extends AppCompatActivity {

    private Gson gson = new Gson();
    //Widgets
    private ImageView imageViewDetails;
    private TextView titleDetails, descDetails, release_date, language, vote_count, vote_average;
    Movie movieModel;
    private MovieDetailsViewModel viewModel;
    //    private RatingBar ratingBarDetails;
    ImageView favorite, save, watched;
    private String listId;
    private Boolean isMovieAvailableInWatchedList = false;
    private ListsViewModel listsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        viewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        listsViewModel = ListsViewModel.getInstance();
        imageViewDetails = findViewById(R.id.imageView_details);
        titleDetails = findViewById(R.id.textView_title_details);
        descDetails = findViewById(R.id.textView_detail);
        release_date = findViewById(R.id.release_date);
        language = findViewById(R.id.language);
        vote_count = findViewById(R.id.vote_count);
        vote_average = findViewById(R.id.vote_average);
//        ratingBarDetails = findViewById(R.id.ratingBar_details);
        save = findViewById(R.id.save);
        GetDataFromIntent();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.saveMovie("saved", movieModel);
                Toast.makeText(getApplicationContext(), movieModel.getTitle() + " Saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }


    private void GetDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            movieModel = (Movie) getIntent().getSerializableExtra("movie");

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
        }

    }
}