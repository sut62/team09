package com.okta.springbootvue.Diagnose.controller;

import com.okta.springbootvue.Diagnose.entity.*;
import com.okta.springbootvue.Diagnose.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DoctorController {

    @Autowired
    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/doctor")
    public Collection<Doctor> doctorRepository() {
        return doctorRepository.findAll().stream().collect(Collectors.toList());
    }

}