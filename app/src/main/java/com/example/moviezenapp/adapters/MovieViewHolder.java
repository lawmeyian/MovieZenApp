package com.example.moviezenapp.adapters;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviezenapp.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    //Widgets
    ImageView imageView;
    TextView movie_title, desc, release_date,language,vote_count, vote_average;
//    RatingBar ratingBar;

    // Click listener
    OnMovieListener onMovieListener;


    public MovieViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);
        this.onMovieListener = onMovieListener;
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
//        onMovieListener.onMovieClick(getAbsoluteAdapterPosition());
        if (onMovieListener != null) {
            onMovieListener.onMovieClick(getBindingAdapterPosition());
        }

    }
}
