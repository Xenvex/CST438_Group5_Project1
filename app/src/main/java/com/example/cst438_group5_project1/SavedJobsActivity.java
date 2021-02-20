package com.example.cst438_group5_project1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import com.example.cst438_group5_project1.model.JobAppRoom;
import com.example.cst438_group5_project1.model.SavedJob;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SavedJobsActivity extends AppCompatActivity {

    List<SavedJob> savedJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_jobs);

        int currentUserId = getIntent().getIntExtra("userId", 1);

        // The saved jobs as they are stored in the database
        savedJobs = JobAppRoom.getJobAppRoom(this).dao().getSavedJobs(currentUserId);

        if (savedJobs.size() == 0) {
            TextView noSavedJobsMessage = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(0, 100, 0, 0);
            noSavedJobsMessage.setLayoutParams(params);
            noSavedJobsMessage.setText("You have no saved jobs");
            noSavedJobsMessage.setTextSize(20);
            noSavedJobsMessage.setGravity(Gravity.CENTER);
            ((LinearLayout)findViewById(R.id.saved_jobs_layout)).addView(noSavedJobsMessage);
        }

        // Set up array adapter for displaying saved jobs
        ListView savedJobsView = findViewById(R.id.saved_jobs_list);
        SavedJobsListAdapter listAdapter = new SavedJobsListAdapter(this);
        savedJobsView.setAdapter(listAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jobs.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        // Add saved jobs asynchronously as they are retrieved from the API
        for (SavedJob savedJob : savedJobs) {
            Call<Post> call = jsonPlaceHolderAPI.getPostById(savedJob.getJobId());

            call.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    if (!response.isSuccessful()) {
                        Log.i("SavedJobsActivity", "Request for post " + savedJob.getJobId() + " NOT SUCCESSFUL");
                        return;
                    }

                    Post post = response.body();
                    listAdapter.add(post);
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {
                    Log.i("SavedJobsActivity", "Request for post " + savedJob.getJobId() + " FAILED");
                }
            });
        }
    }

    public class SavedJobsListAdapter extends ArrayAdapter<Post> {

        public SavedJobsListAdapter (Activity context) {
            super(context, R.layout.row_layout);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = SavedJobsActivity.this.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.row_layout, null, true);
            TextView rowField = rowView.findViewById(R.id.row_id);

            Post post = getItem(position);
            rowField.setText(String.format(
                    "Title: %s\n%s %s\nLocation: %s",
                    post.getTitle(),
                    post.getCompany(),
                    post.getType(),
                    post.getLocation()
            ));
            
            rowField.setOnClickListener(v -> {
                Log.i("SavedJobsActivity", "Clicked button to view job: " + post.getTitle());
                // Placeholder code to view this job/post, once that page has been created
                // Intent intent = VIEW_JOB_ACTIVITY.getIntent(getApplicationContext());
                // intent.putExtra(JOB_TO_VIEW_EXTRA_NAME, post);
                // startActivity(intent);
            });

            return rowView;
        }
    }
}