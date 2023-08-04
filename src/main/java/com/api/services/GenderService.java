package com.api.services;

import com.api.repositories.IGender;
import com.api.models.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenderService {
    @Autowired
    IGender iGender;

    public Gender CreateGender(Gender data) {
        return null;
    }
    public Gender SelectGender(Gender genderId) {
        return null;
    }
}