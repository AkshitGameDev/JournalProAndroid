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

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotesFragment extends Fragment {

    String veto = " ";
    RecyclerView recyclerView;
    private TextInputEditText j_search;
    FirebaseRecyclerOptions<notesModel> options;
    private notesRVadapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotesFragment newInstance(String param1, String param2) {
        NotesFragment fragment = new NotesFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_notes, container, false);
        recyclerView = rootView.findViewById(R.id.n_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        j_search = rootView.findViewById(R.id.n_search);

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


        setupNotesModels();

        adapter = new notesRVadapter(options, getContext());
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private  void performSearch(String str){
        options = new FirebaseRecyclerOptions.Builder<notesModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().orderByChild("Title").startAt(str).endAt(str+"~"), notesModel.class)
                .build();
        adapter = new notesRVadapter(options, getContext());
        adapter.startListening();
        recyclerView.setAdapter(adapter);
    }

    private void setupNotesModels() {
        options = new FirebaseRecyclerOptions.Builder<notesModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Notes"), notesModel.class)
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
