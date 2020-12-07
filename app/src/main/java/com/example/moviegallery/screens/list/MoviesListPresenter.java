package com.example.moviegallery.screens.list;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.moviegallery.adapters.MoviesAdapter;
import com.example.moviegallery.model.database.MoviesDAO;
import com.example.moviegallery.model.database.MoviesDBContract;
import com.example.moviegallery.model.database.movies_entry.MoviesDBHelper;
import com.example.moviegallery.pojo.Movie;
import com.example.moviegallery.pojo.Rating;
import com.example.moviegallery.screens.detail.DetailActivity;
import com.example.moviegallery.screens.list.usecases.GetAllMoviesUseCase;
import com.example.moviegallery.screens.list.usecases.MovieUseCase;
import com.example.moviegallery.util.Constants;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class MoviesListPresenter {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private MovieListView movieListView;

    private SharedPreferences currentMode;

    private Context context;

    private MoviesDBHelper moviesDBHelper;

    private SQLiteDatabase database;

    private GetAllMoviesUseCase getAllMoviesUseCase;

    private MoviesDAO moviesDAO;

    public MoviesListPresenter(MovieListView movieListView, Context context) {
        this.movieListView = movieListView;
        this.context = context;
        moviesDBHelper = new MoviesDBHelper(context);
        getAllMoviesUseCase = new GetAllMoviesUseCase();
        moviesDAO = new MoviesDAO(context);
    }

    public void loadMoviesFromAPI() {
        getAllMoviesUseCase.execute(new MovieUseCase<List<Movie>>() {
            @Override
            public void accept(List<Movie> data) {
                movieListView.controlProgressBar(true);
                movieListView.getListMoviesFromApi(data);
                for (Movie movie : data) {
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
                movieListView.controlProgressBar(false);
            }

            @Override
            public void acceptThrowable() {
                movieListView.controlProgressBar(true);
                movieListView.getListMoviesFromDB(getMoviesFromDB());
                movieListView.controlProgressBar(false);
            }
        });
    }

    public List<Movie> getMoviesFromDB() {
        return moviesDAO.getMoviesFromDB();
    }

    public void insertRatingsInDB(List<Rating> ratings) {
        moviesDAO.insertRatingsInDB(ratings);
    }

    public void deleteAllRaitings() {
        moviesDAO.deleteAllRaitings();
    }

    public void insertMoviesInDB(List<Movie> movies) {
        moviesDAO.insertMoviesInDB(movies);
    }

    public void deleteAllMovies() {
        moviesDAO.deleteAllMovies();
    }

    public void onMovieClickAction(MoviesAdapter moviesAdapter, Context context, int position) {
        Movie movie = moviesAdapter.getMovies().get(position);
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("movie", movie);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void changeToLightMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    public void changeToLightModeSharedPreferences() {
        currentMode = context.getSharedPreferences(Constants.MODE, Context.MODE_PRIVATE);
        currentMode.edit().putInt(Constants.MODE, 0).apply();
    }

    public void changeToDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
    }

    public void changeToDarkModeSharedPreferences() {
        currentMode = context.getSharedPreferences(Constants.MODE, Context.MODE_PRIVATE);
        currentMode.edit().putInt(Constants.MODE, 1).apply();
    }

    public int getCurrentMode() {
        currentMode = context.getSharedPreferences(Constants.MODE, Context.MODE_PRIVATE);
        return currentMode.getInt(Constants.MODE, 0);
    }

    public void onDestroy() {
        moviesDBHelper.close();
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
