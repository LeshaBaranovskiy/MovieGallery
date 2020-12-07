package com.example.moviegallery.screens.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviegallery.R;
import com.example.moviegallery.adapters.CastAdapter;
import com.example.moviegallery.adapters.RatingsAdapter;
import com.example.moviegallery.adapters.WritersAdapter;
import com.example.moviegallery.pojo.Movie;
import com.example.moviegallery.pojo.Rating;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity implements Serializable, DetailView {

    private Movie movie;

    private List<Rating> ratings = new ArrayList<>();

    private TextView textViewGenre;
    private TextView textViewTitle;
    private TextView textViewReleased;
    private TextView textViewRuntime;
    private TextView textViewDirector;
    private TextView textViewStory;

    private ImageView imageViewPosterDetail;

    private RecyclerView recyclerViewRatings;
    private RatingsAdapter ratingsAdapter;

    private RecyclerView recyclerViewWriters;
    private WritersAdapter writersAdapter;

    private RecyclerView recyclerViewCast;
    private CastAdapter castAdapter;

    private DetailPresenter detailPresenter;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
//                NavUtils.navigateUpFromSameTask(this);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textViewGenre = findViewById(R.id.textViewGenre);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewReleased = findViewById(R.id.textViewReleased);
        textViewRuntime = findViewById(R.id.textViewRuntime);
        textViewDirector = findViewById(R.id.textViewDirector);
        textViewStory = findViewById(R.id.textViewStory);
        imageViewPosterDetail = findViewById(R.id.imageViewDetailPoster);

        recyclerViewRatings = findViewById(R.id.recyclerViewRatings);
        ratingsAdapter = new RatingsAdapter();

        recyclerViewWriters = findViewById(R.id.recyclerViewWriters);
        writersAdapter = new WritersAdapter();

        recyclerViewCast = findViewById(R.id.recyclerViewCast);
        castAdapter = new CastAdapter();

        recyclerViewRatings.setAdapter(ratingsAdapter);
        recyclerViewRatings.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        recyclerViewWriters.setAdapter(writersAdapter);
        recyclerViewWriters.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        recyclerViewCast.setAdapter(castAdapter);
        recyclerViewCast.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        detailPresenter = new DetailPresenter(this);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        if (getIntent().getSerializableExtra("movie") != null) {
            movie = (Movie) getIntent().getSerializableExtra("movie");

            ratings = movie.getRatings();
            ratingsAdapter.setRatings(ratings);

            writersAdapter.setWriters(detailPresenter.makeListOfWriters(movie));

            castAdapter.setCasts(detailPresenter.makeListOfCast(movie));

            textViewTitle.setText(movie.getTitle());
            textViewGenre.setText(movie.getGenre());
            textViewReleased.setText(movie.getReleased());
            textViewRuntime.setText(movie.getRuntime());
            textViewDirector.setText(movie.getDirector());
            textViewStory.setText(movie.getPlot());
            if (movie.getPoster().equals("N/A")) {
                Picasso.get().load(R.drawable.placeholder).into(imageViewPosterDetail);
            } else {
                Picasso.get().load(movie.getPoster()).into(imageViewPosterDetail);
            }
        } else {
            finish();
        }
    }
}
