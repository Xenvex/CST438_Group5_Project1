package com.example.cst438_group5_project1;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    private JsonPlaceHolderAPI jsonPlaceHolderAPI;

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

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);

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
                //Returns a list of jobs from the JSON array

                for (Post post : posts) {
                    Log.i("Log Message", "Retrieval Successful!");
                    String content = "";
                    content += "Title: " + post.getTitle() + "\n";
                    content +=  post.getCompany() + " - " + post.getType() + "\n";
                    content += "Location: " + post.getLocation() + "\n\n";
                    post.getId(); //This is so that we can retrieve more data when clicked.
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

        //Search Function(Updates the Job List based on search Entry)
        Button searchButton = findViewById(R.id.search_button_jl);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textViewResult.setText(""); //Clear Previous Search Results
                EditText keyWords = findViewById(R.id.search_entry_jl);

                String searcher = keyWords.getText().toString();


                Call<List<Post>> call2 = jsonPlaceHolderAPI.getPostBySearch(searcher);

                call2.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call2, Response<List<Post>> response) {
                        if (!response.isSuccessful()){
                            //if not successful, this shows us what went wrong
                            textViewResult.setText("Code: " + response.code());
                            return; //Prevents us from doing any operations with a NULL response
                        }

                        List<Post> posts = response.body();
                        //Data we want from servers
                        //Returns a list of jobs from the JSON array

                        for (Post post : posts) {
                            Log.i("Log Message", "Retrieval Successful!");
                            String content = "";
                            content += "Title: " + post.getTitle() + "\n";
                            content +=  post.getCompany() + " - " + post.getType() + "\n";
                            content += "Location: " + post.getLocation() + "\n\n";
                            post.getId(); //This is so that we can retrieve more data when clicked.
                            textViewResult.append(content);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call2, Throwable t) {
                        Log.i("Log Message", "Response Failed");
                        textViewResult.setText(t.getMessage());
                    }

                });






            }

        });

    }
}
