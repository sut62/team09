package com.okta.springbootvue.Diagnose.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.Diagnose.entity.Disease;
import com.okta.springbootvue.Diagnose.repository.DiseaseRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DiseaseController {

    @Autowired
    private DiseaseRepository diseaseRepository;

    public DiseaseController(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    @GetMapping("/disease")
    public Collection<Disease> disease() {
        return diseaseRepository.findAll().stream().collect(Collectors.toList());
    }

}