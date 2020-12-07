package com.example.moviegallery.screens.list.usecases;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public abstract class UseCase<T> {

    public void execute(MovieUseCase<T> movieUseCase) {
        Observable<T> data = getData();

        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieUseCase::accept,
                        throwable -> movieUseCase.acceptThrowable());
    }

    abstract Observable<T> getData();
}
