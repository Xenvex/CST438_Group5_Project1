package com.example.cst438_group5_project1.model;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface JobAppDao {

    // SavedJob Queries
    @Insert
    void saveJob(int userId, String jobId);

    @Query("SELECT * FROM SavedJob WHERE userId=:userId")
    List<SavedJob> getSavedJobs(int userId);

    @Delete
    void removeSavedJob(int userId, String jobId);

}