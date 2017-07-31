package com.mycom.coe.retrofitexample.GitHubServicess;

import com.mycom.coe.retrofitexample.GitHubUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by coe on 7/31/2017.
 */

public interface GitHubService {
    @GET("users/{user}")        // MUST end URL WITHOUT '/'
    Call<GitHubUser> loadUser(@Path("user") String user);
}
