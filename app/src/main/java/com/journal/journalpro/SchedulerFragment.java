package com.journal.journalpro;

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
    public LocalDate SelectedDate;

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
        SelectedDate = LocalDate.now();

        setMonthView();

        nextMonth = rootView.findViewById(R.id.s_nextMonth);
        previousMonth = rootView.findViewById(R.id.s_previousMonth);
        weeklyEvents = rootView.findViewById(R.id.weeeklyAction);
        nextMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedDate = SelectedDate.plusMonths(1);
                setMonthView();
            }
        });
        previousMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectedDate = SelectedDate.minusMonths(1);
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
        MonthYearText.setText(monthYearFromDate(SelectedDate));
        ArrayList<String> daysInMonth = daysInMonthArray(SelectedDate);
        CalanderAdapter calanderAdapter = new CalanderAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        CalanderRecyclerView.setLayoutManager(layoutManager);
        CalanderRecyclerView.setAdapter(calanderAdapter);
    }

    private ArrayList<String> daysInMonthArray(LocalDate date) {
        ArrayList<String> daysInMonthArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();
        LocalDate firstOfMonth = SelectedDate.withDayOfMonth(1);
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        for (int i = 1; i <= 42; i++) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("");
            } else {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }
        return daysInMonthArray;
    }

    private String monthYearFromDate(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return localDate.format(formatter);
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if (!dayText.equals("")) {
            String message = "Selected Date " + dayText + " " + monthYearFromDate(SelectedDate);
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }
}
