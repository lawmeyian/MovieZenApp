package com.example.moviezenapp.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviezenapp.R;
import com.example.moviezenapp.models.FavoriteList;
import com.example.moviezenapp.models.Watchlist;

import java.util.List;

public class FavoriteListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<FavoriteList> favoriteLists;
    private final OnFavoriteClickListener onMovieClickListener;

    public FavoriteListAdapter(List<FavoriteList> favoriteLists, OnFavoriteClickListener onMovieClickListener) {
        this.favoriteLists = favoriteLists;
        this.onMovieClickListener = onMovieClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent,
                false);
        return new FavoriteListViewHolder(view, onMovieClickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/" + favoriteLists.get(position).getPoster_path())
                .into((((FavoriteListViewHolder) holder).imageView));

        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        if (favoriteLists != null) {
            return favoriteLists.size();
        }
        return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setMovies(List<FavoriteList> favoriteLists) {
        this.favoriteLists = favoriteLists;
        notifyDataSetChanged();
    }

    // Getting the id of the movie click

    public FavoriteList getSelectedMovie(int position) {
        if (favoriteLists != null) {
            if (favoriteLists.size() > 0) {
                return favoriteLists.get(position);
            }
        }
        return null;
    }

}
