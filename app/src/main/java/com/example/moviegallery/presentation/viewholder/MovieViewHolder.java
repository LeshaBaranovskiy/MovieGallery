package com.example.moviegallery.presentation.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviegallery.R;
import com.example.moviegallery.data.entities.MovieEntity;
import com.example.moviegallery.presentation.adapters.MoviesAdapter;
import com.squareup.picasso.Picasso;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageViewPoster;
    private TextView textViewTitle;
    private TextView textViewGenre;
    private TextView textViewProducer;
    private TextView textViewRatingIMDB;

    private OnMovieClickListener onMovieClickListener;

    public MovieViewHolder(@NonNull View itemView, OnMovieClickListener onMovieClickListener) {
        super(itemView);
        imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
        imageViewPoster.setClipToOutline(true);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);
        textViewGenre = itemView.findViewById(R.id.textViewGenre);
        textViewProducer = itemView.findViewById(R.id.textViewListProducer);
        textViewRatingIMDB = itemView.findViewById(R.id.textViewRatingListIMDB);
        this.onMovieClickListener = onMovieClickListener;

        itemView.setOnClickListener(v -> {
            if (onMovieClickListener != null) {
                onMovieClickListener.onMovieClick(getAdapterPosition());
            }
        });
    }

    public void bind(MovieEntity movie) {
        textViewTitle.setText(movie.getTitle());
        textViewGenre.setText(movie.getGenre());
        textViewProducer.setText(movie.getDirector());
        textViewRatingIMDB.setText(movie.getImdbRating());
        if (movie.getPoster().equals("N/A")) {
            Picasso.get().load(R.drawable.placeholder).into(imageViewPoster);
        } else {
            Picasso.get().load(movie.getPoster()).into(imageViewPoster);
        }
    }

    public interface OnMovieClickListener {
        void onMovieClick(int position);
    }
}
