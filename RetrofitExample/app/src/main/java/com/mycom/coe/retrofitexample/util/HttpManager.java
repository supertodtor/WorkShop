package com.mycom.coe.retrofitexample.util;

import com.mycom.coe.retrofitexample.GitHubServicess.GitHubService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by coe on 7/31/2017.
 */

public class HttpManager {

    private static HttpManager instance;
    private static GitHubService service;

    public static HttpManager getInstance() {
        if ( instance == null )
            instance = new HttpManager();
        return instance;
    }



    private HttpManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")   // MUST end url with '/'
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GitHubService.class);
    }

    public static GitHubService getService() {
        return service;
    }

}
