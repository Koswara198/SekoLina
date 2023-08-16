package com.example.er;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.ViewHolder> {

    private List<School> schools;

    public SchoolAdapter(List<School> schools) {
        this.schools = schools;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_school, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        School school = schools.get(position);
//        holder.namaTextView.setText(school.getBentukpendidikan());
        holder.namaTextView.setText(school.getNama());
    }

    @Override
    public int getItemCount() {
        return schools.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaTextView = itemView.findViewById(R.id.namaTextView);
        }
    }
}
