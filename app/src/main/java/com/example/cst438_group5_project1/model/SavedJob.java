package com.example.cst438_group5_project1.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(primaryKeys = {"userId", "jobId"})
public class SavedJob {
    final private int userId;
    @NonNull
    final private String jobId;

    public SavedJob(int userId, String jobId) {
        this.userId = userId;
        this.jobId = jobId;
    }

    public int getUserId() {
        return userId;
    }

    public String getJobId() {
        return jobId;
    }
}
