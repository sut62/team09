package com.okta.springbootvue.Appointment.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;


import com.okta.springbootvue.Appointment.Entity.*;
import com.okta.springbootvue.Appointment.Repository.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ClinicController {

    @Autowired
    private ClinicRepository clinicRepository;
    
    public ClinicController(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    @GetMapping("/clinic")
    public Collection<Clinic> Clinics() {
        return clinicRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/clinic/{clinicId}")
    public Optional<Clinic>Clinic(@PathVariable Long clinicId) {
        return clinicRepository.findById(clinicId);

    }

    @GetMapping("/clinic/{name}")
    public Clinic addClinic(@PathVariable String name) {
        Clinic c = new Clinic();
        c.setClinic(name);
        return clinicRepository.save(c);

    }
}