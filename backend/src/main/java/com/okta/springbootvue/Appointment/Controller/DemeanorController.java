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
public class DemeanorController {

    @Autowired
    private DemeanorRepository demeanorRepository;
    
    public DemeanorController(DemeanorRepository demeanorRepository) {
        this.demeanorRepository = demeanorRepository;
    }

    @GetMapping("/demeanor")
    public Collection<Demeanor> Demeanors() {
        return demeanorRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/demeanor/{demeanorID}")
    public Optional<Demeanor> Demeanor(@PathVariable Long demeanorID) {
        return demeanorRepository.findById(demeanorID);

    }

    @GetMapping("/demeanor/{name}")
    public Demeanor addDemeanor(@PathVariable String name) {
        Demeanor d = new Demeanor();
        d.setDemeanor(name);
        return demeanorRepository.save(d);
    }
}