package com.api.services;

import com.api.repositories.IEmployee;
import com.api.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    private final IEmployee iEmployee;

    @Autowired
    public EmployeeService(IEmployee iEmployee) {
        this.iEmployee = iEmployee;
    }

    public Employee createEmployee(Employee data) {
        return iEmployee.save(data);
    }

    public Employee getEmployeeByNameAndLastName(String name, String lastName) {
        return iEmployee.findByNameAndLastName(name, lastName);
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return iEmployee.findById(id);
    }

    public List<Employee> getEmployeeByJobId(Long id) {
        return iEmployee.findByJobId(id);
    }
}