package com.example.moviegallery.domain.usecase;

import android.content.Context;

import com.example.moviegallery.data.database.MoviesDAO;
import com.example.moviegallery.data.entities.MovieEntity;
import com.example.moviegallery.domain.usecase.abstracts.InsertUseCase;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class InsertMoviesInDB extends InsertUseCase<List<MovieEntity>> {
    private MoviesDAO moviesDAO;

    public InsertMoviesInDB(Context context) {
        moviesDAO = new MoviesDAO(context);
    }

    @Override
    public Completable insertData(List<MovieEntity> movies) {
        return Completable.fromRunnable(() -> moviesDAO.insertMoviesInDB(movies));
    }
}
