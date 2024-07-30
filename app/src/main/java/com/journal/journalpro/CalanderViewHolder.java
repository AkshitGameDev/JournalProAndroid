package com.journal.journalpro;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalanderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final TextView dayOfMOnth;
    private final CalanderAdapter.OnItemListener onItemListener;

    public CalanderViewHolder(@NonNull View itemView, CalanderAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayOfMOnth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAbsoluteAdapterPosition(), (String) dayOfMOnth.getText());
    }
}
