package com.example.moviegallery.screens.list.usecases;

import android.content.Context;

import com.example.moviegallery.model.database.MoviesDAO;
import com.example.moviegallery.screens.list.usecases.abstracts.DeleteUseCase;

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
