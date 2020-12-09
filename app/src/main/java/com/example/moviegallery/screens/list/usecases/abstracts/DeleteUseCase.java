package com.example.moviegallery.screens.list.usecases.abstracts;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public abstract class DeleteUseCase {
    public void execute() {
        deleteData().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public abstract Completable deleteData();
}
