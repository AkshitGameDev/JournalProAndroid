package com.example.journalpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CreateActivity extends AppCompatActivity {

    private TextInputEditText Title, Disc;
    private Button create_But;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create);

        Intent intent = getIntent();
        String Veto = intent.getStringExtra("veto");


        create_But = findViewById(R.id.c_create);
        Title = findViewById(R.id.c_title);
        Disc = findViewById(R.id.c_Disc);
        create_But.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> map = new HashMap<>();
                map.put("Title", Title.getText().toString().isEmpty() ? " " : Title.getText().toString());
                map.put("disc", Disc.getText().toString().isEmpty() ? " " : Disc.getText().toString());
                FirebaseDatabase.getInstance().getReference()
                        .child(Veto).push().setValue(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(CreateActivity.this, "Update Successful.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(CreateActivity.this, "Update Failed.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
            }
        });

    }


}