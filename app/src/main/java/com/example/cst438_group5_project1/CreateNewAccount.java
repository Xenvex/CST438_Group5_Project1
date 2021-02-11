package com.example.cst438_group5_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CreateNewAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);

    }

    public void createAccount(View v){
        if(v.getId() == R.id.enter_button){
            EditText tempNewUserName;
            EditText tempNewPassWord;
            String newUserName;
            String newPassWord;

            tempNewUserName = (EditText) findViewById(R.id.new_username);
            tempNewPassWord = (EditText) findViewById(R.id.new_password);

            newUserName = tempNewUserName.getText().toString();
            newPassWord = tempNewPassWord.getText().toString();
        }
    }
}