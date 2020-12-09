package com.example.moviegallery.screens.list.usecases;

import android.content.Context;

import com.example.moviegallery.model.database.MoviesDAO;
import com.example.moviegallery.pojo.Movie;
import com.example.moviegallery.screens.list.usecases.abstracts.GetUseCase;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class GetAllMoviesFromDB extends GetUseCase<List<Movie>> {
    private MoviesDAO moviesDAO;

    public GetAllMoviesFromDB(Context context) {
        moviesDAO = new MoviesDAO(context);
    }

    @Override
    public Observable<List<Movie>> getData() {
        return moviesDAO.getMoviesFromDB();
    }
}
