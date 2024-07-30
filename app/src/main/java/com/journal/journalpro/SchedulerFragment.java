package com.journal.journalpro;

import static com.journal.journalpro.CalanderUtils.monthYearFromDate;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Stack;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SchedulerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SchedulerFragment extends Fragment implements CalanderAdapter.OnItemListener {
    public Button nextMonth, previousMonth, weeklyEvents;
    public TextView MonthYearText;
    public RecyclerView CalanderRecyclerView;


    public SchedulerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_scheduler, container, false);
        CalanderRecyclerView = rootView.findViewById(R.id.calanderRV);
        MonthYearText = rootView.findViewById(R.id.MonthYearTv);
        CalanderUtils.SelectedDate = LocalDate.now();

        setMonthView();

        nextMonth = rootView.findViewById(R.id.s_nextMonth);
        previousMonth = rootView.findViewById(R.id.s_previousMonth);
        weeklyEvents = rootView.findViewById(R.id.weeeklyAction);
        nextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalanderUtils.SelectedDate = CalanderUtils.SelectedDate.plusMonths(1);
                setMonthView();
            }
        });
        previousMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalanderUtils.SelectedDate = CalanderUtils.SelectedDate.minusMonths(1);
                setMonthView();
            }
        });
        weeklyEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(rootView.getContext(), WeekViewActivity.class));
            }
        });


        return rootView;
    }

    void setMonthView() {
        MonthYearText.setText(monthYearFromDate(CalanderUtils.SelectedDate));
        ArrayList<LocalDate> days = CalanderUtils.daysInMonthArray(CalanderUtils.SelectedDate);
        CalanderAdapter calanderAdapter = new CalanderAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        CalanderRecyclerView.setLayoutManager(layoutManager);
        CalanderRecyclerView.setAdapter(calanderAdapter);
    }


    @Override
    public void onItemClick(int position, LocalDate date) {
        if(date!= null) {
            CalanderUtils.SelectedDate = date;
            setMonthView();
        }

    }
}
