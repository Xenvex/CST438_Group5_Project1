package com.example.cst438_group5_project1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderAPI {

    @GET("positions.json")
    Call<List<Post>> getPosts(); //Execute a Call and Response later on.
}
