package com.example.cst438_group5_project1.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AccountDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAccount(Account account);

    @Update
    void update(Account account);

    @Query("SELECT * FROM accounts")
    List<Account> getAll();

    @Delete
    void delete(Account account);
}
