package com.example.cst438_group5_project1.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AccountDao {

    @Insert
    void addAccount(Account account);

    @Query("SELECT * FROM accounts")
    List<Account> getAll();

    @Query("SELECT * FROM accounts WHERE name=:username")
    Account getAccountInfo(String username);

    @Delete
    void delete(Account account);
}
