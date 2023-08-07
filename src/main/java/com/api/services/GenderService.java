package com.api.services;

import com.api.repositories.IGender;
import com.api.models.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenderService {
    private final IGender iGender;

    @Autowired
    public GenderService(IGender iGender) {
        this.iGender = iGender;
    }

    public Gender createGender(Gender data) {
        return iGender.save(data);
    }

    public Optional<Gender> getGenderById(Long id) {
        return iGender.findById(id);
    }

    public List<Gender> getGenderByName(String name) {
        return iGender.findByName(name);
    }
}