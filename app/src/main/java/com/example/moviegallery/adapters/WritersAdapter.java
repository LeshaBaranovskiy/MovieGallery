package com.example.moviegallery.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviegallery.R;
import com.example.moviegallery.pojo.Writer;

import java.util.ArrayList;
import java.util.List;

public class WritersAdapter extends RecyclerView.Adapter<WritersAdapter.WritersViewHolder> {

    List<Writer> writers = new ArrayList<>();

    public List<Writer> getWriters() {
        return writers;
    }

    public void setWriters(List<Writer> writers) {
        this.writers = writers;
    }

    @NonNull
    @Override
    public WritersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.writer_item, parent, false);
        return new WritersViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WritersViewHolder holder, int position) {
        Writer writer = writers.get(position);
        holder.textViewWriterName.setText(writer.getName());
        holder.textViewType.setText(writer.getType());
    }

    @Override
    public int getItemCount() {
        return writers.size();
    }

    public class WritersViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewType;
        private TextView textViewWriterName;

        public WritersViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewType = itemView.findViewById(R.id.textViewType);
            textViewWriterName = itemView.findViewById(R.id.textViewWriterName);
        }
    }
}
