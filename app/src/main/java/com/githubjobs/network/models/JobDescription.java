package com.githubjobs.network.models;

public class JobDescription {

    private String id;
    private String description;
    private String title;
    private String company;
    private String location;
    private String created_at;

    public JobDescription(String id, String description, String title, String company, String location, String created_at) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.company = company;
        this.location = location;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
