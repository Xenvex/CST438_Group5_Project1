package com.example.cst438_group5_project1.model;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

@Database(entities = {Account.class}, version = 2, exportSchema = false)
public abstract class AccountDatabase extends RoomDatabase {

    List<Account> accounts;
    public abstract AccountDao account();

    public static final String name = "accountD";

    private static AccountDatabase aInstance;

    public static synchronized AccountDatabase getInstance(Context context){
        if(aInstance == null){
            aInstance = Room.databaseBuilder(context.getApplicationContext(),
                    AccountDatabase.class,
                    "account.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return aInstance;
    }

    public void populateInitialData(Context context){
            runInTransaction(new Runnable() {

                @Override
                public void run() {
                    AccountDao DAO = getInstance(context).account();
                    DAO.addAccount(new Account("test1", "test1"));
                    DAO.addAccount(new Account("test2","test2"));
                    DAO.addAccount(new Account("test3", "test3"));
                    //Log.i("PopulateInitialData", "Running");
                }
            });

    }
}
