package com.example.cst438_group5_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.cst438_group5_project1.model.Account;
import com.example.cst438_group5_project1.model.AccountDatabase;

public class EditProfile extends AppCompatActivity {

    EditText userName;
    EditText passWord;
    EditText accountInt;

    Button cancel;
    Button save;

    Account editingAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        userName = findViewById(R.id.uName);
        passWord = findViewById(R.id.pWord);
        accountInt = findViewById(R.id.jobInt);

        Bundle extras = getIntent().getExtras();
        String uName = extras.getString("username", "JohnDoe");

        editingAcc = AccountDatabase.getInstance(this).account().getAccountInfo(uName);

        userName.setText(editingAcc.getUserName());
        passWord.setText(editingAcc.getUserPassword());
//        accountInt.setText();
        cancel.setOnClickListener(v -> cancelEdit(uName));
        save.setOnClickListener(v -> saveEdit(editingAcc, userName.getText().toString(), passWord.getText().toString()));
    }

    public void cancelEdit(String userName){
        Intent intent = new Intent(this, ViewProfile.class);
        intent.putExtra("username", userName);
        startActivity(intent);
    }

    public void saveEdit(Account account, String newUser, String newPass){
        account.setUserName(newUser);
        account.setUserPassword(newPass);

        Intent intent = new Intent(this, ViewProfile.class);
        intent.putExtra("username", newUser);
        startActivity(intent);
    }
}