package com.githubjobs.network;

import com.githubjobs.network.models.Job;
import com.githubjobs.network.models.JobDescription;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("positions.json")
    Call<List<Job>> getJobs();


    @GET("positions/{id}.json")
    Call<JobDescription> getJob(@Path("id") String id);
}
