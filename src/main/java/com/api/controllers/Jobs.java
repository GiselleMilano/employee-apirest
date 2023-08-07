package com.api.controllers;

import com.api.models.Job;
import com.api.responses.DefaultResponse;
import com.api.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class Jobs {

    @Autowired
    JobService jobService;

    @PostMapping("/add")
    public DefaultResponse addJob(@RequestBody Job data) {
        DefaultResponse defaultResponse = new DefaultResponse();

        defaultResponse.setId(null);
        defaultResponse.setSuccess(false);

        if ((data.getName() != null && !data.getName().isEmpty()) && (data.getSalary() != null && data.getSalary() > 0)) {
            if (jobService.getJobByName(data.getName()).size() == 0) {
                try {
                    Job job = jobService.createJob(data);
                    defaultResponse.setId(job.getId());
                    defaultResponse.setSuccess(true);
                    return defaultResponse;
                }catch(Exception e) {
                    return defaultResponse;
                }
            }
        }
        return defaultResponse;
    }

    @GetMapping("/get/{id}")
    public Optional<Job> getJobById(@PathVariable Long id) {
        if (id != null) {
            return jobService.getJobById(id);
        }
        return null;
    }
}
