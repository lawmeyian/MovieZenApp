package com.example.moviezenapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.moviezenapp.R;
import com.example.moviezenapp.ui.signIn.SignInActivity;
import com.example.moviezenapp.viewmodels.MainActivityViewModel;
import com.example.moviezenapp.viewmodels.MovieViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    AppBarConfiguration appBarConfiguration;
    private MovieViewModel movieViewModel;
    boolean isPopular = true;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        setContentView(R.layout.activity_main);
        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);
        checkIfSignedIn();
        setupNavigation();

    }

    private void checkIfSignedIn() {
        viewModel.getCurrentUser().observe(this, user -> {
            if (user != null) {
                Log.v("Logging", "HELLO!!!" + viewModel.getCurrentUser().getValue().getEmail());
            } else {
                startLoginActivity();
            }
        });
    }

    private void startLoginActivity() {
        startActivity(new Intent(this, SignInActivity.class));
        finish();
    }

    private void setupNavigation() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

//        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.moviesFragment,
                R.id.listsFragment, R.id.profileFragment, R.id.selected_list_fragment).build();
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