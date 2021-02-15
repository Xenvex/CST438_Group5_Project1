package com.example.cst438_group5_project1;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobListingActivity extends AppCompatActivity {
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("Log Message", "OnCreateSucessful");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_listing);

        textViewResult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jobs.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

        //network request
        Call<List<Post>> call = jsonPlaceHolderAPI.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.i("Log Message", "Response is Happening");
                if (!response.isSuccessful()){
                    //if not successful, this shows us what went wrong
                    textViewResult.setText("Code: " + response.code());
                    return; //Prevents us from doing any operations with a NULL response
                }

                List<Post> posts = response.body();
                //Data we want from servers
                //Returns a list of posts from the JSON array

                for (Post post : posts) {
                    Log.i("Log Message", "Retrieval Successful!");
                    String content = "";

                    content += "Title: " + post.getTitle() + "\n";
                    content +=  post.getCompany() + " " + post.getType() + "\n";
                    content += "Location: " + post.getLocation() + "\n\n";
                    textViewResult.append(content);
                }
            }

            //If something went wrong when communicating with the server
            //or something went wrong with the response
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.i("Log Message", "Response Failed");
                textViewResult.setText(t.getMessage());
            }
        });

    }
}
