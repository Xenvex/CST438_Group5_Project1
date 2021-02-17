package com.example.cst438_group5_project1.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities={SavedJob.class, User.class}, version=1)
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

}