package com.journal.journalpro;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {

    private TextInputEditText Title, Discription;
    private Button Update, Delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_details);

        Title = findViewById(R.id.d_title);
        Discription = findViewById(R.id.d_Disc);
        Update = findViewById(R.id.d_Update);
        Delete = findViewById(R.id.d_Delete);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String key = getIntent().getStringExtra("key"); // Retrieve the Firebase key

        Title.setText(title);
        Discription.setText(description);

        Delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailsActivity.this);
                builder.setTitle("Are you Sure?");
                builder.setMessage("Deleted data can't be Undo.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Journals").child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(DetailsActivity.this, "Delete Successful.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(DetailsActivity.this, "Delete Failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(DetailsActivity.this, "Cancelled.", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.show();
            }
        });

        Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a map to store the data
                Map<String, Object> map = new HashMap<>();

                // Get the text from the Title TextInputEditText and provide a default value if empty
                String titleText = Title.getText().toString().isEmpty() ? " " : Title.getText().toString();
                // Get the text from the Description TextInputEditText and provide a default value if empty
                String descriptionText = Discription.getText().toString().isEmpty() ? " " : Discription.getText().toString();

                // Add the text to the map
                map.put("Title", titleText);
                map.put("disc", descriptionText);

                // Update the children in the Firebase database
                FirebaseDatabase.getInstance().getReference()
                        .child("Journals")
                        .child(key) // Use the key to update the specific item
                        .updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(DetailsActivity.this, "Update Successful.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(DetailsActivity.this, "Update Failed.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
    public void onBackPressed() {
        super.onBackPressed();
    }
}
