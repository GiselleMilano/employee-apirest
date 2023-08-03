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
}
