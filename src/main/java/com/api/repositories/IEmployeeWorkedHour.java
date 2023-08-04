package com.api.repositories;

import com.api.models.EmployeeWorkedHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeWorkedHour extends JpaRepository<EmployeeWorkedHour, Long> {}
