package com.example.moviezenapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviezenapp.R;
import com.example.moviezenapp.models.MovieList;

import java.util.ArrayList;

public class ListsAdapter extends RecyclerView.Adapter<ListsAdapter.ViewHolder> {

    private ArrayList<MovieList> lists;
    final private OnListItemClickListener mOnListItemClickListener;

    public ListsAdapter(ArrayList<MovieList> lists, OnListItemClickListener mOnListItemClickListener) {
        this.lists = lists;
        this.mOnListItemClickListener = mOnListItemClickListener;
    }

    public interface OnListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ListsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(lists.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.listName);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getBindingAdapterPosition());
        }
    }
}