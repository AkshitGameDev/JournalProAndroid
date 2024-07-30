package com.journal.journalpro;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalanderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ArrayList<LocalDate> days;
    public final View parentView;
    public final TextView dayOfMOnth;
    private final CalanderAdapter.OnItemListener onItemListener;

    public CalanderViewHolder(@NonNull View itemView, CalanderAdapter.OnItemListener onItemListener, ArrayList<LocalDate> days) {
        super(itemView);
        this.days = days;
        this.parentView = itemView.findViewById(R.id.parentView);
        dayOfMOnth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAbsoluteAdapterPosition(),days.get(getAbsoluteAdapterPosition()));
    }
}
