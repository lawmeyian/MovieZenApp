package com.example.moviezenapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviezenapp.R;
import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.viewmodels.MoviesViewModel;

import java.util.ArrayList;

public class SelectedListAdapter extends RecyclerView.Adapter<SelectedListAdapter.ViewHolder> {

    private ArrayList<Movie> list;
    private Context context;
    private MoviesViewModel viewModel;
    private Movie toFavorite;
    private Movie watchedMovie;
    private String id;
    final private SelectedListAdapter.OnListItemClickListener mOnListItemClickListener;

    public SelectedListAdapter(String id, ArrayList<Movie> list, Context context, SelectedListAdapter.OnListItemClickListener mOnListItemClickListener) {
        this.list = list;
        this.context = context;
        this.mOnListItemClickListener = mOnListItemClickListener;
        this.viewModel = MoviesViewModel.getInstance();
        this.id = id;
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.my_movies_item, parent, false);
        return new SelectedListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getTitle());
        holder.rating.setText("TMDB Rating: " + list.get(position).getVote_average());

        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/" + list.get(position).getPoster_path())
                .into(holder.poster);

        holder.imageFav.setOnClickListener(v -> {
            toFavorite = list.get(position);
            viewModel.saveMovie("favorite", toFavorite);
            viewModel.remove(id, toFavorite.getId());
            Toast.makeText(context, toFavorite.getTitle() + " added to fav", Toast.LENGTH_SHORT).show();

            list.remove(position);

            this.notifyItemRemoved(position);
        });

        holder.imageWatched.setOnClickListener(v -> {
            watchedMovie = list.get(position);
            viewModel.saveMovie("watched", watchedMovie);
            viewModel.remove(id, watchedMovie.getId());
            Toast.makeText(context, watchedMovie.getTitle() + " added to watched", Toast.LENGTH_SHORT).show();

            list.remove(position);

            this.notifyItemRemoved(position);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView rating;
        ImageView poster;
        ImageView imageFav;
        ImageView imageWatched;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameSelectedListItem);
            rating = itemView.findViewById(R.id.ratingSelectedListItem);
            poster = itemView.findViewById(R.id.imageSelectedListItem);
            imageFav = itemView.findViewById(R.id.addToFavorite);
            imageWatched = itemView.findViewById(R.id.addToWatchlist);

            if (id.equals("favorite")) {
                imageFav.setVisibility(View.INVISIBLE);
            }

            if (id.equals("watched")) {
                imageWatched.setVisibility(View.INVISIBLE);
            }

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }
}