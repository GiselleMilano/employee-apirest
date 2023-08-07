package com.api.services;

import com.api.models.EmployeeWorkedHour;
import com.api.repositories.IEmployeeWorkedHour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeWorkedHourService {
    private final IEmployeeWorkedHour iEmployeeWorkedHour;

    @Autowired
    public EmployeeWorkedHourService(IEmployeeWorkedHour iEmployeeWorkedHour) {
        this.iEmployeeWorkedHour = iEmployeeWorkedHour;
    }

    public EmployeeWorkedHour createWorkedHour(EmployeeWorkedHour data) {
        return iEmployeeWorkedHour.save(data);
    }

    public Optional<EmployeeWorkedHour> getWorkedHourById(Long id) {
        return iEmployeeWorkedHour.findById(id);
    }

    public List<EmployeeWorkedHour> getWorkedHourByDate(String date) {
        return iEmployeeWorkedHour.findByWorkedDate(date);
    }
}
