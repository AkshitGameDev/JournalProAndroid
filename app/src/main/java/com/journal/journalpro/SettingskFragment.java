package com.journal.journalpro;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView name, disc;

    private DatabaseReference databaseReference;

    Button Logout, EditProfile;
    FirebaseAuth auth;
    FirebaseUser user;

    public SettingskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingskFragment newInstance(String param1, String param2) {
        SettingskFragment fragment = new SettingskFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_settingsk, container, false);

        auth = FirebaseAuth.getInstance();
        Logout = rootView.findViewById(R.id.logout);
        user = auth.getCurrentUser();
        name = rootView.findViewById(R.id.user_name);


        databaseReference = FirebaseDatabase.getInstance().getReference("Settings/user");


        // Retrieve data

        // Retrieve data
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Check if the data exists
                if (dataSnapshot.exists()) {
                    // Get name and about from the snapshot
                    name.setText(dataSnapshot.child("Name").getValue(String.class));
                    disc.setText(dataSnapshot.child("about").getValue(String.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors
                Log.w("FirebaseError", "loadPost:onCancelled", databaseError.toException());
            }
        });

        disc = rootView.findViewById(R.id.user_disc);
        if (user == null){
            Intent intent = new Intent(rootView.getContext(), Login.class);
            startActivity(intent);
        }
        else {

        }
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(rootView.getContext(), Login.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }


}