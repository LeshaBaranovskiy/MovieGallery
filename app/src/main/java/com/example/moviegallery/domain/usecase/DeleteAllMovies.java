package com.example.moviegallery.domain.usecase;

import android.content.Context;

import com.example.moviegallery.data.database.MoviesDAO;
import com.example.moviegallery.domain.usecase.abstracts.DeleteUseCase;
import io.reactivex.rxjava3.core.Completable;

public class DeleteAllMovies extends DeleteUseCase {
    private MoviesDAO moviesDAO;

    public DeleteAllMovies(Context context) {
        moviesDAO = new MoviesDAO(context);
    }

    @Override
    public Completable deleteData() {
        return Completable.fromRunnable(() -> moviesDAO.deleteAllMovies());
    }
}
