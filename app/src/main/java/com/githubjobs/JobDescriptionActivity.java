package com.githubjobs;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.githubjobs.network.ApiServiceFactory;
import com.githubjobs.network.models.JobDescription;

import org.w3c.dom.Text;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JobDescriptionActivity extends AppCompatActivity {

    private Call<JobDescription> getJobDescriptionCall;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description);
        getJobDescription(getIntent().getStringExtra("id"));
    }

    private void initViews(JobDescription job){
        TextView jobTitle = findViewById(R.id.job_title);
        TextView jobCompany = findViewById(R.id.job_company);
        TextView jobLocation = findViewById(R.id.location);
        TextView createdAt = findViewById(R.id.created_at);
        TextView description = findViewById(R.id.description);
        TextView backButton = findViewById(R.id.backbutton);

        jobTitle.setText(job.getTitle());
        jobCompany.setText((job.getCompany()));
        jobLocation.setText(job.getLocation());
        createdAt.setText(job.getCreated_at());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            description.setText(Html.fromHtml(job.getDescription(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            description.setText(Html.fromHtml(job.getDescription()));
        }
        description.setMovementMethod(new ScrollingMovementMethod());
        backButton.setOnClickListener(view -> onBackPressed());

    }

    private void getJobDescription(String id){
        getJobDescriptionCall = ApiServiceFactory.getApiService().getJob(id);
        getJobDescriptionCall.enqueue(new Callback<JobDescription>() {
            @Override
            public void onResponse(Call<JobDescription> call, Response<JobDescription> response) {
                if(response.isSuccessful() && response.body() != null){
                    initViews(response.body());
                }
            }

            @Override
            public void onFailure(Call<JobDescription> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Something went wrong. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(getJobDescriptionCall != null){
            getJobDescriptionCall.cancel();
        }
    }
}
