package com.example.cst438_group5_project1.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

@Database(entities = {Account.class}, version = 1, exportSchema = false)
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
                    .fallbackToDestructiveMigration().build();
        }
        return aInstance;
    }

    public void populateInitialData(){
        if(account().getAll().size() == 0){
            runInTransaction(new Runnable() {
                @Override
                public void run() {
                    account().addAccount(new Account("test1", "test1"));
                    account().addAccount(new Account("test2","test2"));
                    account().addAccount(new Account("test3", "test3"));
                }
            });
        }
    }
}
