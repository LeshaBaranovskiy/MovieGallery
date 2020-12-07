package com.example.moviegallery.screens.list.usecases;

public interface MovieUseCase<T> {
    void accept(T data);
    void acceptThrowable();
}
