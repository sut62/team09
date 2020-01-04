package com.okta.springbootvue.Diagnose.controller;

import com.okta.springbootvue.Diagnose.entity.Symptom;
import com.okta.springbootvue.Diagnose.repository.SymptomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class SymptomController {

    @Autowired
    private final SymptomRepository symptomRepository;

    public SymptomController(SymptomRepository symptomRepository) {
        this.symptomRepository = symptomRepository;
    }

    @GetMapping("/Symptom")
    public Collection<Symptom> Symptoms() {
        return symptomRepository.findAll().stream().collect(Collectors.toList());
    }

}