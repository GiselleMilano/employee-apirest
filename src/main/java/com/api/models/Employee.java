package com.api.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne @JoinColumn(name = "gender_id")
    private Gender gender;

    @ManyToOne @JoinColumn(name = "job_id")
    private Job job;

    @Column
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(columnDefinition = "DATE")
    private Date birthdate;

    public Employee(Long id, Gender gender, Job job, String name, String lastName, Date birthdate) {
        this.id = id;
        this.gender = gender;
        this.job = job;
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
