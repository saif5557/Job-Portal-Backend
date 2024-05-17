package com.saifmit.firstjobapp.company;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saifmit.firstjobapp.job.Job;
import com.saifmit.firstjobapp.review.Review;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String name;
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy ="company")
    private List<Job> jobs;

    @OneToMany(mappedBy = "company")
    private List<Review> reviews;

   public Company() {
    }

    public Company(Long id, String company_name, String company_description, List<Job> jobs) {
        this.id = id;
        this.name = company_name;
        this.description = company_description;
        this.jobs = jobs;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}
