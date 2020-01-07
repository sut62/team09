package com.okta.springbootvue.Diagnose.Controller;

import com.okta.springbootvue.Diagnose.Entity.Disease;
import com.okta.springbootvue.Diagnose.Repository.DiseaseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DiseaseController {

    @Autowired
    private final DiseaseRepository diseaseRepository;

    public DiseaseController(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @GetMapping("/disease")
    public Collection<Disease> disease() {
        return diseaseRepository.findAll().stream().collect(Collectors.toList());
    }

}