package com.api.services;

import com.api.models.EmployeeWorkedHour;
import com.api.repositories.IEmployeeWorkedHour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeWorkedHourService {
    @Autowired
    IEmployeeWorkedHour iEmployeeWorkedHour;

    public EmployeeWorkedHour CreateEmployeeWorkedHour(EmployeeWorkedHour data) {
        return null;
    }
    public EmployeeWorkedHour SelectEmployeeWorkedHour(EmployeeWorkedHour employeeWorkedHourId) {
        return null;
    }
}
