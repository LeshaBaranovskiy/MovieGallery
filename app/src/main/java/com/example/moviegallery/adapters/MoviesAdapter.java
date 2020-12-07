package com.example.moviegallery.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviegallery.R;
import com.example.moviegallery.pojo.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private List<Movie> movies = new ArrayList<>();

    private OnMovieClickListener onMovieClickListener;

    public void setOnMovieClickListener(OnMovieClickListener onMovieClickListener) {
        this.onMovieClickListener = onMovieClickListener;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    public interface OnMovieClickListener {
        void onMovieClick(int position);
    }

    public List<Movie> getMovies() {
        return movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent,false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.textViewTitle.setText(movie.getTitle());
        holder.textViewGenre.setText(movie.getGenre());
        holder.textViewProducer.setText(movie.getDirector());
        holder.textViewRatingIMDB.setText(movie.getImdbRating());
        if (movie.getPoster().equals("N/A")) {
            Picasso.get().load(R.drawable.placeholder).into(holder.imageViewPoster);
        } else {
            Picasso.get().load(movie.getPoster()).into(holder.imageViewPoster);
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewPoster;
        private TextView textViewTitle;
        private TextView textViewGenre;
        private TextView textViewProducer;
        private TextView textViewRatingIMDB;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            imageViewPoster.setClipToOutline(true);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewGenre = itemView.findViewById(R.id.textViewGenre);
            textViewProducer = itemView.findViewById(R.id.textViewListProducer);
            textViewRatingIMDB = itemView.findViewById(R.id.textViewRatingListIMDB);

            itemView.setOnClickListener(v -> {
                if (onMovieClickListener != null) {
                    onMovieClickListener.onMovieClick(getAdapterPosition());
                }
            });
        }
    }
}
