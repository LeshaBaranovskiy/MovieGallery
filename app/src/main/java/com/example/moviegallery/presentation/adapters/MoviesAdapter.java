package com.example.moviegallery.presentation.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviegallery.R;
import com.example.moviegallery.data.entities.MovieEntity;
import com.example.moviegallery.presentation.viewholder.MovieViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<MovieEntity> movies = new ArrayList<>();
    private MovieViewHolder.OnMovieClickListener onMovieClickListener;

    public void setOnMovieClickListener(MovieViewHolder.OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    public void setMovies(List<MovieEntity> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public List<MovieEntity> getMovies() {
        return movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent,false);
        return new MovieViewHolder(v, onMovieClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        MovieEntity movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
}
