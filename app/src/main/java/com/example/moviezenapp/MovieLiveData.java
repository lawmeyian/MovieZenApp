package com.example.moviezenapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.moviezenapp.models.Movie;
import com.example.moviezenapp.models.MovieList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class MovieLiveData extends LiveData<Movie> {

    private DatabaseReference myRef;
    private final MutableLiveData<MovieList> selectedMovieList;
    private final MutableLiveData<ArrayList<MovieList>> allMovieLists;
    private String movie = "";

    public MovieLiveData(String userId) {
        selectedMovieList = new MutableLiveData<>();
        allMovieLists = new MutableLiveData<>();
        myRef = FirebaseDatabase.getInstance("https://moviezenapp-35014-default-rtdb.firebaseio.com/")
                .getReference().child("users").child(userId);
    }

    public void remove(String listId, String id) {
        myRef.child(listId).child(id).removeValue();
    }

    public void saveMovie(String listId, Movie movieToSave) {
        movie = myRef.child(listId).push().getKey();
        myRef.child(listId).child(movie).setValue(movieToSave);
    }

    public LiveData<ArrayList<MovieList>> getAllListsFromDB() {
        ArrayList<MovieList> allLists = new ArrayList<>();

        myRef.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                allLists.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    MovieList movieList = getMovieList(child.getKey());
                    allLists.add(movieList);
                }
                allMovieLists.setValue(allLists);
            }

            public void onCancelled(DatabaseError databaseError) {
            }
        });

        return allMovieLists;
    }

    public MovieList getMovieList(String id) {
        MovieList movieList;

        switch (id) {
            case "saved":
                movieList = new MovieList("Saved");
                movieList.setId(id);
                break;
            case "watchlist":
                movieList = new MovieList("Watchlist");
                movieList.setId(id);
                break;
            case "watched":
                movieList = new MovieList("Watched");
                movieList.setId(id);

                break;
            case "favorite":
                movieList = new MovieList("Favorite");
                movieList.setId(id);
                break;
            default:
                movieList = new MovieList("Unknown");
        }

        myRef.child(id).addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                movieList.getList().clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Movie movie = child.getValue(Movie.class);
                    movie.setId(child.getKey());
                    movieList.setId(dataSnapshot.getKey());
                    movieList.addMovie(movie);
                }
                Collections.reverse(movieList.getList());
            }

            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return movieList;
    }

}
