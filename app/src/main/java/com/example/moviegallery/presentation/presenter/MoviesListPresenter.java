package com.example.moviegallery.presentation.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.moviegallery.presentation.adapters.MoviesAdapter;
import com.example.moviegallery.data.entities.MovieEntity;
import com.example.moviegallery.domain.nodel.Rating;
import com.example.moviegallery.presentation.activity.DetailActivity;
import com.example.moviegallery.domain.usecase.DeleteAllMovies;
import com.example.moviegallery.domain.usecase.DeleteAllRaitings;
import com.example.moviegallery.domain.usecase.GetAllMoviesFromAPI;
import com.example.moviegallery.domain.usecase.GetAllMoviesFromDB;
import com.example.moviegallery.domain.usecase.InsertMoviesInDB;
import com.example.moviegallery.domain.usecase.InsertRatingsInDB;
import com.example.moviegallery.domain.usecase.abstracts.GetUseCase;
import com.example.moviegallery.presentation.activity.MovieListView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class MoviesListPresenter implements MoviesListPresenterBase {

    public static final String MODE = "current_mode";

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MovieListView movieListView;
    private SharedPreferences currentMode;
    private Context context;

    private GetAllMoviesFromAPI getAllMoviesFromAPIUseCase;
    private GetAllMoviesFromDB getAllMoviesFromDBUseCase;
    private InsertRatingsInDB insertRatingsInDB;
    private InsertMoviesInDB insertMoviesInDB;
    private DeleteAllMovies deleteAllMovies;
    private DeleteAllRaitings deleteAllRaitings;

    public MoviesListPresenter(MovieListView movieListView, Context context) {
        this.movieListView = movieListView;
        this.context = context;
        getAllMoviesFromAPIUseCase = new GetAllMoviesFromAPI();
        getAllMoviesFromDBUseCase = new GetAllMoviesFromDB(context);
        insertRatingsInDB = new InsertRatingsInDB(context);
        insertMoviesInDB = new InsertMoviesInDB(context);
        deleteAllMovies = new DeleteAllMovies(context);
        deleteAllRaitings = new DeleteAllRaitings(context);
    }

    public void getMovies() {
        movieListView.controlProgressBar(true);

        compositeDisposable.add(getAllMoviesFromAPIUseCase.execute(new GetUseCase.GetMovieUseCase<List<MovieEntity>>() {
            @Override
            public void accept(List<MovieEntity> data) {
                movieListView.setMovies(data);
                movieListView.controlProgressBar(false);

                // TODO: this code segment should be implemented in some sort of CacheManager and executed under Data or Domain level
                {
                    for (MovieEntity movie : data) {
                        List<Rating> r = new ArrayList<>();
                        List<Rating> movieRatings = movie.getRatings();
                        if (movieRatings != null) {
                            for (Rating rating : movieRatings) {
                                rating.setImdbID(movie.getImdbID());
                                r.add(rating);
                            }
                            insertRatingsInDB(r);
                        }
                    }

                    deleteAllMovies();
                    deleteAllRaitings();
                    insertMoviesInDB(data);
                }
            }

            @Override
            public void acceptThrowable() {
                movieListView.controlProgressBar(true);
                getMoviesFromCache();
                movieListView.controlProgressBar(false);
            }
        }));
    }

    private void getMoviesFromCache() {
        compositeDisposable.add(getAllMoviesFromDBUseCase.execute(new GetUseCase.GetMovieUseCase<List<MovieEntity>>() {
            @Override
            public void accept(List<MovieEntity> data) {
                movieListView.setMovies(data);
            }
            @Override
            public void acceptThrowable() {
                Log.i("ttt", "wrong");
            }
        }));
    }

    public void insertMoviesInDB(List<MovieEntity> movies) {
        insertMoviesInDB.execute(movies);
    }

    public void deleteAllMovies() {
        deleteAllMovies.execute();
    }

    public void insertRatingsInDB(List<Rating> ratings) {
        insertRatingsInDB.execute(ratings);
    }

    public void deleteAllRaitings() {
        deleteAllRaitings.execute();
    }

    public void onMovieClickAction(MoviesAdapter moviesAdapter, Context context, int position) {
        MovieEntity movie = moviesAdapter.getMovies().get(position);
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("movie", movie);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void changeToLightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    public void changeToLightModeSharedPreferences() {
        currentMode = context.getSharedPreferences(MODE, Context.MODE_PRIVATE);
        currentMode.edit().putInt(MODE, 0).apply();
    }

    public void changeToDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    public void changeToDarkModeSharedPreferences() {
        currentMode = context.getSharedPreferences(MODE, Context.MODE_PRIVATE);
        currentMode.edit().putInt(MODE, 1).apply();
    }

    public int getCurrentMode() {
        currentMode = context.getSharedPreferences(MODE, Context.MODE_PRIVATE);
        return currentMode.getInt(MODE, 0);
    }

    public void onDestroy() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    @Override
    public void unbind() {

    }
}
