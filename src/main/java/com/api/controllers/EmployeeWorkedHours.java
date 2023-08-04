package com.api.controllers;

import com.api.models.EmployeeWorkedHour;
import com.api.services.EmployeeWorkedHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee-worked-hours")
public class EmployeeWorkedHours {

    @Autowired
    EmployeeWorkedHourService employeeWorkedHourService;

    @PostMapping("/add")
    public EmployeeWorkedHour addWorkedHours(@RequestBody EmployeeWorkedHour data) {
        EmployeeWorkedHour workedHours = new EmployeeWorkedHour(null, data.getEmployeeId(), data.getWorkedHours(), data.getWorkedDate());
        return workedHours;
    }
}
