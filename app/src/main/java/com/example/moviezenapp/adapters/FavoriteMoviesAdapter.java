package com.example.moviezenapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviezenapp.R;
import com.example.moviezenapp.models.Movie;

import java.util.List;

public class FavoriteMoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Movie> movies;
    private final FavoriteMoviesListener watchlistListener;

    public FavoriteMoviesAdapter(List<Movie> movies, FavoriteMoviesListener watchlistListener) {
        this.movies = movies;
        this.watchlistListener = watchlistListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent,
                false);
        return new FavoriteMoviesViewHolder(view, watchlistListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Image view: Using Glide Library
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/" + movies.get(position).getPoster_path())
                .into((((FavoriteMoviesViewHolder) holder).imageView));
//        ((MovieViewHolder)holder).ratingBar.setRating((movies.get(position).getVote_average())/2);

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
}
