package com.api.services;

import com.api.models.EmployeeWorkedHour;
import com.api.repositories.EmployeeWorkedHourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeWorkedHourService {
    private final EmployeeWorkedHourRepository employeeWorkedHourRepository;

    @Autowired
    public EmployeeWorkedHourService(EmployeeWorkedHourRepository employeeWorkedHourRepository) {
        this.employeeWorkedHourRepository = employeeWorkedHourRepository;
    }

    public EmployeeWorkedHour createWorkedHour(EmployeeWorkedHour data) {
        return employeeWorkedHourRepository.save(data);
    }

    public Optional<EmployeeWorkedHour> getWorkedHourById(Long id) {
        return employeeWorkedHourRepository.findById(id);
    }

    public List<EmployeeWorkedHour> getWorkedHourByDate(String date) {
        return employeeWorkedHourRepository.findByWorkedDate(date);
    }
}
