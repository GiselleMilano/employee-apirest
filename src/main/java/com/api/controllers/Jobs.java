package com.api.controllers;

import com.api.models.Job;
import com.api.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
public class Jobs {

    @Autowired
    JobService jobService;

    @PostMapping("/add")
    public Job addJob(@RequestBody Job data) {
        Job job = new Job(data.getId(), data.getName(), data.getSalary());
        return job;
    }
}
