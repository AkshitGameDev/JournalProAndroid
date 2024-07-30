package com.journal.journalpro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity {


    private TextView dateTV;
    private EditText eventNameET;
    private Button saveEvent;

    private LocalTime time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_edit);
        Initwidgets();
        time = LocalTime.now();

        dateTV.setText("Date: " +  CalanderUtils.FormattedDate(CalanderUtils.SelectedDate));

        saveEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = eventNameET.getText().toString();
                Event newEvent = new Event(eventName, CalanderUtils.SelectedDate);
                Event.eventsList.add(newEvent);
                finish();
            }
        });

    }



    private void Initwidgets() {
        eventNameET = findViewById(R.id.EventTitle);
        saveEvent = findViewById(R.id.saveEvent);
        dateTV = findViewById(R.id.dateTv);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}