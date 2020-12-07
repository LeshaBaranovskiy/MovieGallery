package com.example.moviegallery.screens.list;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.moviegallery.R;
import com.example.moviegallery.adapters.MoviesAdapter;
import com.example.moviegallery.pojo.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MoviesListActivity extends AppCompatActivity implements MovieListView {

    private RecyclerView recyclerView;

    private ImageView imageViewNoInternet;
    private ProgressBar progressBar;

    private LinearLayoutManager linearLayoutManager;

    private MoviesAdapter moviesAdapter;

    private MoviesListPresenter presenter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        if (presenter.getCurrentMode() == 0) {
            presenter.changeToLightMode();
            menu.removeItem(R.id.lightMode);
        } else {
            presenter.changeToDarkMode();
            menu.removeItem(R.id.darkMode);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.lightMode :
                presenter.changeToLightModeSharedPreferences();
                break;
            case R.id.darkMode :
                presenter.changeToDarkModeSharedPreferences();
                break;
        }
        invalidateOptionsMenu();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_list);

        presenter =  new MoviesListPresenter(this, getApplicationContext());
        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        moviesAdapter = new MoviesAdapter();

        recyclerView = findViewById(R.id.recyclerViewMoviesList);
        imageViewNoInternet = findViewById(R.id.imageViewNoInternet);
        progressBar = findViewById(R.id.progressBar);

        DrawableCompat.setTint(progressBar.getIndeterminateDrawable(), getResources().getColor(R.color.colorText));

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(moviesAdapter);

        if (savedInstanceState != null && Objects.requireNonNull(savedInstanceState.getParcelableArrayList("movies")).size() > 0) {
            ArrayList<Movie> movies = (ArrayList<Movie>) savedInstanceState.getSerializable("movies");
            moviesAdapter.setMovies(movies);
        } else {
            progressBar.setVisibility(View.VISIBLE);
            presenter.loadMoviesFromAPI();
        }

        moviesAdapter.setOnMovieClickListener(position -> presenter.onMovieClickAction(moviesAdapter, getApplicationContext(), position));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("movies", new ArrayList<>(moviesAdapter.getMovies()));
    }

    @Override
    public void getListMoviesFromApi(List<Movie> movies) {
        presenter.deleteAllMovies();
        presenter.deleteAllRaitings();
        moviesAdapter.setMovies(movies);
        presenter.insertMoviesInDB(movies);
    }

    @Override
    public void getListMoviesFromDB(List<Movie> moviesFromDB) {
        if (moviesFromDB.size() > 0) {
            moviesAdapter.setMovies(moviesFromDB);
        } else {
            imageViewNoInternet.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void controlProgressBar(boolean isStart) {
        if (isStart) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
