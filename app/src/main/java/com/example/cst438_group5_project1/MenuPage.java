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

//        welcomeMessage.setText("Welcome, " + );


    }

    public void viewProf(){
//        Intent intent = new Intent(this, MyProfile.class)
//        startActivity(intent);
    }

    public void search(){
//        Intent intent = new Intent(this, JobSearch.class)
//        startActivity(intent);
    }

    public void saves(){
//        Intent intent = new Intent(this, MySaves.class)
//        startActivity(intent);
    }

    public void signOut(){
//        Intent intent = new Intent(this, SignOut.class)
//        startActivity(intent);
    }

}