package com.api.services;

import com.api.repositories.GenderRepository;
import com.api.models.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenderService {
    private final GenderRepository genderRepository;

    @Autowired
    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public Gender createGender(Gender data) {
        return genderRepository.save(data);
    }

    public Optional<Gender> getGenderById(Long id) {
        return genderRepository.findById(id);
    }

    public List<Gender> getGenderByName(String name) {
        return genderRepository.findByName(name);
    }
}