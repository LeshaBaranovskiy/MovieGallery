package com.example.moviegallery.data.api;

import com.example.moviegallery.data.entities.MovieEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiServiceMovies {
    @GET("dbs/movies.json")
    Single<List<MovieEntity>> getMovies();
}
