package com.api.services;

import com.api.repositories.IEmployee;
import com.api.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    IEmployee iEmployee;

    public Employee CreateEmployee(Employee data) {
        return null;
    }
    public Employee SelectEmployee(Employee employeeId) {
        return null;
    }
}