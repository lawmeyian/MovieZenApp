package com.example.moviezenapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviezenapp.R;
import com.example.moviezenapp.models.MovieModel;

import java.util.List;

public class MovieRecyclerView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<MovieModel> movies;
    private OnMovieListener onMovieListener;

    public MovieRecyclerView(OnMovieListener onMovieListener)
    {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent,
                false);
        return new MovieViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MovieViewHolder)holder).movie_title.setText(movies.get(position).getTitle());
        // Image view: Using Glide Library
        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/" +movies.get(position).getPoster_path())
                .into((((MovieViewHolder)holder).imageView));
    }

    @Override
    public int getItemCount() {

        if(movies != null)
        {
            return movies.size();
        }
        return 0;
    }

    public void setMovies(List<MovieModel> movies)
    {
        this.movies = movies;
        notifyDataSetChanged();
    }
}
