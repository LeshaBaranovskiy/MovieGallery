package com.example.moviegallery.presentation.presenter;

import com.example.moviegallery.presentation.view.View;

public interface Presenter<T extends View> {

    void unbind();
}
