package com.example.moviezenapp;

public class Movie {
    private String name;
    private int iconId;

    public Movie(String name, int iconId) {
        this.name = name;
        this.iconId = iconId;
    }
//
    public String getName() {
        return name;
    }

    public int getIconId() {
        return iconId;
    }
}
