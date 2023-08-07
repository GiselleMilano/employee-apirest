package com.api.services;

import com.api.repositories.EmployeeRepository;
import com.api.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee data) {
        return employeeRepository.save(data);
    }

    public Employee getEmployeeByNameAndLastName(String name, String lastName) {
        return employeeRepository.findByNameAndLastName(name, lastName);
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public List<Employee> getEmployeeByJobId(Long id) {
        return employeeRepository.findByJobId(id);
    }
}