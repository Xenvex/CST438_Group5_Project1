package com.example.cst438_group5_project1.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

@Database(entities={SavedJob.class}, version=1)
public abstract class JobAppRoom extends RoomDatabase {
    private static JobAppRoom instance;

    public abstract JobAppDao dao();

    public static JobAppRoom getJobAppRoom(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    JobAppRoom.class,
                    "JobDB"
            ).allowMainThreadQueries().build();
        }
        return instance;
    }

    public void loadTestData(Context context) {
        JobAppDao dao = JobAppRoom.getJobAppRoom(context).dao();
        List<SavedJob> savedJobs = dao.getSavedJobs(1);
        if (savedJobs.size() == 0) {
            SavedJob jobA = new SavedJob(1, "1a3e40c4-e1d1-43a3-a814-c788287e040e");
            dao.saveJob(jobA);
            SavedJob jobB = new SavedJob(1, "79da435a-598c-43e6-a012-bfab1c841065");
            dao.saveJob(jobB);
        }
    }
}