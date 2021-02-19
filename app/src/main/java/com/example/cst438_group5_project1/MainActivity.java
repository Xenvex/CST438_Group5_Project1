package com.example.cst438_group5_project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cst438_group5_project1.model.Account;
import com.example.cst438_group5_project1.model.AccountDao;
import com.example.cst438_group5_project1.model.AccountDatabase;
import com.example.cst438_group5_project1.model.JobAppRoom;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AccountDatabase accountDB;
    private AccountDao accountDAO;
    List<Account> accounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accountDAO = AccountDatabase.getInstance(this).account();

        accountDB = AccountDatabase.getInstance(this);
        accountDB.populateInitialData(getApplicationContext());
        getUsers();

        // Load test data into the database if empty
        JobAppRoom.getJobAppRoom(this).loadTestData(this);

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
                boolean check = false;

                for(int i = 0; i < accounts.size(); i++){
                    if(accounts.get(i).getUserName().equals(newUserName) && accounts.get(i).getUserPassword().equals(newPassWord)){
                        check = true;
                        break;
                    } else{
                        check = false;
                    }
                }

                if(check) {
                    Intent newIntent = new Intent(getApplicationContext(), MenuPage.class);
                    newIntent.putExtra("username", newUserName);
                    startActivity(newIntent);
                } else{
                    Toast.makeText(getApplicationContext(), "Wrong credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getUsers(){
        accounts = accountDAO.getAll();
        //Log.i("accountSize", "Account size after: \"" + accounts.size());
    }

    public void goToCreateAccount(){
        Intent newAccount = new Intent(this, CreateNewAccount.class);
        startActivity(newAccount);
    }
}