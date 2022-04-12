package com.example.moviezenapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.moviezenapp.viewmodels.MovieViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    AppBarConfiguration appBarConfiguration;
    private MovieViewModel movieViewModel;
    boolean isPopular = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        setupNavigation();

    }

    private void setupNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.moviesFragment,
                R.id.listsFragment, R.id.favoriteFragment).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.appbar, menu);
        MenuItem actionSearch = menu.findItem(R.id.nav_search);
        SearchView searchView = (SearchView) actionSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                movieViewModel.searchMovieApi(
                        // The search string gotten from searchView
                        query,
                        1
                );

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                movieViewModel.searchMovieApiPopular(1);
                return false;
            }
        });


        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isPopular = false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


}