package com.example.moviezenapp.ui.selectedList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moviezenapp.R;
import com.example.moviezenapp.adapters.SelectedListAdapter;
import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.models.MovieList;
import com.example.moviezenapp.ui.movieDetails.MovieDetailsViewModel;
import com.example.moviezenapp.ui.lists.ListsViewModel;

import java.util.ArrayList;

public class SelectedListFragment extends Fragment implements SelectedListAdapter.OnListItemClickListener {
    private ListsViewModel listsViewModel;
    private ArrayList<Movie> list;
    private TextView textView;
    private RecyclerView recyclerView;
    private MovieList movieList = new MovieList();
    MovieDetailsViewModel viewModel;
    private SelectedListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_selected_list, container, false);
        viewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        listsViewModel = ListsViewModel.getInstance();
        recyclerView = view.findViewById(R.id.recyclerView);
        textView = view.findViewById(R.id.textView);

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        movieList = listsViewModel.getListOfMovies();
        list = listsViewModel.getListOfMovies().getList();
        if (list.isEmpty()) {
            textView.setText("No movies");
        } else {
            textView.setText("");
        }
        adapter = new SelectedListAdapter(movieList.getId(), list, getContext(), this);

        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {

    }
}