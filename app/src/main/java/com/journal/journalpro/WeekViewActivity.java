package com.journal.journalpro;

import static com.journal.journalpro.CalanderUtils.daysInWeekArray;
import static com.journal.journalpro.CalanderUtils.monthYearFromDate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;

public class WeekViewActivity extends AppCompatActivity implements CalanderAdapter.OnItemListener {

    public Button nextWeek, previousWeek;
    public TextView MonthYearText;
    public RecyclerView CalanderRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);
        initWidgets();
        setWeekView();

        nextWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalanderUtils.SelectedDate = CalanderUtils.SelectedDate.plusWeeks(1);
                setWeekView();
            }
        });

        previousWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalanderUtils.SelectedDate = CalanderUtils.SelectedDate.minusWeeks(1);
                setWeekView();
            }
        });
    }

    void initWidgets() {
        CalanderRecyclerView = findViewById(R.id.w_calanderRV);
        MonthYearText = findViewById(R.id.w_MonthYearTv);
        nextWeek = findViewById(R.id.w_nextWeek);
        previousWeek = findViewById(R.id.w_previousWeek);
    }

    void setWeekView() {
        MonthYearText.setText(monthYearFromDate(CalanderUtils.SelectedDate));
        ArrayList<LocalDate> days = CalanderUtils.daysInWeekArray(CalanderUtils.SelectedDate);
        CalanderAdapter calanderAdapter = new CalanderAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(WeekViewActivity.this, 7);
        CalanderRecyclerView.setLayoutManager(layoutManager);
        CalanderRecyclerView.setAdapter(calanderAdapter);
    }

    @Override
    public void onItemClick(int position, LocalDate date) {
        CalanderUtils.SelectedDate = date;
        setWeekView();
    }
}
