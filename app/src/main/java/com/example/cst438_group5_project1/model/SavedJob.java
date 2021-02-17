package com.example.cst438_group5_project1.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"userId", "jobId"})
public class SavedJob {
    final private long userId;
    @NonNull
    final private String jobId;

    public SavedJob(long userId, String jobId) {
        this.userId = userId;
        this.jobId = jobId;
    }

    public long getUserId() {
        return userId;
    }

    public String getJobId() {
        return jobId;
    }
}
