package com.api.controllers;

import com.api.models.Employee;
import com.api.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class Employees {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee data) {
        Employee employee = new Employee(null, data.getGenderId(), data.getJobId(), data.getName(), data.getLastName(), data.getBirthdate());
        return employee;
    }
}
