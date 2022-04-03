package com.example.moviezenapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<Movie> movies;

    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.name.setText(movies.get(position).getName());
        Movie movieData = movies.get(position);
        holder.icon.setImageResource(movies.get(position).getIconId());

        holder.icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), movieData.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //        private final TextView name;
        private final ImageView icon;

        public ViewHolder(View itemView) {
            super(itemView);
//            name = itemView.findViewById(R.id.movie_name);
            icon = itemView.findViewById(R.id.movie_image);
        }
    }
}
