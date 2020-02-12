package com.githubjobs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.githubjobs.network.ApiServiceFactory;
import com.githubjobs.network.models.Job;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements JobsAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private JobsAdapter jobsAdapter;
    private Call getJobsCall;
    private ArrayList<Job> jobList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        TextView logOut = findViewById(R.id.logout);
        logOut.setOnClickListener(view -> signOut());
        initRecyclerView();
        getJobs();
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        jobsAdapter = new JobsAdapter(jobList, this);
        recyclerView.setAdapter(jobsAdapter);
    }

    private void getJobs(){
        getJobsCall = ApiServiceFactory.getApiService().getJobs();
        getJobsCall.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    jobList.addAll(response.body());
                    jobsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (getJobsCall != null) {
            getJobsCall.cancel();
        }
    }

    @Override
    public void onItemClick(Job job) {
        Intent intent = new Intent(this, JobDescriptionActivity.class);
        intent.putExtra("id", job.getId());
        startActivity(intent);
    }

    // https://developers.google.com/identity/sign-in/android/disconnect#sign_out_users
    private void signOut() {
        GoogleSignInOptions gsio = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gsio);
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, task -> {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finishAffinity();
                });
    }
}
