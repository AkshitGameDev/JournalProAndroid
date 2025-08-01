package com.journal.journalpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.journal.journalpro.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {


    ActivityHomeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new JournalFragment());

        binding.bottomnav.setOnItemSelectedListener(item -> {

            if(item.getItemId() == R.id.journal){
                Toast.makeText(this, "Rediericting to journal screen ", Toast.LENGTH_SHORT).show();
                replaceFragment(new JournalFragment());
            }
            else if(item.getItemId() == R.id.notes){

                Toast.makeText(this, "Rediericting to test screen ", Toast.LENGTH_SHORT).show();
                replaceFragment(new NotesFragment());
            }
            else if(item.getItemId() == R.id.scheduler){
                Toast.makeText(this, "Rediericting to test screen ", Toast.LENGTH_SHORT).show();
                replaceFragment(new SchedulerFragment());
            }
            else if(item.getItemId() == R.id.settings){
                Toast.makeText(this, "Rediericting to test screen ", Toast.LENGTH_SHORT).show();
                replaceFragment(new SettingskFragment());
            }
            return  true;
        });
    }

    public void onClickCreate(View view) {
        Intent intent = new Intent(HomeActivity.this, CreateActivity.class);
        intent.putExtra("veto", "Journals");
        startActivity(intent);
    }
    public void onClickCreteNotes(View view){
        Intent intent = new Intent(HomeActivity.this, CreateActivity.class);
        intent.putExtra("veto", "Notes");
        startActivity(intent);
    }


    public void getfragment(Fragment fragment) {
        replaceFragment(fragment);
    }

    private void  replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.f_layout, fragment);
        fragmentTransaction.commit();
    }

    public void s_nextMonth(View view) {

    }
}