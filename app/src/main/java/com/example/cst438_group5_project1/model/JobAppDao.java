package com.example.cst438_group5_project1.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface JobAppDao {

    // User Queries
    @Insert
    void createUser(User user);

    @Query("SELECT * FROM User WHERE id=:id LIMIT 1")
    User getUserById(int id);

    @Delete
    void deleteUser(User user);

    // SavedJob Queries
    @Insert
    void saveJob(SavedJob savedJob);

    @Query("SELECT * FROM SavedJob WHERE userId=:userId")
    List<SavedJob> getSavedJobs(int userId);

    @Delete
    void removeSavedJob(SavedJob savedJob);

}