package com.journal.journalpro;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalanderAdapter extends RecyclerView.Adapter<CalanderViewHolder> {
    private final ArrayList<LocalDate> days;
    private final OnItemListener onItemListener;

    public CalanderAdapter(ArrayList<LocalDate> days, OnItemListener onItemListener) {
        this.days = days;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalanderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calander_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = parent.getHeight() / 7; // 7 days in a week
        view.setLayoutParams(layoutParams);
        return new CalanderViewHolder(view, onItemListener, days);
    }

    @Override
    public void onBindViewHolder(@NonNull CalanderViewHolder holder, int position) {
        final LocalDate date = days.get(position);

        if (date == null) {
            holder.dayOfMOnth.setText("");
        } else {
            holder.dayOfMOnth.setText(String.valueOf(date.getDayOfMonth()));
            if (date.equals(CalanderUtils.SelectedDate)) {
                holder.parentView.setBackgroundColor(Color.LTGRAY); // Highlight selected date
            }
        }
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, LocalDate date);
    }
}
