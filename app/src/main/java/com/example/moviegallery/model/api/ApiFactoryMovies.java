package com.example.moviegallery.model.api;

import android.util.Log;

import com.example.moviegallery.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactoryMovies {
    private Retrofit retrofit = null;

    private static ApiFactoryMovies apiFactoryMovies;

    private ApiFactoryMovies() {
        if (retrofit == null) {
            Gson gson = new GsonBuilder().setLenient().create();

            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .baseUrl(Constants.BASE_URL).build();
        }
    }

    public static ApiFactoryMovies getInstance() {
        if (apiFactoryMovies == null) {
            apiFactoryMovies = new ApiFactoryMovies();
        }
        return apiFactoryMovies;
    }

    public ApiServiceMovies getApiServiceMovies() {
        return retrofit.create(ApiServiceMovies.class);
    }
}