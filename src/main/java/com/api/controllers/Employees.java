package com.api.controllers;

import com.api.models.Employee;
import com.api.models.Gender;
import com.api.models.Job;
import com.api.responses.DefaultResponse;
import com.api.responses.EmployeesByJobResponse;
import com.api.services.EmployeeService;
import com.api.services.GenderService;
import com.api.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class Employees {

    private final EmployeeService employeeService;
    private final JobService jobService;
    private final GenderService genderService;

    @Autowired
    public Employees(EmployeeService employeeService, JobService jobService, GenderService genderService) {
        this.employeeService = employeeService;
        this.jobService = jobService;
        this.genderService = genderService;
    }

    private Employee getByNameAndLastName(String name, String lastName) {
        return employeeService.getEmployeeByNameAndLastName(name, lastName);
    }

    private Optional<Job> getJobById(Long id) {
        return jobService.getJobById(id);
    }

    private Optional<Gender> getGenderById(Long id) {
        return genderService.getGenderById(id);
    }

    private Integer calculateAgeByBirthdate(String birthdate) {
        LocalDate currentDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        return Period.between(LocalDate.parse(birthdate, formatter), currentDate).getYears();
    }

    private Boolean validateRequestBody(Employee data) {
        if (getByNameAndLastName(data.getName(), data.getLastName()) == null) {
            if (calculateAgeByBirthdate(data.getBirthdate()) >= 18) {
                if (getGenderById(data.getGenderId()).isPresent() && getJobById(data.getJobId()).isPresent()) {
                    return true;
                }
            }
        }
        return false;
    }

    @PostMapping("/add-employee")
    public DefaultResponse addEmployee(@RequestBody Employee data) {
        DefaultResponse defaultResponse = new DefaultResponse();

        defaultResponse.setId(null);
        defaultResponse.setSuccess(false);

        if (validateRequestBody(data)) {
            try {
                Employee employee = employeeService.createEmployee(data);
                defaultResponse.setId(employee.getId());
                defaultResponse.setSuccess(true);
                return defaultResponse;
            }catch (Exception e) {
                return defaultResponse;
            }
        }
        return defaultResponse;
    }

    public Optional<Employee> getEmployeeById(Long id) {
        if (id != null) {
            return employeeService.getEmployeeById(id);
        }
        return null;
    }

    @PostMapping("/by-job")
    public EmployeesByJobResponse getEmployeeByJobId(@RequestBody Map<String, Object> requestBody) {
        Long jobId = Long.parseLong(requestBody.get("job_id").toString());

        EmployeesByJobResponse employeesByJobResponse = new EmployeesByJobResponse();
        if (jobId != null && getJobById(jobId).isPresent()) {
            List<Employee> employees = employeeService.getEmployeeByJobId(jobId);

            employeesByJobResponse.setEmployees(employees);
            employeesByJobResponse.setSuccess(true);

            return employeesByJobResponse;
        }
        List<Employee> employees = new ArrayList<>();

        employeesByJobResponse.setEmployees(employees);
        employeesByJobResponse.setSuccess(false);

        return employeesByJobResponse;
    }
}
