package com.example.cst438_group5_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cst438_group5_project1.model.Account;
import com.example.cst438_group5_project1.model.AccountDao;
import com.example.cst438_group5_project1.model.AccountDatabase;

import org.w3c.dom.Text;

import java.util.List;

public class CreateNewAccount extends AppCompatActivity {

    private Account account;
    private AccountDao accountDAO;
    List<Account> accounts;

    private EditText tempNewUserName;
    private EditText tempNewPassWord;
    private String newUserName;
    private String newPassWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        getDataBase();
        getUsers();
        createAccount();
    }

    private void getUsers(){
        accounts = accountDAO.getAll();
    }

    public void getDataBase(){
        accountDAO = Room.databaseBuilder(this, AccountDatabase.class,AccountDatabase.name)
                .allowMainThreadQueries()
                .build()
                .account();
    }

    public void createAccount(){

            tempNewUserName = (EditText) findViewById(R.id.new_username);
            tempNewPassWord = (EditText) findViewById(R.id.new_password);

            newUserName = tempNewUserName.getText().toString();
            newPassWord = tempNewPassWord.getText().toString();

            Button enter = findViewById(R.id.enter_button);
            enter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //when account is created, it takes you back to the main activity
                    accountDAO.addAccount(new Account(newUserName, newPassWord));
                    Toast.makeText(CreateNewAccount.this, "Account Created!!!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                }
            });

    }
}