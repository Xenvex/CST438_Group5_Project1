package com.example.cst438_group5_project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_account);
        accountDAO = AccountDatabase.getInstance(this).account();
        getUsers();
        createAccount();
    }

    private void getUsers(){
        accounts = accountDAO.getAll();
        //Log.i("accountSize", "Account size after: \"" + accounts.size());
    }

    public void createAccount(){

            Button enter = findViewById(R.id.create_button);
            enter.setOnClickListener(new View.OnClickListener() {
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

                    //when account is created, it takes you back to the main activity
                    boolean check = false;
                    //Log.i("createNewAccountActivity", "size: \"" + accounts.size());
                    for(int i = 0; i < accounts.size(); i++){
                        Log.i("createNewAccountActivity", "userName: \"" + accounts.get(i).getUserName());
                        if(accounts.get(i).getUserName().equals(newUserName)){
                            check = true;
                            break;
                        }
                    }

                    //checks for authentication
                    if(check){
                        Toast.makeText(getApplicationContext(), "Account is already taken", Toast.LENGTH_SHORT).show();
                    } else{
                        accountDAO.addAccount(new Account(newUserName, newPassWord));
                        Log.i("createNewAccountActivity", "Created account with: \"" + newUserName);

                        Toast.makeText(getApplicationContext(), "Account Created!!!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }
                }
            });

    }
}