package com.example.cst438_group5_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.cst438_group5_project1.model.AccountDatabase;

public class MainActivity extends AppCompatActivity {

    private AccountDatabase accountDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accountDB = AccountDatabase.getInstance(this);
        accountDB.populateInitialData();

        Button createAccount = (Button) findViewById(R.id.create_account);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreateAccount();
            }
        });

        Button enterButton = findViewById(R.id.enter_button);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText tempNewUserName;
                EditText tempNewPassWord;
                String newUserName;
                String newPassWord;

                tempNewUserName = (EditText) findViewById(R.id.new_username);
                tempNewPassWord = (EditText) findViewById(R.id.new_password);

                newUserName = tempNewUserName.getText().toString();
                newPassWord = tempNewPassWord.getText().toString();

                Intent newIntent = new Intent(getApplicationContext(), MenuPage.class);
                startActivity(newIntent);
            }
        });
    }

    public void goToCreateAccount(){
        Intent newAccount = new Intent(this, CreateNewAccount.class);
        startActivity(newAccount);
    }
}