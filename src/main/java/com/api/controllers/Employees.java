package com.api.controllers;

import com.api.models.Employee;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class Employees {

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee data) {
        Employee employee = new Employee(null, data.getGenderId(), data.getJobId(), data.getName(), data.getLastName(), data.getBirthdate());
        return employee;
    }
}
