package com.journal.journalpro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class notesRVadapter extends FirebaseRecyclerAdapter<notesModel, notesRVadapter.MyViewHolder> {

    Context context;

    public notesRVadapter( @NonNull FirebaseRecyclerOptions<notesModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull notesModel model) {
        holder.title.setText(model.getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start a new activity or fragment when the item is clicked
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("title", model.getTitle());
                intent.putExtra("description", model.getDisc());
                intent.putExtra("pos",position);
                intent.putExtra("key", getRef(holder.getAbsoluteAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });
        holder.description.setText(model.getDisc());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);
        return new MyViewHolder(view);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }
    }
}
