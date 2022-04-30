package com.example.moviezenapp.ui.lists;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moviezenapp.R;
import com.example.moviezenapp.adapters.ListsAdapter;
import com.example.moviezenapp.models.MovieList;
import com.example.moviezenapp.viewmodels.MovieDetailsViewModel;

import java.util.ArrayList;

public class ListsFragment extends Fragment implements ListsAdapter.OnListItemClickListener {
    private ArrayList<MovieList> list;
    private RecyclerView recyclerView;
    private String name;
    private MovieDetailsViewModel viewModel;
    private MutableLiveData<ArrayList<MovieList>> listMutableLiveData = new MutableLiveData<>();
    TextView textView, listSize;
    private ListsViewModel listsViewModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lists, container, false);
        viewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);
        this.list = new ArrayList<>();
        recyclerView = view.findViewById(R.id.myMoviesRecycleView);
        textView = view.findViewById(R.id.myMoviesListText);
        listSize = view.findViewById(R.id.listSize);
        recyclerView.hasFixedSize();
        listsViewModel = ListsViewModel.getInstance();

        viewModel.getAllListsFromDB().observe(getViewLifecycleOwner(), theList -> {
            if (list.isEmpty()) {
                for (MovieList movieList : theList) {
                    if (movieList.getId() != null) {
                        switch (movieList.getId()) {
                            case "saved":
                                name = "Saved";
                                movieList.setName(name);
                                break;
                            case "watchlist":
                                name = "Watchlist";
                                movieList.setName(name);
                                break;
                            case "watched":
                                name = "Watched";
                                movieList.setName(name);
                                break;
                            case "favorite":
                                name = "Favorite";
                                movieList.setName(name);
                                break;
                            default:
                                movieList = new MovieList("Unknown");
                        }
                    }
                    list.add(movieList);
                }
            }
            listMutableLiveData.setValue(list);
        });

        getListMutableLiveData().observe(getViewLifecycleOwner(), listsOfMovieList -> {
            if (listsOfMovieList.size() == 0) {
                textView.setText("No movies, please add some");

            } else {
                textView.setText("");
            }

            GridLayoutManager manager = new GridLayoutManager(getContext(), 1, RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(manager);
            ListsAdapter adapter = new ListsAdapter(listsOfMovieList, this);
            recyclerView.setAdapter(adapter);
        });

        return view;
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        if (list.get(clickedItemIndex).isEmpty()) {
            Toast.makeText(getActivity(), "Nothing here yet", Toast.LENGTH_SHORT).show();
        } else {
            listsViewModel.setListOfMovies(list.get(clickedItemIndex));
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.navigate_to_selected_list);
        }
    }

    public MutableLiveData<ArrayList<MovieList>> getListMutableLiveData() {
        return listMutableLiveData;
    }
}