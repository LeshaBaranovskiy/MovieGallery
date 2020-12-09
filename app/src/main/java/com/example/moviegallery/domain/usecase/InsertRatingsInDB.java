package com.example.moviegallery.domain.usecase;

import android.content.Context;

import com.example.moviegallery.data.database.MoviesDAO;
import com.example.moviegallery.domain.nodel.Rating;
import com.example.moviegallery.domain.usecase.abstracts.InsertUseCase;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class InsertRatingsInDB extends InsertUseCase<List<Rating>> {
    private MoviesDAO moviesDAO;

    public InsertRatingsInDB(Context context) {
        moviesDAO = new MoviesDAO(context);
    }

    @Override
    public Completable insertData(List<Rating> ratings) {
        return Completable.fromRunnable(() -> moviesDAO.insertRatingsInDB(ratings));
    }
}
