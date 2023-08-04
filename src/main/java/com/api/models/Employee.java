package com.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "gender_id")
    @JsonProperty("gender_id")
    private Long genderId;

    @Column(name = "job_id")
    @JsonProperty("job_id")
    private Long jobId;

    @Column
    private String name;

    @Column(name = "last_name")
    @JsonProperty("last_name")
    private String lastName;

    @Column(columnDefinition = "DATE")
    private String birthdate;

    public Employee(Long id, Long genderId, Long jobId, String name, String lastName, String birthdate) {
        this.id = id;
        this.genderId = genderId;
        this.jobId = jobId;
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

    public Long getGenderId() {
        return genderId;
    }

    public void setGenderId(Long genderId) {
        this.genderId = genderId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
