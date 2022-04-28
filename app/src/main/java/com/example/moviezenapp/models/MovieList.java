package com.example.moviezenapp.models;

import java.util.ArrayList;

public class MovieList {
    private String id;
    private ArrayList<Movie> list;
    private String name;

    public MovieList() {
        this.list = new ArrayList<>();
        this.name = "";
    }

    public MovieList(String name) {
        this.name = name;
        this.list = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public ArrayList<Movie> getList() {
        return list;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setList(ArrayList<Movie> list) {
        this.list = list;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return list.size();
    }

    public void addMovie(Movie movie) {
        list.add(movie);
    }

    public void removeMovie(int id) {
        list.remove(id);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }
}
