package com.example.cst438_group5_project1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceHolderAPI {

    @GET("positions.json")
    Call<List<Post>> getPosts(); //Execute a Call and Response later on.

    @GET("positions/{jobId}.json")
    Call<Post> getPostById(@Path("jobId") String jobId);
}
