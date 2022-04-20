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
import com.example.moviezenapp.models.Watchlist;
import com.example.moviezenapp.repositories.WatchlistRepository;

import java.util.List;

public class WatchlistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Watchlist> watchlist;
    private final OnMovieClickListener onMovieClickListener;
    WatchlistRepository watchlistRepository;

    public WatchlistAdapter(List<Watchlist> watchlist, OnMovieClickListener onMovieClickListener) {
        this.watchlist = watchlist;
        this.onMovieClickListener = onMovieClickListener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent,
                false);
        return new WatchListViewHolder(view, onMovieClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/" + watchlist.get(position).getPoster_path())
                .into((((WatchListViewHolder) holder).imageView));
    }

    @Override
    public int getItemCount() {
        if (watchlist != null) {
            return watchlist.size();
        }
        return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMovies(List<Watchlist> watchlist) {
        this.watchlist = watchlist;
        notifyDataSetChanged();
    }

    // Getting the id of the movie click

    public Watchlist getSelectedMovie(int position) {
        if (watchlist != null) {
            if (watchlist.size() > 0) {
                return watchlist.get(position);
            }
        }
        return null;
    }

}
