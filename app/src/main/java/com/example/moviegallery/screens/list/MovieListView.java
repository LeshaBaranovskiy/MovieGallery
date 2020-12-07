package com.example.moviegallery.screens.list;

import com.example.moviegallery.pojo.Movie;

import java.util.List;

public interface MovieListView {
    void getListMoviesFromApi(List<Movie> movies);

    void getListMoviesFromDB(List<Movie> moviesFromDB);

    void controlProgressBar(boolean isStart);
}
