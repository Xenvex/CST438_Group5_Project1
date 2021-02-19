package com.example.cst438_group5_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cst438_group5_project1.model.Account;
import com.example.cst438_group5_project1.model.AccountDao;
import com.example.cst438_group5_project1.model.AccountDatabase;


public class ViewProfile extends AppCompatActivity {

    Button home;
    Button edit;
    Button delete;

    TextView uName;
    TextView pWord;
    TextView interests;

    Account viewingAccount;

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

        Bundle extras = getIntent().getExtras();
        String userName = extras.getString("username", "JohnDoe");

        viewingAccount = AccountDatabase.getInstance(this).account().getAccountInfo(userName);


        home.setOnClickListener(v -> goHome(viewingAccount.getUserName()));
        edit.setOnClickListener(v -> editProfile(viewingAccount.getUserName()));
        delete.setOnClickListener(v -> deleteProfile());

        uName.setText(viewingAccount.getUserName());
        pWord.setText(viewingAccount.getUserPassword());
//        interests.setText();
    }

    public void goHome(String accName){
        Intent intent = new Intent(this, MenuPage.class);
        intent.putExtra("username", accName);
        startActivity(intent);
    }

    public void editProfile(String accName){
        Intent intent = new Intent(this, EditProfile.class);
        intent.putExtra("username", accName);
        startActivity(intent);
    }

    public void deleteProfile(){

        AccountDatabase.getInstance(this).account().delete(viewingAccount);
        Toast.makeText(this, "Account deleted", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}