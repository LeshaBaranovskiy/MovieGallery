package com.example.moviegallery.domain.usecase;

import android.content.Context;

import com.example.moviegallery.data.database.MoviesDAO;
import com.example.moviegallery.data.entities.MovieEntity;
import com.example.moviegallery.domain.usecase.abstracts.GetUseCase;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class GetAllMoviesFromDB extends GetUseCase<List<MovieEntity>> {
    private MoviesDAO moviesDAO;

    public GetAllMoviesFromDB(Context context) {
        moviesDAO = new MoviesDAO(context);
    }

    @Override
    public Observable<List<MovieEntity>> getData() {
        return moviesDAO.getMoviesFromDB();
    }
}
