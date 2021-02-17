package com.example.cst438_group5_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuPage extends AppCompatActivity {

    Button viewProfile;
    Button searchJobs;
    Button savedJobs;
    Button logout;

    TextView welcomeMessage;

//        Get username from login activity
//        Bundle extras = getIntent().getExtras();
//        String userName = extras.getStr("username");
//        welcomeMessage.setText("Welcome, " + userName);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_page);

        viewProfile = findViewById(R.id.my_profile);
        searchJobs = findViewById(R.id.search_for_jobs);
        savedJobs = findViewById(R.id.saved_jobs);
        logout = findViewById(R.id.logout);

        viewProfile.setOnClickListener(v -> viewProf());
        searchJobs.setOnClickListener(v -> search());
        savedJobs.setOnClickListener(v -> saves());
        logout.setOnClickListener(v -> signOut());




    }

    public void viewProf(){
        Intent intent = new Intent(this, ViewProfile.class);
        startActivity(intent);
    }

    public void search(){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void saves(){
        Intent intent = new Intent(this, SavedJobsActivity.class);
        startActivity(intent);
    }

    public void signOut(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}