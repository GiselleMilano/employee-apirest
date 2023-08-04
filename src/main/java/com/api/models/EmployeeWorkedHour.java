package com.api.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class EmployeeWorkedHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "worked_hours")
    private Integer workedHours;

    @Column(name = "worked_date", columnDefinition = "DATE")
    private Date workedDate;

    public EmployeeWorkedHour(Long id, Employee employee, Integer workedHours, Date workedDate) {
        this.id = id;
        this.employee = employee;
        this.workedHours = workedHours;
        this.workedDate = workedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(Integer workedHours) {
        this.workedHours = workedHours;
    }

    public Date getWorkedDate() {
        return workedDate;
    }

    public void setWorkedDate(Date workedDate) {
        this.workedDate = workedDate;
    }
}
