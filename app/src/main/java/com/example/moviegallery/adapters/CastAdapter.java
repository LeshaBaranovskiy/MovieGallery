package com.example.moviegallery.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviegallery.R;
import com.example.moviegallery.pojo.Cast;

import java.util.ArrayList;
import java.util.List;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder> {

    List<Cast> casts = new ArrayList<>();

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_item, parent, false);
        return new CastViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        Cast cast = casts.get(position);
        holder.textViewCastName.setText(cast.getCastName());
    }

    @Override
    public int getItemCount() {
        return casts.size();
    }

    public class CastViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewCastName;
        private ImageView imageViewActorImage;

        public CastViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCastName = itemView.findViewById(R.id.textViewCastName);
            imageViewActorImage = itemView.findViewById(R.id.imageViewCastImage);
        }
    }
}
