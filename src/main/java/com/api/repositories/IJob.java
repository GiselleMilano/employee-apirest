package com.api.repositories;

import com.api.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IJob extends JpaRepository<Job, Long> {
    List<Job> findByName(String name);
}