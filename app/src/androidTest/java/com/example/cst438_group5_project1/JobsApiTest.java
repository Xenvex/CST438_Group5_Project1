package com.example.cst438_group5_project1;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(AndroidJUnit4.class)
public class JobsApiTest {

    private JsonPlaceHolderAPI api;

    String jobId = "1a3e40c4-e1d1-43a3-a814-c788287e040e";

    @Before
    public void setup() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jobs.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(JsonPlaceHolderAPI.class);
    }

    @Test
    public void queryApiForSavedJob() {

        Call<Post> call = api.getPostById(jobId);

        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                assertTrue(response.isSuccessful());
                Post post = response.body();
                assertEquals(jobId, post.getId());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {}
        });
    }
}