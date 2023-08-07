package com.api.controllers;

import com.api.models.Employee;
import com.api.models.Job;
import com.api.requests.TotalWorkedHoursRequest;
import com.api.responses.DefaultResponse;
import com.api.responses.TotalPaymentResponse;
import com.api.services.EmployeeService;
import com.api.services.EmployeeWorkedHourService;
import com.api.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class Jobs {

    private final EmployeeService employeeService;
    private final JobService jobService;
    private final EmployeeWorkedHourService employeeWorkedHourService;

    @Autowired
    public Jobs(EmployeeService employeeService, JobService jobService, EmployeeWorkedHourService employeeWorkedHourService) {
        this.employeeService = employeeService;
        this.jobService = jobService;
        this.employeeWorkedHourService = employeeWorkedHourService;
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

    @PostMapping("/add-job")
    public DefaultResponse addJob(@RequestBody Job data) {
        DefaultResponse defaultResponse = new DefaultResponse();

        defaultResponse.setId(null);
        defaultResponse.setSuccess(false);

        if ((data.getName() != null && !data.getName().isEmpty()) && (data.getSalary() != null && data.getSalary() > 0)) {
            if (jobService.getJobByName(data.getName()).size() == 0) {
                try {
                    Job job = jobService.createJob(data);
                    defaultResponse.setId(job.getId());
                    defaultResponse.setSuccess(true);
                    return defaultResponse;
                }catch(Exception e) {
                    return defaultResponse;
                }
            }
        }
        return defaultResponse;
    }

    @PostMapping("/total-payment")
    public TotalPaymentResponse getTotalPayment(@RequestBody TotalWorkedHoursRequest data) {
        TotalPaymentResponse totalPaymentResponse = new TotalPaymentResponse();

        totalPaymentResponse.setPayment(null);
        totalPaymentResponse.setSuccess(false);

        Optional<Employee> employee = employeeService.getEmployeeById(data.getEmployeeId());

        if (employee.isPresent()) {
            Optional<Job> job = jobService.getJobById(employee.get().getJobId());

            if (job.isPresent()) {
                if (validateStartAndEndDate(data.getStartDate(), data.getEndDate())) {
                    Integer totalWorkedHours = employeeWorkedHourService.getTotalWorkedHoursByDateRange(data.getEmployeeId(), data.getStartDate(), data.getEndDate());

                    if (totalWorkedHours != null) {
                        totalPaymentResponse.setPayment(job.get().getSalary() * totalWorkedHours);
                    }else {
                        totalPaymentResponse.setPayment(0.0);
                    }
                    totalPaymentResponse.setSuccess(true);
                    return totalPaymentResponse;
                }
            }
        }
        return totalPaymentResponse;
    }

    public Optional<Job> getJobById(Long id) {
        if (id != null) {
            return jobService.getJobById(id);
        }
        return null;
    }
}
