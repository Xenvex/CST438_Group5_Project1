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
    List<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_jobs);

        long currentUserId = getIntent().getLongExtra("userId", 1);

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

        // The jobs will be added to this list after receiving a response from the API
        posts = new ArrayList<>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jobs.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        for (SavedJob savedJob : savedJobs) {
            Call<Post> call = jsonPlaceHolderAPI.getPostById(savedJob.getJobId());

            call.enqueue(new Callback<Post>() {
                @Override
                public void onResponse(Call<Post> call, Response<Post> response) {
                    if (!response.isSuccessful()) {
                        return;
                    }

                    Post post = response.body();
                    posts.add(post);
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t) {

                }
            });
        }

        ListView savedJobsView = findViewById(R.id.saved_jobs_list);
        savedJobsView.setAdapter(new SavedJobsListAdapter(this, posts));
    }

    public class SavedJobsListAdapter extends ArrayAdapter<Post> {

        public SavedJobsListAdapter (Activity context, List<Post> posts) {
            super(context, R.layout.row_layout, posts);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = SavedJobsActivity.this.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.row_layout, null, true);
            TextView rowField = rowView.findViewById(R.id.row_id);

            Post post = posts.get(position);
            rowField.setText(String.format(
                    "Title: %s\n%s %s\nLocation: %s\n\n",
                    post.getTitle(),
                    post.getCompany(),
                    post.getType(),
                    post.getLocation()
            ));
            
            rowField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("SavedJobsActivity", "Clicked button to view job: " + post.getTitle());
                    // Placeholder code to view this job/post, once that page has been created
                    // Intent intent = VIEW_JOB_ACTIVITY.getIntent(getApplicationContext());
                    // intent.putExtra(JOB_TO_VIEW_EXTRA_NAME, post);
                    // startActivity(intent);
                }
            });

            return rowView;
        }
    }
}