package com.githubjobs;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.githubjobs.network.models.Job;

import java.util.ArrayList;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobHolder> {

    public interface OnItemClickListener {
        public void onItemClick(Job job);
    }

    private ArrayList<Job> jobs;
    private OnItemClickListener listener;

    public JobsAdapter(ArrayList<Job> jobs, OnItemClickListener listener) {
        this.jobs = jobs;
        this.listener = listener;
    }


    @Override
    public JobHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new JobHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_job, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(JobHolder jobHolder, int i) {
        Job job = jobs.get(i);
        TextView jobTitleTextView = jobHolder.itemView.findViewById(R.id.job_title);
        TextView jobCompanyTextView = jobHolder.itemView.findViewById(R.id.job_company);
        LinearLayout rootLayout = jobHolder.itemView.findViewById(R.id.root_layout);
        rootLayout.setOnClickListener(view -> listener.onItemClick(job));
        jobTitleTextView.setText(job.getTitle());
        jobCompanyTextView.setText(job.getCompany());
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    class JobHolder extends RecyclerView.ViewHolder {
        JobHolder(View itemView) {
            super(itemView);
        }
    }
}
