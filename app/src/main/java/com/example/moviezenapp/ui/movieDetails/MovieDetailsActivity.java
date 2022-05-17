
package com.example.moviezenapp.ui.movieDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviezenapp.R;
import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.ui.lists.ListsViewModel;
import com.example.moviezenapp.ui.movies.MoviesViewModel;
import com.google.gson.Gson;

public class MovieDetailsActivity extends AppCompatActivity {
    //Widgets
    private ImageView imageViewDetails;
    private TextView titleDetails, descDetails, release_date, language, vote_count, vote_average;
    Movie movieModel;
    private MovieDetailsViewModel viewModel;
    ImageView favorite, watchlist, watched;
    private Boolean isMovieAvailableInFavList = false;
    private MoviesViewModel moviesViewModel;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        this.context = getBaseContext();
        viewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        moviesViewModel = MoviesViewModel.getInstance();
        imageViewDetails = findViewById(R.id.imageView_details);
        titleDetails = findViewById(R.id.textView_title_details);
        descDetails = findViewById(R.id.textView_detail);
        release_date = findViewById(R.id.release_date);
        language = findViewById(R.id.language);
        vote_count = findViewById(R.id.vote_count);
        vote_average = findViewById(R.id.vote_average);
        favorite = findViewById(R.id.favorite);
        watched = findViewById(R.id.watched);
        watchlist = findViewById(R.id.watchlist);

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dialog.setCancelable(true);
        Button submit = dialog.findViewById(R.id.doneRating);
        RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);
        EditText keyword = dialog.findViewById(R.id.keyword);

        GetDataFromIntent();

        favorite.setOnClickListener(v -> {
            dialog.show();
            submit.setOnClickListener(v1 -> {
                if (ratingBar.getRating() == 0.0 || keyword.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please rate this movie", Toast.LENGTH_SHORT).show();
                } else {
                    float value = ratingBar.getRating();
                    String keywordText = keyword.getText().toString();
                    movieModel.setKeyword(keywordText);
                    movieModel.setPersonalRating(value);
                    dialog.dismiss();
                    viewModel.saveMovie("favorite", movieModel);
                    favorite.setImageResource(R.drawable.ic_favorite_filled);
                    Toast.makeText(getApplicationContext(), "Added to favorites: " + movieModel.getTitle(), Toast.LENGTH_SHORT).show();
                }

            });
        });

        watched.setOnClickListener(v -> {
            dialog.show();
            submit.setOnClickListener(v1 -> {
                if (ratingBar.getRating() == 0.0 || keyword.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Please rate this movie", Toast.LENGTH_SHORT).show();
                } else {
                    float value = ratingBar.getRating();
                    String keywordText = keyword.getText().toString();
                    movieModel.setKeyword(keywordText);
                    movieModel.setPersonalRating(value);
                    dialog.dismiss();
                    viewModel.saveMovie("watched", movieModel);
                    watched.setImageResource(R.drawable.ic_watched);
                    Toast.makeText(getApplicationContext(), "Added to watched list: " + movieModel.getTitle(), Toast.LENGTH_SHORT).show();
                }

            });
        });

        watchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.saveMovie("watchlist", movieModel);
                watchlist.setImageResource(R.drawable.ic_watchlist_filled);
                Toast.makeText(getApplicationContext(), "Added to watchlist: " + movieModel.getTitle(), Toast.LENGTH_SHORT).show();
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

        }

    }
}