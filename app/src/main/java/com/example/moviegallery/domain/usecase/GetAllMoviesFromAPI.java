package com.example.moviegallery.domain.usecase;

import com.example.moviegallery.data.api.ApiFactoryMovies;
import com.example.moviegallery.data.api.ApiServiceMovies;
import com.example.moviegallery.data.entities.MovieEntity;
import com.example.moviegallery.domain.usecase.abstracts.GetUseCase;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class GetAllMoviesFromAPI extends GetUseCase<List<MovieEntity>> {

    @Override
    public Observable<List<MovieEntity>> getData() {
        ApiFactoryMovies apiFactoryMovies = ApiFactoryMovies.getInstance();
        ApiServiceMovies apiServiceMovies = apiFactoryMovies.getApiServiceMovies();

        return apiServiceMovies.getMovies().toObservable();
    }
}
