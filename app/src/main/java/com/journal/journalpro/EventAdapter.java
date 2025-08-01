package com.journal.journalpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;


public class EventAdapter extends ArrayAdapter<Event>{

    public EventAdapter(@NonNull Context context, List<Event> events) {
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Event event = getItem(position);

        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_call, parent, false);

        TextView eventCell = convertView.findViewById(R.id.EventCellTV);
        TextView eventDate = convertView.findViewById(R.id.EventDateTV);

        eventCell.setText(event.getName());
        eventDate.setText((event.getTime()).toString());



        return convertView;
    }
}
