package com.example.cst438_group5_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class JobListingActivity extends AppCompatActivity {

    ///List<Job> Jobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_listing);



        //Display the JobListings

        //Give an option(button) to return to Search Activity
        Button return_to_search_button = findViewById(R.id.return_to_search_button);
        return_to_search_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            //Transfer back to Search Activity(Using Intent)
            android.content.Intent intent = new android.content.Intent(JobListingActivity.this, SearchActivity.class);
            startActivity(intent);

            }
        });


    }

    //Display Jobs based on Interest(Retrieved from JobModel)



}

