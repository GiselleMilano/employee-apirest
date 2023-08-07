package com.api.controllers;

import com.api.models.Gender;
import com.api.responses.DefaultResponse;
import com.api.services.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/genders")
public class Genders {

    @Autowired
    GenderService genderService;

    @PostMapping("/add")
    public DefaultResponse addGender(@RequestBody Gender data) {
        DefaultResponse defaultResponse = new DefaultResponse();

        defaultResponse.setId(null);
        defaultResponse.setSuccess(false);

        if (data.getName() != null && !data.getName().isEmpty()) {
            if (genderService.getGenderByName(data.getName()).size() == 0) {
                try {
                    Gender gender = genderService.createGender(data);
                    defaultResponse.setId(gender.getId());
                    defaultResponse.setSuccess(true);
                    return defaultResponse;
                }catch(Exception e) {
                    return defaultResponse;
                }
            }
        }
        return defaultResponse;
    }

    @GetMapping("/get/{id}")
    public Optional<Gender> getGenderById(@PathVariable Long id) {
        if (id != null) {
            return genderService.getGenderById(id);
        }
        return null;
    }
}
