package com.api.controllers;

import com.api.models.Employee;
import com.api.models.EmployeeWorkedHour;
import com.api.responses.DefaultResponse;
import com.api.services.EmployeeService;
import com.api.services.EmployeeWorkedHourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee-worked-hours")
public class EmployeeWorkedHours {

    private final EmployeeService employeeService;
    private final EmployeeWorkedHourService employeeWorkedHourService;

    @Autowired
    public EmployeeWorkedHours(EmployeeService employeeService, EmployeeWorkedHourService employeeWorkedHourService) {
        this.employeeWorkedHourService = employeeWorkedHourService;
        this.employeeService = employeeService;
    }

    private Boolean validateWorkedDate(String workedDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate workedDateParsed = LocalDate.parse(workedDate, formatter);
            LocalDate currentDate = LocalDate.now();

            if (workedDateParsed.isBefore(currentDate) || workedDateParsed.isEqual(currentDate)) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private Boolean validateEmployeeId(Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (!employee.isPresent()) {
            return true;
        }
        return false;
    }

    private Boolean validateRequestBody(String workedDate, Integer workedHours, Long employeeId) {
        if (employeeId != null && (workedHours != null && workedHours > 0) && (workedDate != null && !workedDate.isEmpty())) {
            if (validateEmployeeId(employeeId)) {
                if (workedHours <= 20) {
                    List<EmployeeWorkedHour> workedHoursByDate = employeeWorkedHourService.getWorkedHourByDate(workedDate);
                    if (workedHoursByDate.size() == 0) {
                        if (validateWorkedDate(workedDate)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @PostMapping("/add")
    public DefaultResponse addWorkedHours(@RequestBody EmployeeWorkedHour data) {
        DefaultResponse defaultResponse = new DefaultResponse();

        defaultResponse.setId(null);
        defaultResponse.setSuccess(false);

        if (validateRequestBody(data.getWorkedDate(), data.getWorkedHours(), data.getEmployeeId())) {
            try {
                EmployeeWorkedHour workedHour = employeeWorkedHourService.createWorkedHour(data);
                defaultResponse.setId(workedHour.getId());
                defaultResponse.setSuccess(true);
                return defaultResponse;
            }catch(Exception e) {
                return defaultResponse;
            }
        }
        return defaultResponse;
    }

    @GetMapping("/get/{id}")
    public Optional<EmployeeWorkedHour> getWorkedHoursById(@PathVariable Long id) {
        if (id != null) {
            return employeeWorkedHourService.getWorkedHourById(id);
        }
        return null;
    }
}
