package com.okta.springbootvue.Diagnose.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.Diagnose.entity.Query;
import com.okta.springbootvue.Diagnose.entity.Doctor;
import com.okta.springbootvue.Diagnose.entity.Disease;
import com.okta.springbootvue.Diagnose.entity.Diagnose;
import com.okta.springbootvue.Diagnose.repository.DiseaseRepository;
import com.okta.springbootvue.Diagnose.repository.DoctorRepository;
import com.okta.springbootvue.Diagnose.repository.QueryRepository;
import com.okta.springbootvue.Diagnose.repository.DiagnoseRepository;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class DiagnoseController {
    @Autowired
    private final DiagnoseRepository diagnoseRepository;
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

    @PostMapping("/diagnose")
    public Diagnose newDiagnose(@RequestBody Map<String, String> body) {
        Diagnose diagnose = new Diagnose();
        
 
        Long queryId = Long.valueOf(body.get("queryId").toString());
        Query query = queryRepository.findById(queryId).get();
        diagnose.setQuery(query);

        Long doctorId = Long.valueOf(body.get("doctorId").toString());
        Doctor doctor = doctorRepository.findById(doctorId).get();
        diagnose.setDoctor(doctor);

        Long diseaseId = Long.valueOf(body.get("diseaseId").toString());
        Disease disease = diseaseRepository.findById(diseaseId).get();
        diagnose.setDisease(disease);

        return diagnoseRepository.save(diagnose);
    }
}