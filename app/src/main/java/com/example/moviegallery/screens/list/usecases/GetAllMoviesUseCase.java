package com.example.moviegallery.screens.list.usecases;

import android.content.Context;

import com.example.moviegallery.model.api.ApiFactoryMovies;
import com.example.moviegallery.model.api.ApiServiceMovies;
import com.example.moviegallery.pojo.Movie;
import com.example.moviegallery.pojo.Rating;
import com.example.moviegallery.screens.list.MovieListView;
import com.example.moviegallery.screens.list.MoviesListPresenter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class GetAllMoviesUseCase extends UseCase<List<Movie>> {

    @Override
    Observable<List<Movie>> getData() {
        ApiFactoryMovies apiFactoryMovies = ApiFactoryMovies.getInstance();
        ApiServiceMovies apiServiceMovies = apiFactoryMovies.getApiServiceMovies();

        return apiServiceMovies.getMovies().toObservable();
    }
}
