package com.example.cst438_group5_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.widget.Button;
import android.widget.TextView;

public class ViewProfile extends AppCompatActivity {

    Button home;
    TextView uName;
    TextView pWord;
    TextView interests;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        home = findViewById(R.id.goHome);
        uName = findViewById(R.id.usernameView);
        pWord = findViewById(R.id.passwordView);
        interests = findViewById(R.id.interestsView);

        home.setOnClickListener(v -> goHome());

//        uName.setText();
//        pWord.setText();
//        interests.setText();
    }


    public void goHome(){
        Intent intent = new Intent(this, MenuPage.class);
        startActivity(intent);
    }
}