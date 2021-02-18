package com.example.cst438_group5_project1;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.cst438_group5_project1.model.JobAppDao;
import com.example.cst438_group5_project1.model.JobAppRoom;
import com.example.cst438_group5_project1.model.SavedJob;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class SavedJobsActivityTest {

    private JobAppRoom db;
    private JobAppDao dao;

    final private String[] jobIds = {
            "1a3e40c4-e1d1-43a3-a814-c788287e040e",
            "79da435a-598c-43e6-a012-bfab1c841065"
    };

    @Before
    public void setup() {
        Context context = ApplicationProvider.getApplicationContext();

        // Create in-memory database
        db = Room.inMemoryDatabaseBuilder(context, JobAppRoom.class).build();
        dao = db.dao();
    }

    @After
    public void cleanup() {
        db.close();
    }

    @Test
    public void retrieveSavedJobsTest() {
        // Add some saved jobs to the database
        for (String jobId : jobIds) {
            // Create savedJob object
            SavedJob savedJob = new SavedJob(1, jobId);
            // Insert into db
            dao.saveJob(savedJob);
        }

        // Retrieve them
        List<SavedJob> savedJobs = dao.getSavedJobs(1);

        // Make sure all of the correct ones have been retrieved
        for (String jobId : jobIds) {
            boolean retrieved = false;
            for (SavedJob savedJob : savedJobs) {
                if (savedJob.getJobId().equals(jobId)) {
                    retrieved = true;
                    break;
                }
            }
            Log.i("retrieveSavedJobsTest", "Retrieved job " + jobId + ": " + retrieved);
            assertTrue(retrieved);
        }
    }
}