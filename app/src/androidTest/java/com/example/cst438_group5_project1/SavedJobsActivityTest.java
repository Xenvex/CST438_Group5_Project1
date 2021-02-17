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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class SavedJobsActivityTest {

    private JobAppRoom db;
    private JobAppDao dao;

    private List<Post> posts = new ArrayList<>();

    @Before
    public void setup() {
        Context context = ApplicationProvider.getApplicationContext();

        // Create in-memory database
        db = Room.inMemoryDatabaseBuilder(context, JobAppRoom.class).build();
        dao = db.dao();

        // Create some post objects
        Post post = new Post(
                "1a3e40c4-e1d1-43a3-a814-c788287e040e",
                "Front & Back End Dev's for E2EE Email Service",
                "CTemplar",
                "Part Time",
                "online"
        );
        posts.add(post);
        post = new Post(
                "79da435a-598c-43e6-a012-bfab1c841065",
                "Engineering Manager",
                "Pixelberry Studios",
                "Full Time",
                "Mountain View, CA"
        );
        posts.add(post);
    }

    @After
    public void cleanup() {
        db.close();
    }

    @Test
    public void retrieveSavedJobsTest() {
        // Add some saved jobs to the database
        for (Post post : posts) {
            // Create savedJob object
            SavedJob savedJob = new SavedJob(1, post.getId());
            // Insert into db
            dao.saveJob(savedJob);
        }

        // Retrieve them
        List<SavedJob> savedJobs = dao.getSavedJobs(1);

        // Make sure all of the correct ones have been retrieved
        for (Post post : posts) {
            boolean retrieved = false;
            for (SavedJob savedJob : savedJobs) {
                if (savedJob.getJobId().equals(post.getId())) {
                    retrieved = true;
                    break;
                }
            }
            Log.i("retrieveSavedJobsTest", "Retrieved job " + post.getId() + ": " + retrieved);
            assertTrue(retrieved);
        }
    }
}