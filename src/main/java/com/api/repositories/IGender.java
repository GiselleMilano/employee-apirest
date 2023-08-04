package com.api.repositories;

import com.api.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGender extends JpaRepository<Gender, Long> {}