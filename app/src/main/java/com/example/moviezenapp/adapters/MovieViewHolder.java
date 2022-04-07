package com.example.moviezenapp.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviezenapp.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    //Widgets
    ImageView imageView;
    TextView movie_title;

    // Click listener
    OnMovieListener onMovieListener;


    public MovieViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);
        imageView = itemView.findViewById(R.id.movie_image);
        movie_title = itemView.findViewById(R.id.movie_title);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
//        onMovieListener.onMovieClick(getBindingAdapterPosition());
        Toast.makeText(view.getContext(), movie_title.getText(), Toast.LENGTH_SHORT).show();

    }
}
