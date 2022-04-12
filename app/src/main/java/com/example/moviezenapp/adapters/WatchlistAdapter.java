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

public class WatchlistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Movie> movies;
    private final WatchlistListener watchlistListener;

    public WatchlistAdapter(List<Movie> movies, WatchlistListener watchlistListener) {
        this.movies = movies;
        this.watchlistListener = watchlistListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent,
                false);
        return new WatchlistViewHolder(view, watchlistListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // Image view: Using Glide Library
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/" + movies.get(position).getPoster_path())
                .into((((WatchlistViewHolder) holder).imageView));
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
