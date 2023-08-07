package com.api.repositories;

import com.api.models.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGender extends JpaRepository<Gender, Long> {
    List<Gender> findByName(String name);
}