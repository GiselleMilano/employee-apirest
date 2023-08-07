package com.api.repositories;

import com.api.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByNameAndLastName(String name, String lastName);
    List<Employee> findByJobId(Long id);
}