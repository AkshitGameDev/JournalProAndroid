package com.journal.journalpro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JournalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JournalFragment extends Fragment {
    RecyclerView recyclerView;
    private TextInputEditText j_search;
    FirebaseRecyclerOptions<journalModel> options;
    private journalRVadapter adapter;

    private Button create_but;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    String veto = " ";


    public JournalFragment() {
        // Required empty public constructor
    }

    public static JournalFragment newInstance(String param1, String param2) {
        JournalFragment fragment = new JournalFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_journal, container, false);
        recyclerView = rootView.findViewById(R.id.j_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        j_search = rootView.findViewById(R.id.j_search);

        j_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                performSearch(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setupJournalModels();
        adapter = new journalRVadapter(options, getContext());
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private  void performSearch(String str){
        options = new FirebaseRecyclerOptions.Builder<journalModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().orderByChild("Title").startAt(str).endAt(str+"~"), journalModel.class)
                .build();
        adapter = new journalRVadapter(options, getContext());
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    private void setupJournalModels() {
        options = new FirebaseRecyclerOptions.Builder<journalModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Journals"), journalModel.class)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
