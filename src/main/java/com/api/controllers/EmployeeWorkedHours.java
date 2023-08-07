package com.api.controllers;

import com.api.models.Employee;
import com.api.models.EmployeeWorkedHour;
import com.api.requests.TotalWorkedHoursRequest;
import com.api.responses.DefaultResponse;
import com.api.responses.TotalWorkedHoursResponse;
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

    private Boolean validateStartAndEndDate(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate startDateParsed = LocalDate.parse(startDate, formatter);
            LocalDate endDateParsed = LocalDate.parse(endDate, formatter);

            if (startDateParsed.isBefore(endDateParsed)) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    private Boolean validateEmployeeId(Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            return true;
        }
        return false;
    }

    private Boolean validateRequestBody(String workedDate, Integer workedHours, Long employeeId) {
        if (employeeId != null && (workedHours != null && workedHours > 0) && (workedDate != null && !workedDate.isEmpty())) {
            if (validateEmployeeId(employeeId)) {
                if (workedHours <= 20) {
                    List<EmployeeWorkedHour> workedHoursByDate = employeeWorkedHourService.getWorkedHourByDateAndEmployeeId(employeeId, workedDate);
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

    @PostMapping("/add-worked-hours")
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

    @PostMapping("/total-worked-hours")
    public TotalWorkedHoursResponse getTotalWorkedHours(@RequestBody TotalWorkedHoursRequest data) {
        TotalWorkedHoursResponse totalWorkedHoursResponse = new TotalWorkedHoursResponse();

        totalWorkedHoursResponse.setTotal_worked_hours(null);
        totalWorkedHoursResponse.setSuccess(false);

        if (validateEmployeeId(data.getEmployeeId()) && validateStartAndEndDate(data.getStartDate(), data.getEndDate())) {
            try {
                Integer totalWorkedHours = employeeWorkedHourService.getTotalWorkedHoursByDateRange(data.getEmployeeId(), data.getStartDate(), data.getEndDate());

                totalWorkedHoursResponse.setTotal_worked_hours(totalWorkedHours);
                totalWorkedHoursResponse.setSuccess(true);

                return totalWorkedHoursResponse;
            } catch(Exception e) {
                return totalWorkedHoursResponse;
            }
        }
        return totalWorkedHoursResponse;
    }
}
