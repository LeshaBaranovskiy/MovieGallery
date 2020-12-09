package com.example.moviegallery.screens.list.usecases;

import android.content.Context;

import com.example.moviegallery.model.database.MoviesDAO;
import com.example.moviegallery.pojo.Movie;
import com.example.moviegallery.screens.list.usecases.abstracts.InsertUseCase;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class InsertMoviesInDB extends InsertUseCase<List<Movie>> {
    private MoviesDAO moviesDAO;

    public InsertMoviesInDB(Context context) {
        moviesDAO = new MoviesDAO(context);
    }

    @Override
    public Completable insertData(List<Movie> movies) {
        return Completable.fromRunnable(() -> moviesDAO.insertMoviesInDB(movies));
    }
}
