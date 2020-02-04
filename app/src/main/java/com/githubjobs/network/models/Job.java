package com.githubjobs.network.models;

public class Job {

    private String id;
    private String title;
    private String company;

    public Job(String id, String title, String company) {
        this.id = id;
        this.title = title;
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
