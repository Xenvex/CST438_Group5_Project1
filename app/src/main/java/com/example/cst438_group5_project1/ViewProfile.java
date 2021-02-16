package com.example.cst438_group5_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.widget.Button;
import android.widget.TextView;

public class ViewProfile extends AppCompatActivity {

    Button home;
    Button edit;
    Button delete;

    TextView uName;
    TextView pWord;
    TextView interests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        home = findViewById(R.id.goHome);
        edit = findViewById(R.id.editProfile);
        delete = findViewById(R.id.delete);
        uName = findViewById(R.id.usernameView);
        pWord = findViewById(R.id.passwordView);
        interests = findViewById(R.id.interestsView);

        home.setOnClickListener(v -> goHome());
        edit.setOnClickListener(v -> editProfile());
        delete.setOnClickListener(v -> deleteProfile());

//        uName.setText();
//        pWord.setText();
//        interests.setText();
    }


    public void goHome(){
        Intent intent = new Intent(this, MenuPage.class);
        startActivity(intent);
    }

    public void editProfile(){
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }

    public void deleteProfile(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}