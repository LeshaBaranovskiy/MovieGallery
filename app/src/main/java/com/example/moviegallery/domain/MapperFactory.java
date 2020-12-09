package com.example.moviegallery.domain;

import com.example.moviegallery.data.entities.MovieEntity;
import com.example.moviegallery.domain.nodel.Cast;
import com.example.moviegallery.domain.nodel.Movie;

import java.util.ArrayList;
import java.util.List;

public class MapperFactory {

    Mapper<MovieEntity, Movie> getMovieMapper() {
        return movieEntity -> {
            Movie movie = new Movie();
            movie.setCast(getCastMapper().transform(movieEntity.getActors()));
            return movie;
        };
    }

    Mapper<String, List<Cast>> getCastMapper() {
        return s -> {
            return new ArrayList<>();
        };
    }
}
