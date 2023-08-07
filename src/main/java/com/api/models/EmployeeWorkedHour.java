package com.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class EmployeeWorkedHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "employee_id")
    @JsonProperty("employee_id")
    private Long employeeId;

    @Column(name = "worked_hours")
    @JsonProperty("worked_hours")
    private Integer workedHours;

    @Column(name = "worked_date", columnDefinition = "DATE")
    @JsonProperty("worked_date")
    private String workedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(Integer workedHours) {
        this.workedHours = workedHours;
    }

    public String getWorkedDate() {
        return workedDate;
    }

    public void setWorkedDate(String workedDate) {
        this.workedDate = workedDate;
    }
}
