package com.api.controllers;

import com.api.models.Gender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/genders")
public class Genders {

    @PostMapping("/add")
    public Gender addGender(@RequestBody Gender data) {
        Gender gender = new Gender(data.getId(), data.getName());
        return gender;
    }
}
