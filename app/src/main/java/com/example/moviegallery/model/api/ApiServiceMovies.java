package com.example.moviegallery.model.api;

import com.example.moviegallery.pojo.Movie;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiServiceMovies {
    @GET("dbs/movies.json")
    Single<List<Movie>> getMovies();
}
