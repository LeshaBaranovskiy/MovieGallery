package com.example.moviegallery.screens.list.usecases.abstracts;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public abstract class GetUseCase<T> {

    public interface GetMovieUseCase<T> {
        void accept(T data);
        void acceptThrowable();
    }

    public void execute(GetMovieUseCase<T> getMovieUseCase) {
        Observable<T> data = getData();

        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getMovieUseCase::accept,
                        throwable -> getMovieUseCase.acceptThrowable());
    }

    public abstract Observable<T> getData();
}
