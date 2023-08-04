package com.api.controllers;

import com.api.models.Job;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jobs")
public class Jobs {

    @PostMapping("/add")
    public Job addJob(@RequestBody Job data) {
        Job job = new Job(data.getId(), data.getName(), data.getSalary());
        return job;
    }
}
