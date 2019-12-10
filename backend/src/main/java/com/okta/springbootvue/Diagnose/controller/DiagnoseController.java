package com.okta.springbootvue.Diagnose.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;

import com.okta.springbootvue.Diagnose.entity.*;
// import com.okta.backend.entity.Disease;
// import com.okta.backend.entity.Doctor;
// import com.okta.backend.entity.Query;
//import com.okta.backend.Booking.entity.Booking;
import com.okta.springbootvue.Diagnose.repository.*;
// import com.okta.backend.repository.QueryRepository;
// import com.okta.backend.repository.DiseaseRepository;
// import com.okta.backend.repository.DoctorRepository;

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
    public Collection<Diagnose> Domitoryrental() {
        return diagnoseRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/diagnose/{queryId}/{diseaseId}/{doctorId}")
    public Diagnose newDiagnose(@PathVariable long queryId,
    @PathVariable long diseaseId, @PathVariable long doctorId) {
        Diagnose diagnose = new Diagnose();

        Query query = queryRepository.findById(queryId);  
        Doctor doctor = doctorRepository.findById(doctorId);        
        Disease disease = diseaseRepository.findById(diseaseId);
        
        // diagnose.setQuery(query);
        // diagnose.setDoctor(doctor);
        // diagnose.setDisease(disease);
        // domitoryrental.setNameMember(booking.getMember().getFirstName());
        // domitoryrental.setNameOwner(owner.getName());
        //domitoryrental.setNameTypeagreement(typeagreement.getName());

        return diagnoseRepository.save(diagnose);
    }
}