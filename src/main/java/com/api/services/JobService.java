package com.api.services;

import com.api.repositories.IJob;
import com.api.models.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {

    private final IJob iJob;

    @Autowired
    public JobService(IJob iJob) {
        this.iJob = iJob;
    }

    public Job createJob(Job data) {
        return iJob.save(data);
    }

    public Optional<Job> getJobById(Long id) {
        return iJob.findById(id);
    }

    public List<Job> getJobByName(String name) {
        return iJob.findByName(name);
    }
}