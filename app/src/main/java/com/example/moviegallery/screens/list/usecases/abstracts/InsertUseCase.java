package com.example.moviegallery.screens.list.usecases.abstracts;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public abstract class InsertUseCase<T> {

    public void execute(T t) {
        insertData(t).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }

    public abstract Completable insertData(T t);
}
