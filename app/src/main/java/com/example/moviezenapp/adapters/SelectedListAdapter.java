package com.example.moviezenapp.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviezenapp.R;
import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.models.MovieList;
import com.example.moviezenapp.ui.lists.ListsViewModel;
import com.example.moviezenapp.ui.movies.MoviesViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class SelectedListAdapter extends RecyclerView.Adapter<SelectedListAdapter.ViewHolder> {

    private ArrayList<Movie> list;
    private Context context;
    private MoviesViewModel viewModel;
    private Movie toFavorite;
    private Movie watchedMovie;
    private Movie movie;
    private String id;
    private MovieList listFromNavigation = new MovieList();
    private ListsViewModel listsViewModel;
    View view;
    final private SelectedListAdapter.OnListItemClickListener mOnListItemClickListener;

    public SelectedListAdapter(String id, ArrayList<Movie> list, Context context, SelectedListAdapter.OnListItemClickListener mOnListItemClickListener) {
        this.list = list;
        this.context = context;
        this.mOnListItemClickListener = mOnListItemClickListener;
        this.viewModel = MoviesViewModel.getInstance();
        this.id = id;
        listsViewModel = ListsViewModel.getInstance();
        listFromNavigation = listsViewModel.getListOfMovies();
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.my_movies_item, parent, false);
        return new SelectedListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);

        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        dialog.setCancelable(true);

        Button submit = dialog.findViewById(R.id.doneRating);
        RatingBar ratingBar = dialog.findViewById(R.id.ratingBar);

        holder.name.setText(list.get(position).getTitle());
        holder.releaseDate.setText(list.get(position).getRelease_date());

        if (list.get(position).getPersonalRating() == 0.0) {
            holder.personalRating.setText("");
        } else {
            holder.personalRating.setText("My Rating: " + list.get(position).getPersonalRating() + "/5.0");
        }

        Glide.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/" + list.get(position).getPoster_path())
                .into(holder.poster);

        holder.imageFav.setOnClickListener(v -> {
            toFavorite = list.get(position);
            dialog.show();

            submit.setOnClickListener(v1 -> {
                if (ratingBar.getRating() == 0.0) {
                    Toast.makeText(context, "Please rate this movie", Toast.LENGTH_SHORT).show();
                } else {
                    float value = ratingBar.getRating();
                    toFavorite.setPersonalRating(value);
                    dialog.dismiss();
                    viewModel.saveMovie("favorite", toFavorite);
                    list.remove(position);
                    viewModel.remove(id, toFavorite.getId());
                    this.notifyItemRemoved(position);
                }

            });


        });

        holder.imageWatched.setOnClickListener(v -> {

            watchedMovie = list.get(position);
            dialog.show();

            submit.setOnClickListener(v1 -> {
                if (ratingBar.getRating() == 0.0) {
                    Toast.makeText(context, "Please rate this movie", Toast.LENGTH_SHORT).show();
                } else {
                    float value = ratingBar.getRating();
                    watchedMovie.setPersonalRating(value);
                    dialog.dismiss();
                    viewModel.saveMovie("watched", watchedMovie);
                    list.remove(position);
                    viewModel.remove(id, watchedMovie.getId());
                    this.notifyItemRemoved(position);
                }
            });


        });

        holder.deleteMovie.setOnClickListener(v -> {

            movie = list.get(position);
            list.remove(position);
            viewModel.remove(id, movie.getId());
            this.notifyItemRemoved(position);

            Snackbar snackbar = Snackbar.make(view, movie.getTitle() + " Removed", Snackbar.LENGTH_LONG)
                    .setActionTextColor(ContextCompat.getColor(context, R.color.teal_700))
                    .setAction("Undo", view -> {
                        list.add(position, movie);
                        viewModel.saveMovie(listFromNavigation.getId(), movie);
                        this.notifyItemInserted(position);
                    });
            View snackBarView = snackbar.getView();
            snackBarView.setTranslationY(-45);
            snackbar.show();


        });

        holder.editRating.setOnClickListener(v -> {
            dialog.show();

            submit.setOnClickListener(v1 -> {
                if (ratingBar.getRating() == 0.0) {
                    Toast.makeText(context, "Please rate this movie", Toast.LENGTH_SHORT).show();
                } else {
                    float value = ratingBar.getRating();
                    dialog.dismiss();
                    list.get(position).setPersonalRating(value);
                    viewModel.editMoviePersonalRating(id, list.get(position).getId(), value);
                    notifyDataSetChanged();
                }
            });
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView releaseDate;
        ImageView poster;
        ImageView imageFav;
        ImageView imageWatched;
        ImageView deleteMovie;
        TextView personalRating;
        RatingBar personalRat;
        ImageView editRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameSelectedListItem);
            releaseDate = itemView.findViewById(R.id.dateOfRelease);
            poster = itemView.findViewById(R.id.imageSelectedListItem);
            imageFav = itemView.findViewById(R.id.addToFavorite);
            imageWatched = itemView.findViewById(R.id.addToWatchlist);
            personalRating = itemView.findViewById(R.id.personalRatingSelectedList);
            personalRat = itemView.findViewById(R.id.ratingBar);
            editRating = itemView.findViewById(R.id.editRating);
            deleteMovie = itemView.findViewById(R.id.delete);

            if (id.equals("favorite")) {
                imageFav.setVisibility(View.INVISIBLE);
                imageWatched.setVisibility(View.INVISIBLE);
            }

            if (id.equals("watched")) {
                imageWatched.setVisibility(View.INVISIBLE);
                imageFav.setVisibility(View.INVISIBLE);
            }

            if (id.equals("saved")) {
                editRating.setVisibility(View.GONE);
            }

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getBindingAdapterPosition());
        }
    }
}