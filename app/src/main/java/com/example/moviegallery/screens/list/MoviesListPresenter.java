package com.example.moviegallery.screens.list;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;

import com.example.moviegallery.adapters.MoviesAdapter;
import com.example.moviegallery.model.database.MoviesDAO;
import com.example.moviegallery.pojo.Movie;
import com.example.moviegallery.pojo.Rating;
import com.example.moviegallery.screens.detail.DetailActivity;
import com.example.moviegallery.screens.list.usecases.DeleteAllMovies;
import com.example.moviegallery.screens.list.usecases.DeleteAllRaitings;
import com.example.moviegallery.screens.list.usecases.GetAllMoviesFromAPI;
import com.example.moviegallery.screens.list.usecases.GetAllMoviesFromDB;
import com.example.moviegallery.screens.list.usecases.InsertMoviesInDB;
import com.example.moviegallery.screens.list.usecases.InsertRatingsInDB;
import com.example.moviegallery.screens.list.usecases.abstracts.GetUseCase;
import com.example.moviegallery.util.Constants;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class MoviesListPresenter {

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

    private MoviesDAO moviesDAO;

    public static final String MODE = "current_mode";

    public MoviesListPresenter(MovieListView movieListView, Context context) {
        this.movieListView = movieListView;
        this.context = context;
        getAllMoviesFromAPIUseCase = new GetAllMoviesFromAPI();
        getAllMoviesFromDBUseCase = new GetAllMoviesFromDB(context);
        insertRatingsInDB = new InsertRatingsInDB(context);
        insertMoviesInDB = new InsertMoviesInDB(context);
        deleteAllMovies = new DeleteAllMovies(context);
        deleteAllRaitings = new DeleteAllRaitings(context);

        moviesDAO = new MoviesDAO(context);
    }

    public void loadMoviesFromAPI() {
        getAllMoviesFromAPIUseCase.execute(new GetUseCase.GetMovieUseCase<List<Movie>>() {
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
                getMoviesFromDB();
                movieListView.controlProgressBar(false);
            }
        });
    }

    public void getMoviesFromDB() {
        getAllMoviesFromDBUseCase.execute(new GetUseCase.GetMovieUseCase<List<Movie>>() {
            @Override
            public void accept(List<Movie> data) {
                movieListView.getListMoviesFromDB(data);
            }
            @Override
            public void acceptThrowable() {
                Log.i("ttt", "wrong");
            }
        });
    }

    public void insertMoviesInDB(List<Movie> movies) {
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
}
