package com.example.moviezenapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviezenapp.R;
import com.example.moviezenapp.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Movie> movies;
    private final OnMovieClickListener onMovieClickListener;

    public MovieAdapter(List<Movie> movies, OnMovieClickListener onMovieClickListener) {
        this.movies = movies;
        this.onMovieClickListener = onMovieClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent,
                false);
        return new MovieViewHolder(view, onMovieClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/" + movies.get(position).getPoster_path())
                .into((((MovieViewHolder) holder).imageView));
    }


    @Override
    public int getItemCount() {

        if (movies != null) {
            return movies.size();
        }
        return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    // Getting the id of the movie click

    public Movie getSelectedMovie(int position) {
        if (movies != null) {
            if (movies.size() > 0) {
                return movies.get(position);
            }
        }
        return null;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Widgets
        ImageView imageView;
        TextView movie_title, desc, release_date, language, vote_count, vote_average;

        // Click listener
        OnMovieClickListener onMovieClickListener;
        Movie movie;


        public MovieViewHolder(@NonNull View itemView, OnMovieClickListener onMovieClickListener) {
            super(itemView);
            this.onMovieClickListener = onMovieClickListener;
            imageView = itemView.findViewById(R.id.movie_image);
            movie_title = itemView.findViewById(R.id.textView_title_details);
            desc = itemView.findViewById(R.id.textView_detail);
            release_date = itemView.findViewById(R.id.release_date);
            language = itemView.findViewById(R.id.language);
            vote_count = itemView.findViewById(R.id.vote_count);
            vote_average = itemView.findViewById(R.id.vote_average);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (onMovieClickListener != null) {
                onMovieClickListener.onMovieClick(getBindingAdapterPosition());
            }

        }
    }

    public interface OnMovieClickListener {
        void onMovieClick(int position);

    }
}
