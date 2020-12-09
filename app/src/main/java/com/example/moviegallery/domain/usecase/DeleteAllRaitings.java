package com.example.moviegallery.domain.usecase;

import android.content.Context;

import com.example.moviegallery.data.database.MoviesDAO;
import com.example.moviegallery.domain.usecase.abstracts.DeleteUseCase;

import io.reactivex.rxjava3.core.Completable;

public class DeleteAllRaitings extends DeleteUseCase {
    private MoviesDAO moviesDAO;

    public DeleteAllRaitings(Context context) {
        moviesDAO = new MoviesDAO(context);
    }

    @Override
    public Completable deleteData() {
        return Completable.fromRunnable(() -> moviesDAO.deleteAllRaitings());
    }
}
