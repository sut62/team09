package com.okta.springbootvue.Diagnose.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.Diagnose.entity.Diagnose;
import com.okta.springbootvue.Diagnose.entity.Disease;
import com.okta.springbootvue.Diagnose.entity.Doctor;
import com.okta.springbootvue.Diagnose.repository.DiagnoseRepository;
import com.okta.springbootvue.Diagnose.repository.DiseaseRepository;
import com.okta.springbootvue.Diagnose.repository.DoctorRepository;
import com.okta.springbootvue.Query.Entity.Query;
import com.okta.springbootvue.Query.Repository.QueryRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DiagnoseController {
    @Autowired
    private DiagnoseRepository diagnoseRepository;
    @Autowired
    private QueryRepository queryRepository;
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    DiagnoseController(DiagnoseRepository diagnoseRepository) {
        this.diagnoseRepository = diagnoseRepository;
    }

    @GetMapping("/diagnose")
    public Collection<Diagnose> Diagnose() {
        return diagnoseRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/diagnose/{queryId}/{doctorId}/{diseaseId}")
    public Diagnose newDiagnose(@PathVariable long queryId,
    @PathVariable long doctorId, @PathVariable long diseaseId) {
        Diagnose diagnose = new Diagnose();

        Query query = queryRepository.findById(queryId);  
        Doctor doctor = doctorRepository.findById(doctorId);        
        Disease disease = diseaseRepository.findById(diseaseId);
        
        diagnose.setQuery(query);
        diagnose.setDoctor(doctor);
        diagnose.setDisease(disease);
        diagnose.setNameRegister(query.getFirstName());
        diagnose.setNameDoctor(doctor.getName());
        diagnose.setNameDisease(disease.getName());

        return diagnoseRepository.save(diagnose);
    }
}