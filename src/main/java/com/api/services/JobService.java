package com.api.services;

import com.api.repositories.IJob;
import com.api.models.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    @Autowired
    IJob iJob;

    public Job CreateJob(Job data) {
        return null;
    }
    public Job SelectJob(Long jobId) {
        return null;
    }
}