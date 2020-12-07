package com.example.moviegallery.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviegallery.R;
import com.example.moviegallery.pojo.Rating;

import java.util.ArrayList;
import java.util.List;

public class RatingsAdapter extends RecyclerView.Adapter<RatingsAdapter.RatingViewHolder> {

    private List<Rating> ratings = new ArrayList<>();

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    @NonNull
    @Override
    public RatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_item, parent, false);
        return new RatingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingViewHolder holder, int position) {
        Rating rating = ratings.get(position);
        if (rating.getSource().equals("Internet Movie Database")) {
            holder.textViewSource.setText("IMDB");
        } else {
            holder.textViewSource.setText(rating.getSource());
        }
        holder.textViewRating.setText(rating.getValue());
    }

    @Override
    public int getItemCount() {
        if (ratings != null) {
            return ratings.size();
        }
        return 0;
    }

    public class RatingViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewSource;
        private TextView textViewRating;

        public RatingViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewSource = itemView.findViewById(R.id.textViewSource);
            textViewRating = itemView.findViewById(R.id.textViewRating);
        }
    }
}
