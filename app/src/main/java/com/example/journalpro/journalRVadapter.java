package com.example.journalpro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class journalRVadapter extends RecyclerView.Adapter<journalRVadapter.MyViewHolder> {
    Context context;
    ArrayList<journalModel> journalModels;

    public journalRVadapter(Context context, ArrayList<journalModel> journalModels){
        this.context = context;
        this.journalModels = journalModels;
    }

    @NonNull
    @Override
    public journalRVadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new journalRVadapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull journalRVadapter.MyViewHolder holder, int position) {
        holder.title.setText(journalModels.get(position).getTitle());
        String str = journalModels.get(position).getDescription();
        if(str.length() > 50){
            str = str.substring(0,47) + "...";
        }
        holder.description.setText(str);
    }

    @Override
    public int getItemCount() {
        return journalModels.size();
    }

    public  static  class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, description;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);

        }
    }

}
