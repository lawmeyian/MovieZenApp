package com.example.moviezenapp.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviezenapp.R;
import com.example.moviezenapp.models.Movie;

public class WatchlistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //Widgets
    ImageView imageView;
    TextView movie_title, desc, release_date, language, vote_count, vote_average;

    // Click listener
    WatchlistListener watchlistListener;
    Movie movie;


    public WatchlistViewHolder(@NonNull View itemView, WatchlistListener watchlistListener) {
        super(itemView);
        this.watchlistListener = watchlistListener;
        imageView = itemView.findViewById(R.id.movie_image);
        movie_title = itemView.findViewById(R.id.textView_title_details);
        desc = itemView.findViewById(R.id.textView_detail);
        release_date = itemView.findViewById(R.id.release_date);
        language = itemView.findViewById(R.id.language);
        vote_count = itemView.findViewById(R.id.vote_count);
        vote_average = itemView.findViewById(R.id.vote_average);
//        ratingBar = itemView.findViewById(R.id.ratingBar_details);


        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (watchlistListener != null) {
            watchlistListener.onMovieClick(getBindingAdapterPosition());
        }

    }

}
