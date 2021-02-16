package com.example.cst438_group5_project1;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import com.example.cst438_group5_project1.model.JobAppRoom;
import com.example.cst438_group5_project1.model.SavedJob;

public class SavedJobsActivity extends AppCompatActivity {

    List<SavedJob> savedJobs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_jobs);

        int currentUserId = getIntent().getIntExtra("userId", 1);

        savedJobs = JobAppRoom.getJobAppRoom(this).dao().getSavedJobs(currentUserId);

        ListView savedJobsView = findViewById(R.id.saved_jobs_list);
        savedJobsView.setAdapter(new SavedJobsListAdapter(this, savedJobs));
    }

    public class SavedJobsListAdapter extends ArrayAdapter<SavedJob> {

        public SavedJobsListAdapter (Activity context, List<SavedJob> savedJobs) {
            super(context, R.layout.row_layout, savedJobs);
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = SavedJobsActivity.this.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.row_layout, null, true);
            TextView rowField = rowView.findViewById(R.id.row_id);
            //rowField.setText(savedJobs.get(position).toString());
            rowField.setText("Placeholder: Saved job #" + position);
            return rowView;
        }
    }
}