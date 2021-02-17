package com.example.cst438_group5_project1.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface JobAppDao {

    /*
    // Account Queries
    @Insert
    long insertAccount(Account account); // returns the account's id

    @Query("SELECT * FROM accounts WHERE id=:id LIMIT 1")
    User getAccount(long id);

    @Delete
    void deleteAccount(Account account);
    */

    // SavedJob Queries
    @Insert
    void saveJob(SavedJob savedJob);

    @Query("SELECT * FROM SavedJob WHERE userId=:userId")
    List<SavedJob> getSavedJobs(long userId);

    @Delete
    void removeSavedJob(SavedJob savedJob);

}