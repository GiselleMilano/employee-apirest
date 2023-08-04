package com.api.repositories;

import com.api.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJob extends JpaRepository<Job, Long> {}