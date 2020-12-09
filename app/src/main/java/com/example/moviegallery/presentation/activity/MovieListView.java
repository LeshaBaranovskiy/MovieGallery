package com.example.moviegallery.presentation.activity;

import com.example.moviegallery.data.entities.MovieEntity;

import java.util.List;

public interface MovieListView {
    void setMovies(List<MovieEntity> movies);

    void controlProgressBar(boolean isStart);
}
