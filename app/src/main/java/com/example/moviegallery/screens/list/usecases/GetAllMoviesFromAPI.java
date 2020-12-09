package com.example.moviegallery.screens.list.usecases;

import com.example.moviegallery.model.api.ApiFactoryMovies;
import com.example.moviegallery.model.api.ApiServiceMovies;
import com.example.moviegallery.pojo.Movie;
import com.example.moviegallery.screens.list.usecases.abstracts.GetUseCase;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class GetAllMoviesFromAPI extends GetUseCase<List<Movie>> {

    @Override
    public Observable<List<Movie>> getData() {
        ApiFactoryMovies apiFactoryMovies = ApiFactoryMovies.getInstance();
        ApiServiceMovies apiServiceMovies = apiFactoryMovies.getApiServiceMovies();

        return apiServiceMovies.getMovies().toObservable();
    }
}
